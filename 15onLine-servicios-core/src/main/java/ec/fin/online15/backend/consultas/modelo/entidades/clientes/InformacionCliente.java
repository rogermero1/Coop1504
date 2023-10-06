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
public class InformacionCliente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer codigoCliente;
	private String nombreCliente;
	private String direccion;
	private String telefonos;

	/*
	 * public InformacionCliente() { }
	 * 
	 * public InformacionCliente(Integer codigoCliente, String nombreCliente, String
	 * direccion, String telefonos) { super(); this.codigoCliente = codigoCliente;
	 * this.nombreCliente = nombreCliente; this.direccion = direccion;
	 * this.telefonos = telefonos; }
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
	 * 
	 * public String getDireccion() { return direccion; }
	 * 
	 * public void setDireccion(String direccion) { this.direccion = direccion; }
	 * 
	 * public String getTelefonos() { return telefonos; }
	 * 
	 * public void setTelefonos(String telefonos) { this.telefonos = telefonos; }
	 */

}
