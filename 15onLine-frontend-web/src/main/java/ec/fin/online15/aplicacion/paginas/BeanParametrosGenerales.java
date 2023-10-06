package ec.fin.online15.aplicacion.paginas;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoParametrosGenerales;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("administracionParametrosGeneralesMB")
@SessionScoped
public class BeanParametrosGenerales extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosMantenimientoParametrosGenerales serviciosMantenimientoParametrosGenerales;

	private TwebParametrosGenerales parametroGeneral;
	private List<TwebParametrosGenerales> parametrosGenerales;
	private boolean accionEliminar;

	public TwebParametrosGenerales getParametroGeneral() {
		return parametroGeneral;
	}

	public void setParametroGeneral(TwebParametrosGenerales parametroGeneral) {
		this.parametroGeneral = parametroGeneral;
	}

	public List<TwebParametrosGenerales> getParametrosGenerales() {
		return parametrosGenerales;
	}

	public void setParametrosGenerales(
			List<TwebParametrosGenerales> parametrosGenerales) {
		this.parametrosGenerales = parametrosGenerales;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	@PostConstruct
	private void init() {
		inicializacion();
	}

	public void inicializacion() {
		parametroGeneral = new TwebParametrosGenerales();
		parametrosGenerales = serviciosMantenimientoParametrosGenerales
				.getIServicioParametrosGeneralesWs().listaParametrosGenerales(
						this.getHttpSession().getId());
		accionEliminar = true;
	}

	public void actualizarParametroGeneral(ActionEvent evento) {
		try {
			if (parametroGeneral.getId() != null) {
				parametroGeneral.setFechaActualizacion(new Date());
				parametroGeneral.setActualizadoPor(this.getUsuarioAutenticado()
						.toString());
				serviciosMantenimientoParametrosGenerales
						.getIServicioParametrosGeneralesWs()
						.actualizarParametro(this.getHttpSession().getId(),
								parametroGeneral);
				this.configuracionesGenerales.mensajeTransaccionExitosa();
			}
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
