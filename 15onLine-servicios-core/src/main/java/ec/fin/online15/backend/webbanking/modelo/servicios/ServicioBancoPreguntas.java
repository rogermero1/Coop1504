package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebBancoPregunta;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebBancoPreguntaEAO;

@Stateless
public class ServicioBancoPreguntas extends
		ServicioMantenimientoEntidad<TwebBancoPregunta, Long> {

	@EJB
	private TwebBancoPreguntaEAO bancoPreguntaEAO;

	public List<TwebBancoPregunta> listadoBancoPreguntas() {
		return bancoPreguntaEAO.listaBancoPreguntas();
	}

	@Override
	protected EAOGenerico<TwebBancoPregunta, Long> getEAO() {
		return bancoPreguntaEAO;
	}

	public List<TwebBancoPregunta> listaPreguntasRegistro() {
		return bancoPreguntaEAO.listaPreguntasRegistro();
	}

}
