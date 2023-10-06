package ec.fin.online15.aplicacion.paginas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;

import org.primefaces.PrimeFaces;

import ec.fin.online15.aplicacion.generales.ConstantesMemoria;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("configuracionesGeneralesMB")
@RequestScoped
public class BeanConfiguracionesGenerales extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	public void mensajeTransaccionExitosa() {
		this.aniadirMensajeInformacion(this.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES),
				this.getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_TRANSACCION_EXITOSA));
	}

	public void mensajeTransaccionError() {
		this.aniadirMensajeError(this.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES),
				this.getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_TRANSACCION_ERROR));
	}

	public void mensajeServicioNoDisponible() {
		this.aniadirMensajeError(this.getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_MENSAJES),
				this.getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_SERVICIO_NO_DISPONIBLE));
	}

	public void redireccionarUrlConstante(String url, String comodin) {
		try {
			// System.out.println("patch");
			// System.out.println(this.getContextPath());

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(this.getContextPath() + getValorConstanteConfiguracion(url) + comodin);// +
																										// "?faces-redirect=true"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void redireccionarUrl(String comodin) {
		try {
			// System.out.println("patch");
			// System.out.println(this.getContextPath());

			FacesContext.getCurrentInstance().getExternalContext().redirect(comodin);// + "?faces-redirect=true"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void regresaLogin() {
		this.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_LOGIN_APLICACION, "");
	}

	public void regresaPaginaPrincipalWeb() {
		this.redireccionarUrlConstante(NombresConstantesMemoria.RUTA_PRINCIPAL_APLICACION_USUARIO_WEB, "");
	}

	public String getValorConstanteConfiguracion(String nombreConstante) {
		return ConstantesMemoria.getValorParametro(nombreConstante, String.class);
	}

	public String getDirectorioArchivos() {
		return System.getProperty("directorioArchivos");
	}

	public String getTemaAplicacion() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.TEMA_APLICACION);
	}

	public String getRutaLoginAplicacion() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_LOGIN_APLICACION);
	}

	public String getTituloAplicacion() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.TITULO_APLICACION);
	}

	public String getIconoAplicacion() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_ICONO_APLICACION);
	}

	public String getLogoAplicacion() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_LOGO_APLICACION);
	}

	public String getMarcaAplicacion() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_MARCA_APLICACION);
	}

	public String getLogoAutenticado() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_LOGO_AUTENTICADO);
	}

	public String getBotonSalir() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_BOTON_SALIR);
	}

	public String getEstiloInicial() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_ESTILO_INICIAL);
	}

	public String getEstiloRegistro() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_ESTILO_REGISTRO);
	}

	public String getEstiloPrincipal() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_ESTILO_PRINCIPAL);
	}

	public String getJsPrincipal() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_JS_GENERAL);
	}

	public String getJsProteccion() {
		return this.getContextPath() + getValorConstanteConfiguracion(NombresConstantesMemoria.RUTA_JS_PROTECCION);
	}

	public String getMensajeNoAutenticado() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_NO_AUTENTICADO);
	}

	public String getMensajeNoAutorizado() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_NO_AUTORIZADO);
	}

	public String getMensajeNoDisponible() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_NO_DISPONIBLE);
	}

	public String getMensajeServicioNoDisponible() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_SERVICIO_NO_DISPONIBLE);
	}

	public String getMensajeSessionExpiro() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.MENSAJE_SESSION_EXPIRADA);
	}

	public boolean getPresentarsegmMenuHorizontalSuperiorCentro() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.PRESENTAR_SEGM_MENU_HORIZONTAL_SUPERIOR_CENTRO)
				.equals("S");
	}

	public boolean getPresentarSegmLogoSuperiorIzquierda() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.PRESENTAR_SEGM_LOGO_SUPERIOR_IZQUIERDA)
				.equals("S");
	}

	public boolean getPresentarSegmInformacionUsuarioSuperiorDerecha() {
		return getValorConstanteConfiguracion(
				NombresConstantesMemoria.PRESENTAR_SEGM_INFORMACION_USUARIO_SUPERIOR_DERECHA).equals("S");
	}

	public boolean getPresentarSegmInformacionVariosCentroIzquierda() {
		return getValorConstanteConfiguracion(
				NombresConstantesMemoria.PRESENTAR_SEGM_INFORMACION_VARIOS_CENTRO_IZQUIERDA).equals("S");
	}

	public boolean getPresentarSegmInformacionVariosCentroDerecha() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.PRESENTAR_SEGM_INFORMACION_VARIOS_CENTRO_DERECHA)
				.equals("S");
	}

	public boolean getPresentarSegmToolbarInferior() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.PRESENTAR_SEGM_TOOLBAR_INFERIOR).equals("S");
	}

	public String getNombreAplicacionServicios() {
		return getValorConstanteConfiguracion(NombresConstantesMemoria.PREFIJO_APLICACION_SERVICIOS);
	}

	public void logout() throws IOException {

		try {
			this.getHttpServletRequest().logout();
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.invalidateSession();

		PrimeFaces.current().executeScript("window.close();");

	}

	private boolean existeNumeroEnLista(List<String> lista, int numero) {
		boolean existe = false;
		for (String elemento : lista) {
			if (elemento.equals(String.valueOf(numero))) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	public String getTecladoRandom() {
		List<String> lista = new ArrayList<String>();

		while (lista.size() < 10) {
			Random random = new Random();
			int value = random.nextInt(10);
			// System.out.println(value);
			if (lista.size() == 0) {
				lista.add(String.valueOf(value));
			} else {
				if (!existeNumeroEnLista(lista, value)) {
					lista.add(String.valueOf(value));
				}
			}
			// System.out.println("tamaño " + lista.size());
		}

		String resultado = "";
		Integer cuenta = 0;
//		for (String elemento : lista) {
//			resultado += elemento;
//			cuenta++;
//			if (cuenta == 5)
//				resultado += ",";
//		}
//		resultado+=",-clear-close";
		
		for (String elemento : lista) {
			resultado += elemento;
			cuenta++;
			if (cuenta == 3)
				resultado += "-close,";
			else if(cuenta == 6)
				resultado += "-clear,";
			else if(cuenta == 9)
				resultado += "-back,";
		}
		
		return resultado;
	}

}
