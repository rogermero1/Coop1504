package ec.fin.online15.portal.transversales;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import ec.fin.online15.aplicacion.generales.ConstantesMemoria;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.paginas.BeanServicioUsuario;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGuardianAutorizacionSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGuardiaAutorizador;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;

@QualificadorGuardianAutorizacionSimple
@RequestScoped
public class BeanGuardianAutorizador implements IBeanGuardiaAutorizador {

	@Inject
	@Default
	private BeanServicioUsuario servicioUsuario;

	public boolean autorizarUrl(String url) {
		// por ahora se autoriza todo para el usuario
		boolean encontrado = false;

		if ((servicioUsuario.isUsuarioAdministrador() && ConstantesMemoria
				.getValorParametro(NombresConstantesMemoria.RUTA_PRINCIPAL,
						String.class).equals(url))
				|| (!servicioUsuario.isUsuarioAdministrador() && ConstantesMemoria
						.getValorParametro(
								NombresConstantesMemoria.RUTA_PRINCIPAL_USUARIO_WEB,
								String.class).equals(url)))
			encontrado = true;

		if (!encontrado)
			
			for (TwebOpcion opcion : servicioUsuario.getOpcionesUsuario()) {
				if (opcion.getAccion() != null)
					if (opcion.getAccion().equals(url))
						encontrado = true;
			}
 
		return encontrado;
		/*
		 * ConstantesMemoria .getValorParametro(
		 * NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION, String.class)
		 */
	}
}
