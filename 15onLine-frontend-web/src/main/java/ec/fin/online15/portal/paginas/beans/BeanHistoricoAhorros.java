package ec.fin.online15.portal.paginas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.UltimosMovimientosAhorro;
import ec.fin.online15.integracion.beans.BeanServiciosPosicionConsolidada;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("historicoAhorrosMB")
@SessionScoped
public class BeanHistoricoAhorros extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanServiciosPosicionConsolidada servicioPosicionConsolidada;

	@Inject
	private BeanConsultaPosicionConsolidada posicionConsolidada;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	private Date fechaDesde;
	private Date fechaHasta;
	private List<UltimosMovimientosAhorro> movimientos;

	@PostConstruct
	public void init() {
		this.inicializa();
	}

	public void inicializa() {
		this.fechaDesde = null;
		this.fechaHasta = null;
		this.movimientos = new ArrayList<UltimosMovimientosAhorro>();
	}

	public Date getFechaDesde() {
		this.inicializa();
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public List<UltimosMovimientosAhorro> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<UltimosMovimientosAhorro> movimientos) {
		this.movimientos = movimientos;
	}

	public void consultar(ActionEvent evento) {
		if (this.fechaDesde == null || this.fechaHasta == null) {
			this.aniadirMensajeError(
					this.configuracionesGenerales.getTituloAplicacion(),
					"Seleccione las fechas");
		} else {
			this.movimientos = this.servicioPosicionConsolidada
					.getIServicioPosicionConsolidadaWs()
					.consultaHistoricoAhorro(
							this.getHttpSession().getId(),
							this.posicionConsolidada.getCuentaSeleccionada()
									.getNumeroCuenta(), this.fechaDesde,
							this.fechaHasta);
		}
	}
}
