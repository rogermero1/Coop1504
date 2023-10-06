package ec.fin.online15.backend.librerias.servicios;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;
import ec.fin.online15.backend.librerias.entidades.EntidadUtils;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;

public abstract class ServicioMantenimientoEntidad<E extends EntidadBasica<Id>, Id extends Serializable>
		extends ServicioMantenimiento<E, Id> implements
		IServicioMantenimientoEntidad<E, Id> {

	protected static <R extends EntidadBasica<Id>, Id extends Serializable> void crearActualizarRelaciones(
			Collection<R> pEntidadesRelacionadas,
			IServicioMantenimientoEntidad<R, Id> pMantenimientoEntidadRelacionada)
			throws ExcepcionAplicacion {

		// Identifico las entidades relacionadas que hay que actualizar
		Collection<R> lEntidadesRelacionadasActualizar = EntidadUtils
				.getElementosConId(pEntidadesRelacionadas);
		// Actualizar las entidades relacionadas
		lEntidadesRelacionadasActualizar = pMantenimientoEntidadRelacionada
				.actualizarColeccion(lEntidadesRelacionadasActualizar);

		// Identifico las entidades relacionadas que hay que crear
		Collection<R> lEntidadesRelacionadasCrear = EntidadUtils
				.getElementosSinId(pEntidadesRelacionadas);
		// Creo las entidades relacionadas
		pMantenimientoEntidadRelacionada
				.crearColeccion(lEntidadesRelacionadasCrear);
		// Actualizao la coleccion de entidades relacionadas para que contenga
		// los objetos atached
		pEntidadesRelacionadas.clear();
		pEntidadesRelacionadas.addAll(lEntidadesRelacionadasActualizar);
		pEntidadesRelacionadas.addAll(lEntidadesRelacionadasCrear);

	}

	protected static <R extends EntidadBasica<Id>, Id extends Serializable> void eliminarRelaciones(
			Collection<R> pEntidadesActuales, Collection<R> pEntidadesDeberian,
			IServicioMantenimientoEntidad<R, Id> pMantenimiento)
			throws ExcepcionAplicacion {
		@SuppressWarnings("unchecked")
		Collection<R> lEntidadesEliminar = (Collection<R>) CollectionUtils
				.subtract(pEntidadesActuales, pEntidadesDeberian);
		pMantenimiento.eliminarColeccion(lEntidadesEliminar);
		pEntidadesActuales.removeAll(lEntidadesEliminar);
	}

}