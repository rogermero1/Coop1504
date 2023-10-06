package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.TiposCertificacion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.Certificacion;
import ec.fin.online15.backend.webbanking.modelo.repositorios.transacciones.CertificacionesEAO;

@Stateless
public class ServicioCertificacion {

	@EJB
	CertificacionesEAO certificacion;

	public List<TiposCertificacion> listaCertificaciones() {
		return this.certificacion.listaCertificaciones();
	}

	public Certificacion solicitarCertificacion(Certificacion certificacion) {
		return this.certificacion.solicitarCertificacion(certificacion);
	}

}
