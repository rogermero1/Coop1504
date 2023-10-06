package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebParametrosSolicitud;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebParametrosSolicitudEAO;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioParametrosSolicitud extends
		ServicioMantenimientoEntidad<TwebParametrosSolicitud, Long>  {

	@EJB
	private TwebParametrosSolicitudEAO twebParametrosSolicitudEAO;

	
	protected EAOGenerico<TwebParametrosSolicitud, Long> getEAO() {
		return this.twebParametrosSolicitudEAO;
	}

	
	public List<TwebParametrosSolicitud> listaParametrosVigentes() {
		return twebParametrosSolicitudEAO.parametrosVigentes();
	}

	
	public TwebParametrosSolicitud buscaParametro(String relacionDependencia,
			String preaprobado, Double monto) {
		return twebParametrosSolicitudEAO.buscaParametro(relacionDependencia, preaprobado, monto);
	}

}
