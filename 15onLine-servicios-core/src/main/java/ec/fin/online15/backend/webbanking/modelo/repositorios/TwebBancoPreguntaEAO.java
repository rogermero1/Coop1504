package ec.fin.online15.backend.webbanking.modelo.repositorios;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebBancoPregunta;

@Stateless
@LocalBean
public class TwebBancoPreguntaEAO extends EAOSeed<TwebBancoPregunta, Long> {

	@SuppressWarnings("unchecked")
	public List<TwebBancoPregunta> listaBancoPreguntas() {
		Query query = getEntityManager()
				.createNativeQuery(
						"select * from tweb_banco_preguntas where estado = 'A' order by 1",
						TwebBancoPregunta.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TwebBancoPregunta> listaPreguntasRegistro() {
		Query query = getEntityManager()
				.createNativeQuery(
						"SELECT x.*"
								+ " FROM   (SELECT *"
								+ "         FROM   Tweb_Banco_Preguntas"
								+ "         WHERE  Estado = 'A'"
								+ "         ORDER  BY Dbms_Random.Value) x"
								+ " WHERE  Rownum <= (SELECT Preguntas_Obligatorias FROM Tweb_Parametros_Generales)",
						TwebBancoPregunta.class);
		return query.getResultList();
	}

	/*
	 * public void inactivarPregunta(TwebBancoPregunta bacncoPregunta) { String
	 * lsQuery = " update tweb_banco_preguntas b " +
	 * " set b.estado = 'I', b.fecha_actualizacion = sysdate " +
	 * " where b.id = ?"; Query query =
	 * getEntityManager().createNativeQuery(lsQuery); query.setParameter(1,
	 * bacncoPregunta.getId()); query.executeUpdate(); }
	 */
}
