package ec.fin.online15.portal.paginas.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.DetalleCuotaPrestamo;
import ec.fin.online15.integracion.beans.BeanServiciosPosicionConsolidada;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("detallePosicionConsolidadaMB")
@RequestScoped
public class BeanDetallePosicionConsolidada extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	private List<DetalleCuotaPrestamo> listadoDetalleCuota;
	// private CuotasPrestamo cuotaSeleccionada;

	@Inject
	private BeanServiciosPosicionConsolidada servicioPosicionConsolidada;

	private Integer cuotaSeleccionada;

	// @PostConstruct
	// private void init() {
	// this.inicializar();
	// }

	// private void inicializar() {
	// cuotaSeleccionada = new CuotasPrestamo();
	// }

	public void detalleCuotaPrestamo(ActionEvent evento) {
		listadoDetalleCuota = new ArrayList<DetalleCuotaPrestamo>();

		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

		System.out.println(params.get("paramNumeroCuota"));
		System.out.println(params.get("paramNumeroPrestamo"));

		this.cuotaSeleccionada = Integer.parseInt(params.get("paramNumeroCuota"));

		listadoDetalleCuota = servicioPosicionConsolidada.getIServicioPosicionConsolidadaWs()
				.consultaPosicionConsolidadaDetalleCuotasPrestamo(this.getHttpSession().getId(),
						Integer.parseInt(params.get("paramNumeroCuota")),
						Long.parseLong(params.get("paramNumeroPrestamo")));

	}

	public List<DetalleCuotaPrestamo> getListadoDetalleCuota() {
		return listadoDetalleCuota;
	}

	public void setListadoDetalleCuota(List<DetalleCuotaPrestamo> listadoDetalleCuota) {
		this.listadoDetalleCuota = listadoDetalleCuota;
	}

	public Integer getCuotaSeleccionada() {
		return cuotaSeleccionada;
	}

	public void setCuotaSeleccionada(Integer cuotaSeleccionada) {
		this.cuotaSeleccionada = cuotaSeleccionada;
	}

	/*
	 * public CuotasPrestamo getCuotaSeleccionada() { return cuotaSeleccionada; }
	 * 
	 * public void setCuotaSeleccionada(CuotasPrestamo cuotaSeleccionada) {
	 * this.cuotaSeleccionada = cuotaSeleccionada; }
	 */
}
