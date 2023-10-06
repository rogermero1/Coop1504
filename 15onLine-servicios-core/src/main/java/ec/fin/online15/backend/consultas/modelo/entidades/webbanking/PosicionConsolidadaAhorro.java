package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PosicionConsolidadaAhorro implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoAplicacion;
	private Integer codigoSubAplicacion;
	private String numeroCuenta;
	private Integer codigoMoneda;
	private String descripcionMoneda;
	private String abreviaturaMoneda;
	private Double saldoTotalLinea;
	private Double saldoReservaLinea;
	private Double saldoDisponible;
	private Double saldoBloqueado;
	private String nombreCuenta;
	private String claseCuenta;

/* 	public PosicionConsolidadaAhorro() {
	}

	public PosicionConsolidadaAhorro(String codigoAplicacion,
			Integer codigoSubAplicacion, String numeroCuenta,
			Integer codigoMoneda, String descripcionMoneda,
			String abreviaturaMoneda, Double saldoTotalLinea,
			Double saldoReservaLinea, Double saldoDisponible,
			Double saldoBloqueado, String nombreCuenta, String claseCuenta) {
		super();
		this.codigoAplicacion = codigoAplicacion;
		this.codigoSubAplicacion = codigoSubAplicacion;
		this.numeroCuenta = numeroCuenta;
		this.codigoMoneda = codigoMoneda;
		this.descripcionMoneda = descripcionMoneda;
		this.abreviaturaMoneda = abreviaturaMoneda;
		this.saldoTotalLinea = saldoTotalLinea;
		this.saldoReservaLinea = saldoReservaLinea;
		this.saldoDisponible = saldoDisponible;
		this.saldoBloqueado = saldoBloqueado;
		this.nombreCuenta = nombreCuenta;
		this.claseCuenta = claseCuenta;
	}

	public String getCodigoAplicacion() {
		return codigoAplicacion;
	}

	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	public Integer getCodigoSubAplicacion() {
		return codigoSubAplicacion;
	}

	public void setCodigoSubAplicacion(Integer codigoSubAplicacion) {
		this.codigoSubAplicacion = codigoSubAplicacion;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Integer getCodigoMoneda() {
		return codigoMoneda;
	}

	public void setCodigoMoneda(Integer codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}

	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}

	public String getAbreviaturaMoneda() {
		return abreviaturaMoneda;
	}

	public void setAbreviaturaMoneda(String abreviaturaMoneda) {
		this.abreviaturaMoneda = abreviaturaMoneda;
	}

	public Double getSaldoTotalLinea() {
		return saldoTotalLinea;
	}

	public void setSaldoTotalLinea(Double saldoTotalLinea) {
		this.saldoTotalLinea = saldoTotalLinea;
	}

	public Double getSaldoReservaLinea() {
		return saldoReservaLinea;
	}

	public void setSaldoReservaLinea(Double saldoReservaLinea) {
		this.saldoReservaLinea = saldoReservaLinea;
	}

	public Double getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public Double getSaldoBloqueado() {
		return saldoBloqueado;
	}

	public void setSaldoBloqueado(Double saldoBloqueado) {
		this.saldoBloqueado = saldoBloqueado;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getClaseCuenta() {
		return claseCuenta;
	}

	public void setClaseCuenta(String claseCuenta) {
		this.claseCuenta = claseCuenta;
	} */

}
