package ec.fin.online15.backend.librerias.repositorios;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;

import javax.persistence.Transient;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionEliminacionNoPermitida;

public abstract class EAOEntidad<E extends EntidadBasica<Id>, Id extends Serializable>
		extends EAOGenerico<E, Id> {

	@Override
	public E refrescar(E entidad) {
		if (getEntityManager().contains(entidad)) {
			getEntityManager().refresh(entidad);
			return entidad;
		} else {
			return buscarPorId(entidad.getId());
		}
	}

	public void eliminar(E entidad) throws ExcepcionEliminacionNoPermitida {
		entidad = buscarPorId(entidad.getId());
		getEntityManager().refresh(entidad);
		Method lMetodos[] = entidad.getClass().getMethods();
		for (Method lMetodo : lMetodos) {
			Class<?>[] lParametros = lMetodo.getParameterTypes();
			Class<?> lTipoRetorno = lMetodo.getReturnType();
			boolean lMetodoEstatico = Modifier.isStatic(lMetodo.getModifiers());
			boolean lMetodoAnotadoConTransient = lMetodo
					.isAnnotationPresent(Transient.class);

			if (lParametros.length == 0
					&& Collection.class.isAssignableFrom(lTipoRetorno)
					&& lMetodo.getName().startsWith("get") && !lMetodoEstatico
					&& !lMetodoAnotadoConTransient) {
				Collection<?> lDatos = null;

				try {
					lDatos = (Collection<?>) lMetodo.invoke(entidad);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (lDatos != null && lDatos.size() > 0)
					throw new ExcepcionEliminacionNoPermitida(
							getMensajeEliminacionNoPermitida());
			}
		}
		getEntityManager().remove(entidad);
		flush();
	}

	protected String getMensajeEliminacionNoPermitida() {
		return "Eliminacion No Permitida";
	}
}