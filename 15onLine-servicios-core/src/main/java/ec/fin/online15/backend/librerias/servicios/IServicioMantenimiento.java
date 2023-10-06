package ec.fin.online15.backend.librerias.servicios;

import java.io.Serializable;
import java.util.Collection;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;

public interface IServicioMantenimiento<E, Id extends Serializable> {
	public void crear(E e) throws ExcepcionAplicacion;

	public void crearColeccion(Collection<? extends E> e)
			throws ExcepcionAplicacion;

	public E actualizar(E e) throws ExcepcionAplicacion;

	public Collection<E> actualizarColeccion(Collection<E> e)
			throws ExcepcionAplicacion;

	public void eliminar(E e) throws ExcepcionAplicacion;

	public void eliminarColeccion(Collection<E> e) throws ExcepcionAplicacion;

	public E buscarPorId(Id id);

	public E refrescar(E e);
}
