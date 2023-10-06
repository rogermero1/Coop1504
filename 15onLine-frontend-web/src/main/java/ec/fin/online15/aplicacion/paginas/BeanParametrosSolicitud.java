package ec.fin.online15.aplicacion.paginas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosSolicitud;
import ec.fin.online15.integracion.beans.BeanServiciosParametrosSolicitud;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("parametrosSolicitudMB")
@SessionScoped
public class BeanParametrosSolicitud extends BaseManagedBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosParametrosSolicitud servicioParametros;

	private List<TwebParametrosSolicitud> listaParametros;
	private TwebParametrosSolicitud parametro;

	private boolean accionEliminar;

	@PostConstruct
	public void init() {
		inicializacion();
	}

	public void inicializacion() {
		parametro = new TwebParametrosSolicitud();
		parametro.setId(null);
		listaParametros = servicioParametros
				.getIServicioParametrosSolicitudWs().listaParametrosVigentes(
						this.getHttpSession().getId());
		// System.out.println("tamañooooooo " + listaParametros.size());
		accionEliminar = true;
	}

	public List<TwebParametrosSolicitud> getListaParametros() {
		return listaParametros;
	}

	public void setListaParametros(List<TwebParametrosSolicitud> listaParametros) {
		this.listaParametros = listaParametros;
	}

	public TwebParametrosSolicitud getParametro() {
		return parametro;
	}

	public void setParametro(TwebParametrosSolicitud parametro) {
		this.parametro = parametro;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public void creaParametroMB(ActionEvent evento) {
		if (parametro.getId() != null) {
			System.out.println("Id para actualizar " + parametro.getId());
			System.out.println("actualizacion de parametro Solicitud");
			this.actualizaParametro();
		} else {
			System.out.println("creacion de parametro Solicitud ");
			this.creaParametro();
		}
	}

	private void creaParametro() {
		try {
			this.parametro.setEstado("A");
			this.parametro.setFechaRegistro(new Date());
			this.parametro.setRegistradoPor(this.getUsuarioAutenticado()
					.toString());
			servicioParametros.getIServicioParametrosSolicitudWs()
					.crearParametro(this.getHttpSession().getId(),
							this.parametro);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	private void actualizaParametro() {
		try {
			this.parametro.setEstado("A");
			this.parametro.setFechaActualizacion(new Date());
			this.parametro.setActualizadoPor(this.getUsuarioAutenticado()
					.toString());
			servicioParametros.getIServicioParametrosSolicitudWs()
					.actualizarParametro(this.getHttpSession().getId(),
							this.parametro);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} catch (Exception e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void eliminaParametro(ActionEvent event) throws ExcepcionAplicacion {
		try {
			this.parametro.setFechaActualizacion(new Date());
			this.parametro.setActualizadoPor(this.getUsuarioAutenticado()
					.toString());
			this.parametro.setEstado("I");
			System.out.println("Eliminar parametro");
			servicioParametros.getIServicioParametrosSolicitudWs()
					.actualizarParametro(this.getHttpSession().getId(),
							this.parametro);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.accionEliminar = false;
	}

	public void onRowUnselect(UnselectEvent event) {
		this.accionEliminar = true;
	}

}
