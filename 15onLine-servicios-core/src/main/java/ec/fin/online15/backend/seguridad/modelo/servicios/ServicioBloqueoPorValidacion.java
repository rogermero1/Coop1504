package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebBloqueoPorValidacion;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebBloqueosPorValiacionEAO;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioBloqueoPorValidacion extends
		ServicioMantenimientoEntidad<TwebBloqueoPorValidacion, Long> 
		 {

	@EJB
	private TwebBloqueosPorValiacionEAO twebBloqueoPorValidacionEAO;

	@Resource
	private SessionContext sessionContext;

	 
	protected EAOGenerico<TwebBloqueoPorValidacion, Long> getEAO() {
		return this.twebBloqueoPorValidacionEAO;
	}

	 
	public List<TwebBloqueoPorValidacion> listaBloqueosVigentes() {
		return this.twebBloqueoPorValidacionEAO.bloqueosVigentes();
	}

	 
	public TwebBloqueoPorValidacion bloqueoActual(Integer codigoCliente) {
		return this.twebBloqueoPorValidacionEAO
				.obtenerBloqueoActual(codigoCliente);
	}

	 
	public Integer actualizacionBloqueo(TwebBloqueoPorValidacion bloqueo) {
		UserTransaction cx = sessionContext.getUserTransaction();
		try {
			cx.begin();
			this.twebBloqueoPorValidacionEAO.actualizar(bloqueo);
			cx.commit();
			return 1;
		} catch (Exception e) {
			try {
				cx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return 0;
		}
	}

}
