package ec.fin.online15.backend.seguridad.modelo.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebHistoricoClaves;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebHistoricoClavesEAO;

@Stateless
public class ServicioHistoricoClave extends
		ServicioMantenimientoEntidad<TwebHistoricoClaves, Long>  {

	@EJB
	private TwebHistoricoClavesEAO twebHistoricoClaveEAO;

	@Override
	protected EAOGenerico<TwebHistoricoClaves, Long> getEAO() {
		return this.twebHistoricoClaveEAO;
	}

	
	public boolean verificaHistoricoClave(TwebUsuario usuario, Integer limite,
			String clave) {
		boolean control = true;

		for (TwebHistoricoClaves historico : this.twebHistoricoClaveEAO
				.historicoClaveUsuario(usuario, limite)) {
			if (historico.getClave().equals(clave)) {
				control = false;
				break;
			}
		}

		return control;
	}
}
