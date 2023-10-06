package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebTiposTransaccion;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebTiposTransaccionEAO;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebOperacionesFrecuentes;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebOperacionesFrecuentesEAO;

@Stateless
public class ServicioOperacionesFrecuentes extends
		ServicioMantenimientoEntidad<TwebOperacionesFrecuentes, Long>  {

	@EJB
	private TwebOperacionesFrecuentesEAO twebOperacionesFrecuentesEAO;

	@EJB
	private TwebTiposTransaccionEAO twebTiposTransaccionEAO;

	
	protected EAOGenerico<TwebOperacionesFrecuentes, Long> getEAO() {
		return this.twebOperacionesFrecuentesEAO;
	}

	
	public List<TwebOperacionesFrecuentes> operacionesFrecuentesPorUsuario(
			TwebUsuario usuario, Integer tipoTransaccion) {
		return this.twebOperacionesFrecuentesEAO
				.operacionesFrecuentesPorUsuario(usuario, tipoTransaccion);
	}

	
	public List<TwebTiposTransaccion> tiposTransaccion() {
		return this.twebTiposTransaccionEAO.listaTransaccionesVigentes();
	}

	
	public Integer guardarOperacion(TwebOperacionesFrecuentes operacionFrecuente) {
		try {
			// System.out.println("LLEGA A GUARDAR");
			// System.out.println("TIPO: "
			// + operacionFrecuente.getTipoTransaccion().getId() + " "
			// + operacionFrecuente.getTipoTransaccion().getObservacion());
			this.twebOperacionesFrecuentesEAO.crear(operacionFrecuente);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
	public Integer actualizarOperacion(
			TwebOperacionesFrecuentes operacionFrecuente) {
		try {
			this.twebOperacionesFrecuentesEAO.actualizar(operacionFrecuente);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
