package ec.fin.online15.integracion.localizador;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionLocalizacionServicio;

public interface IServicioLocalizacion<E> {
	public E lookup() throws ExcepcionLocalizacionServicio;
}
