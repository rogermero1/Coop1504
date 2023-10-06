package ec.fin.online15.backend.librerias.generales;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {

	public static Type[] getTiposGenericos(Class<?> pClase) {

		ParameterizedType lTiposVariable = (ParameterizedType) pClase
				.getGenericSuperclass();

		return lTiposVariable.getActualTypeArguments();
	}
}
