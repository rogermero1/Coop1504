package ec.fin.online15.backend.librerias.servicios;

import java.io.Serializable;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

public interface IServicioMantenimientoEntidad<E extends EntidadBasica<Id>, Id extends Serializable>
		extends IServicioMantenimiento<E, Id> {

}
