package ec.fin.online15.backend.consultas.modelo.entidades.clientes;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvenioWebCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private String nombreCliente;

	/*
	 * public ConvenioWebCliente() { }
	 * 
	 * public ConvenioWebCliente(Integer codigoCliente, String nombreCliente) {
	 * this.codigoCliente = codigoCliente; this.nombreCliente = nombreCliente; }
	 * 
	 * public Integer getCodigoCliente() { return codigoCliente; }
	 * 
	 * public void setCodigoCliente(Integer codigoCliente) { this.codigoCliente =
	 * codigoCliente; }
	 * 
	 * public String getNombreCliente() { return nombreCliente; }
	 * 
	 * public void setNombreCliente(String nombreCliente) { this.nombreCliente =
	 * nombreCliente; }
	 */

}
