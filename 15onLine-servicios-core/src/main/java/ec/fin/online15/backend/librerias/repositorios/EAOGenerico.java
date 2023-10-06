package ec.fin.online15.backend.librerias.repositorios;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionEliminacionNoPermitida;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionParametrosQuerys;
import ec.fin.online15.backend.librerias.generales.GenericUtils;

public abstract class EAOGenerico<E, Id extends Serializable> implements
		IEAOGenerico<E, Id> {

	protected abstract EntityManager getEntityManager();

	public void crear(E entidad) {
		getEntityManager().persist(entidad);
		flush();
	}

	@Override
	public void crearColeccion(Collection<E> pEntidades) {
		for (E e : pEntidades)
			crear(e);
	}

	public E actualizar(E entidad) {
		E lDato = getEntityManager().merge(entidad);
		flush();
		return lDato;
	}

	public Collection<E> actualizarColeccion(Collection<E> pEntidades) {
		Collection<E> lColecionesActualizadas = new ArrayList<E>();
		for (E e : pEntidades)
			lColecionesActualizadas.add(actualizar(e));
		return lColecionesActualizadas;
	}

	@SuppressWarnings("unchecked")
	public E buscarPorId(Id pk) {
		Type[] lTipos = GenericUtils.getTiposGenericos(this.getClass());
		Class<E> lClase = (Class<E>) lTipos[0];
		return (E) getEntityManager().find(lClase, pk);
	}

	public abstract E refrescar(E entidad);

	public abstract void eliminar(E entidad)
			throws ExcepcionEliminacionNoPermitida;

	public void eliminarColeccion(Collection<E> pEntidades)
			throws ExcepcionEliminacionNoPermitida {
		for (E e : pEntidades)
			eliminar(e);
	}

	protected Query getNamedQuery(String pNamedQuery) {
		return getEntityManager().createNamedQuery(pNamedQuery);
	}

	protected Query getNamedQuery(String pNamedQuery,
			Object... pParametrosQuery) {
		Query lQuery = getNamedQuery(pNamedQuery);
		if (pParametrosQuery != null && pParametrosQuery.length > 0) {
			if (pParametrosQuery.length % 2 != 0)
				throw new ExcepcionParametrosQuerys("0",
						"SOLO FUNCIONA CON PARAMETROS PARES");
			for (int i = 0; i < pParametrosQuery.length; i = i + 2) {
				if (pParametrosQuery[i] == null)
					throw new ExcepcionParametrosQuerys("0",
							"EXISTEN PARAMETROS NULOS");
				String lNombreParametro = (String) pParametrosQuery[i];
				Object lParametro = pParametrosQuery[i + 1];
				lQuery.setParameter(lNombreParametro, lParametro);
			}
		}
		return lQuery;
	}

	@SuppressWarnings("unchecked")
	protected List<E> getResultList(String pNamedQuery,
			Object... pParametrosQuery) {
		return getNamedQuery(pNamedQuery, pParametrosQuery).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<E> getResultList(String pNamedQuery, int pFirstResult,
			Object... pParametrosQuery) {
		return getNamedQuery(pNamedQuery, pParametrosQuery).setFirstResult(
				pFirstResult).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<E> getResultList(String pNamedQuery, int pFirstResult,
			int pMaxResults, Object... pParametrosQuery) {
		return getNamedQuery(pNamedQuery, pParametrosQuery)
				.setFirstResult(pFirstResult).setMaxResults(pMaxResults)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected E getSingleResult(String pNamedQuery, Object... pParametrosQuery) {
		try {
			return (E) getNamedQuery(pNamedQuery, pParametrosQuery)
					.getSingleResult();
		} catch (NoResultException pNoResultException) {
			return null;
		}
	}

	protected Query getNamedQuery(String pNamedQuery, int pFirstResult) {
		return getNamedQuery(pNamedQuery).setFirstResult(pFirstResult);
	}

	protected Query getNamedQuery(String pNamedQuery, int pFirstResult,
			int pMaxResults) {
		return getNamedQuery(pNamedQuery, pFirstResult).setMaxResults(
				pMaxResults);
	}

	protected void flush() {
		getEntityManager().flush();
	}

	public void invocacionJDBC(EAOComandoJdbcGenerico comando) {
		this.flush();
		Session sesion = (Session) getEntityManager().getDelegate();
		sesion.doWork(comando);
	}
}
