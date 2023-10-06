package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosGenerales;

@Stateless
@LocalBean
public class TwebParametrosGeneralesEAO extends
		EAOSeed<TwebParametrosGenerales, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebParametrosGenerales> listaParametrosGenerales() {
		Query query = getEntityManager()
				.createNativeQuery(
						"select * from tweb_parametros_generales where estado = 'A' order by 1",
						TwebParametrosGenerales.class);
		return query.getResultList();
	}

	public Date consultaFechaValida(String modulo) {
		SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
		Query query = getEntityManager().createNativeQuery(
				"SELECT Fecha_Hoy " + "FROM   Mg_Calendario "
						+ "WHERE  Codigo_Empresa = 1 "
						+ "AND    Codigo_Aplicacion = ?");
		query.setParameter(1, modulo);
		@SuppressWarnings("unchecked")
		List<Date> resultado = query.getResultList();
		if (resultado.isEmpty()) {
			return null;
		} else {
			try {
				return formatoDeFecha.parse(resultado.get(0).toString());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
