package ec.fin.online15.backend.librerias.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


public class EntidadUtils {

	public static <E extends EntidadBasica<Id>, Id extends Serializable> Collection<E> getElementosSinId(
			Collection<E> entidadades) {
		Collection<E> lElementosSinId = new ArrayList<E>();
		for (E lEntidad : entidadades) {
			if (lEntidad.getId() == null)
				lElementosSinId.add(lEntidad);
		}
		return lElementosSinId;
	}

	public static <E extends EntidadBasica<Id>, Id extends Serializable> Collection<E> getElementosConId(
			Collection<E> entidadades) {
		Collection<E> lElementosConId = new ArrayList<E>();
		for (E lEntidad : entidadades) {
			if (lEntidad.getId() != null)
				lElementosConId.add(lEntidad);
		}
		return lElementosConId;
	}

	public static <E extends EntidadBasica<Id>, Id extends Serializable> void limpiarId(
			E entidad) {
		entidad.setId(null);
	}

	public static <E extends EntidadBasica<Id>, Id extends Serializable> void limpiarIdCollection(
			Collection<E> entidades) {
		for (E lEntidad : entidades)
			limpiarId(lEntidad);
	}

}
