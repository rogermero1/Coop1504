package ec.fin.online15.backend.librerias.repositorios;

import java.io.Serializable;
import java.util.Collection;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionEliminacionNoPermitida;

public interface IEAOGenerico<E, Id extends Serializable> {
	public void crear(E dato);

	public void crearColeccion(Collection<E> datos);

	public E actualizar(E dato);

	public Collection<E> actualizarColeccion(Collection<E> datos);

	public E buscarPorId(Id id);

	public E refrescar(E entidad);

	public void eliminar(E dato) throws ExcepcionEliminacionNoPermitida;

	public void eliminarColeccion(Collection<E> datos)
			throws ExcepcionEliminacionNoPermitida;
}
