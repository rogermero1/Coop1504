package ec.fin.online15.backend.librerias.servicios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;

public abstract class ServicioMantenimiento<E, Id extends Serializable>
		implements IServicioMantenimiento<E, Id> {

	protected abstract EAOGenerico<E, Id> getEAO();

	@Override
	public void crear(E entidad) throws ExcepcionAplicacion {
		getEAO().crear(entidad);
	}

	@Override
	public void crearColeccion(Collection<? extends E> entidades)
			throws ExcepcionAplicacion {
		for (E entidad : entidades)
			crear(entidad);
	}

	@Override
	public E actualizar(E entidad) throws ExcepcionAplicacion {
		return getEAO().actualizar(entidad);
	}

	@Override
	public Collection<E> actualizarColeccion(Collection<E> entidades)
			throws ExcepcionAplicacion {
		Collection<E> lColecionesActualizadas = new ArrayList<E>();
		for (E entidad : entidades)
			lColecionesActualizadas.add(actualizar(entidad));
		return lColecionesActualizadas;
	}

	@Override
	public void eliminar(E entidad) throws ExcepcionAplicacion {
		getEAO().eliminar(entidad);
	}

	@Override
	public void eliminarColeccion(Collection<E> entidades)
			throws ExcepcionAplicacion {
		for (E entidad : entidades)
			eliminar(entidad);
	}

	@Override
	public E buscarPorId(Id id) {
		return getEAO().buscarPorId(id);
	}

	@Override
	public E refrescar(E entidad) {
		return getEAO().refrescar(entidad);
	}

}
