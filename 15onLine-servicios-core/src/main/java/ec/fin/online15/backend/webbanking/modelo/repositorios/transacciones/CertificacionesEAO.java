package ec.fin.online15.backend.webbanking.modelo.repositorios.transacciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.TiposCertificacion;
import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.Certificacion;
import ec.fin.online15.backend.procedimientosalmacenados.modelo.entidades.CertificacionSP;

@SuppressWarnings("rawtypes")
@Stateless
@LocalBean
public class CertificacionesEAO extends EAOSeed {

	@SuppressWarnings("unchecked")
	public List<TiposCertificacion> listaCertificaciones() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT Codigo_Tipo_Certificacion, "
								+ "       Descripcion, "
								+ "       Codigo_Tipo_Transaccion_Debita, "
								+ "       Nvl((SELECT Cargo_Minimo "
								+ "           FROM   Mg_Transacciones_Especiales "
								+ "           WHERE  Codigo_Tipo_Transaccion = x.Codigo_Tipo_Transaccion_Debita), "
								+ "           0) Valor "
								+ "FROM   Mg_Tipo_Certificaciones x");
		List<TiposCertificacion> listaCertificaciones = new ArrayList<TiposCertificacion>();
		List<Object[]> listaResultado = query.getResultList();
		for (Object[] elementoResultado : listaResultado) {
			TiposCertificacion certificado = new TiposCertificacion(
					((BigDecimal) elementoResultado[0]).intValue(),
					(String) elementoResultado[1],
					((BigDecimal) elementoResultado[2]).intValue(),
					((BigDecimal) elementoResultado[3]).doubleValue());
			listaCertificaciones.add(certificado);
		}
		return listaCertificaciones;
	}

	public Certificacion solicitarCertificacion(Certificacion certificacion) {
		CertificacionSP procedimiento = new CertificacionSP(certificacion);
		this.invocacionJDBC(procedimiento);
		return procedimiento.getCertificacion();
	}

}
