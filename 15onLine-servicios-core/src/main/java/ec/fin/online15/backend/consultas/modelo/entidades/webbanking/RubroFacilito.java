package ec.fin.online15.backend.consultas.modelo.entidades.webbanking;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RubroFacilito implements Serializable {

	private static final long serialVersionUID = 1L;

	private String identidad;
	private String producto;
	private String nombre;
	private String tipoTransaccion;
	private String tipoPago;
	private String tituloReferencia;
	private String valores;
	private String pagoEspecial;

	/*
	 * public RubroFacilito(String identidad, String producto, String nombre, String
	 * tipoTransaccion, String tipoPago, String tituloReferencia, String valores,
	 * String pagoEspecial) { super(); this.identidad = identidad; this.producto =
	 * producto; this.nombre = nombre; this.tipoTransaccion = tipoTransaccion;
	 * this.tipoPago = tipoPago; this.tituloReferencia = tituloReferencia;
	 * this.valores = valores; this.pagoEspecial = pagoEspecial; }
	 * 
	 * public RubroFacilito() { }
	 * 
	 * public String getIdentidad() { return identidad; }
	 * 
	 * public void setIdentidad(String identidad) { this.identidad = identidad; }
	 * 
	 * public String getProducto() { return producto; }
	 * 
	 * public void setProducto(String producto) { this.producto = producto; }
	 * 
	 * public String getNombre() { return nombre; }
	 * 
	 * public void setNombre(String nombre) { this.nombre = nombre; }
	 * 
	 * public String getTipoTransaccion() { return tipoTransaccion; }
	 * 
	 * public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion
	 * = tipoTransaccion; }
	 * 
	 * public String getValores() { return valores; }
	 * 
	 * public void setValores(String valores) { this.valores = valores; }
	 * 
	 * public String getTipoPago() { return tipoPago; }
	 * 
	 * public void setTipoPago(String tipoPago) { this.tipoPago = tipoPago; }
	 * 
	 * public String getTituloReferencia() { return tituloReferencia; }
	 * 
	 * public void setTituloReferencia(String tituloReferencia) {
	 * this.tituloReferencia = tituloReferencia; }
	 * 
	 * public String getPagoEspecial() { return pagoEspecial; }
	 * 
	 * public void setPagoEspecial(String pagoEspecial) { this.pagoEspecial =
	 * pagoEspecial; }
	 */

}
