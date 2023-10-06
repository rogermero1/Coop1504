package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import ec.fin.online15.backend.consultas.modelo.entidades.clientes.IdentidadCliente;
import ec.fin.online15.backend.consultas.modelo.entidades.clientes.InformacionCliente;
import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebBloqueoPorValidacion;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebBloqueosPorValiacionEAO;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebConveniosEAO;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ServicioConvenio extends
		ServicioMantenimientoEntidad<TwebConvenios, Long>  {

	@EJB
	private TwebConveniosEAO twebConvenioEAO;

	@EJB
	private TwebBloqueosPorValiacionEAO twebBloqueoPorValidacionEAO;

	@Resource
	private SessionContext sessionContext;

	 
	protected EAOGenerico<TwebConvenios, Long> getEAO() {
		return twebConvenioEAO;
	}

	public TwebConvenios consultaConvenioWebCliente(String cedula,
			String convenioWeb) {
		List<TwebConvenios> listaConvenioWebCliente = this.twebConvenioEAO
				.consultaConvenioWebCliente(cedula, convenioWeb);
		TwebConvenios convenioWebCliente;
		if (!listaConvenioWebCliente.isEmpty()) {
			convenioWebCliente = listaConvenioWebCliente.get(0);
		} else {
			convenioWebCliente = new TwebConvenios();
			convenioWebCliente.setCodigoCliente(0);
			convenioWebCliente.setNombreCliente("NO ENCONTRADO");
		}
		return convenioWebCliente;
	}

	 
	public TwebConvenios validaDatosCliente(String cedula, String correo,
			String celular) {
		TwebConvenios convenioWebCliente = this.twebConvenioEAO
				.validaDatosCliente(cedula, correo, celular);
		if (convenioWebCliente.getCodigoCliente() == null) {
			convenioWebCliente.setCodigoCliente(0);
			convenioWebCliente.setNombreCliente("NO ENCONTRADO");
		}
		return convenioWebCliente;
	}

	 
	public TwebConvenios validaIdentidadCliente(String ip, String cedula,
			String cuenta, Integer anioNacimiento, Date fechaUltimaTransaccion,
			Boolean tieneCredito, Boolean tieneInversion,
			Boolean recibeTransferencia,
			List<Integer> listaPreguntasSegundaValidacion,
			Integer intentosParaBloquear) {

		UserTransaction cx = sessionContext.getUserTransaction();
		TwebConvenios convenioWebCliente = new TwebConvenios();

		try {

			System.out.println("Validacion de identidad de cliente:");
			System.out.println("ip " + ip);
			System.out.println("cedula " + cedula);
			System.out.println("cuenta " + cuenta);
			System.out.println("anioNacimiento" + anioNacimiento);
			System.out.println("fechaUltimaTransaccion "
					+ fechaUltimaTransaccion);
			System.out.println("tieneCredito " + tieneCredito);
			System.out.println("tieneInversion " + tieneInversion);
			System.out.println("recibeTransferencia " + recibeTransferencia);

			IdentidadCliente cliente = this.twebConvenioEAO
					.validaIdentidadCliente(cedula, cuenta, anioNacimiento,
							fechaUltimaTransaccion, tieneCredito,
							tieneInversion, recibeTransferencia);

			if (cliente != null) {
				System.out.println("Preguntas validacion "
						+ listaPreguntasSegundaValidacion);
				System.out.println("Resultado validacion: "
						+ cliente.getCodigoCliente() + " "
						+ cliente.getNombre() + " "
						+ cliente.getRespuestaCuenta() + " "
						+ cliente.getRespuestaAnioNacimiento() + " "
						+ cliente.getRespuestaUltimaTransaccion() + " "
						+ cliente.getRespuestaPrestamo() + " "
						+ cliente.getRespuestaInversion() + " "
						+ cliente.getRespuestaTransferencia());

				convenioWebCliente.setCodigoCliente(cliente.getCodigoCliente());
				convenioWebCliente.setNombreCliente(cliente.getNombre());

				// validad cuenta
				if (cliente.getRespuestaCuenta().equals("N")) {
					convenioWebCliente.setCodigoCliente(0);
					convenioWebCliente
							.setNombreCliente("No se puede validar CUENTA");
					System.out.println(convenioWebCliente.getNombreCliente());
				}
				// valida respuestas
				for (Integer i : listaPreguntasSegundaValidacion) {
					if (i == 1) {
						if (cliente.getRespuestaAnioNacimiento().equals("N")) {
							convenioWebCliente.setCodigoCliente(0);
							convenioWebCliente
									.setNombreCliente("No se puede validar ANIO NACIMIENTO");
							System.out.println(convenioWebCliente
									.getNombreCliente());
							break;
						}
					} else if (i == 2) {
						if (cliente.getRespuestaUltimaTransaccion().equals("N")) {
							convenioWebCliente.setCodigoCliente(0);
							convenioWebCliente
									.setNombreCliente("No se puede validar ULTIMA TRANSACCION");
							System.out.println(convenioWebCliente
									.getNombreCliente());
							break;
						}
					} else if (i == 3) {
						if (cliente.getRespuestaPrestamo().equals("N")) {
							convenioWebCliente.setCodigoCliente(0);
							convenioWebCliente
									.setNombreCliente("No se puede validar CREDITOS VIGENTES");
							System.out.println(convenioWebCliente
									.getNombreCliente());
							break;
						}
					} else if (i == 4) {
						if (cliente.getRespuestaInversion().equals("N")) {
							convenioWebCliente.setCodigoCliente(0);
							convenioWebCliente
									.setNombreCliente("No se puede validar INVERSIONES");
							System.out.println(convenioWebCliente
									.getNombreCliente());
							break;
						}
					} else {
						if (cliente.getRespuestaTransferencia().equals("N")) {
							convenioWebCliente.setCodigoCliente(0);
							convenioWebCliente
									.setNombreCliente("No se puede validar TRANSFERENCIAS");
							System.out.println(convenioWebCliente
									.getNombreCliente());
							break;
						}
					}
				}

				// control bloqueo
				if (convenioWebCliente.getCodigoCliente() == 0) {
					cx.begin();
					TwebBloqueoPorValidacion bloqueo = twebBloqueoPorValidacionEAO
							.obtenerBloqueoActual(cliente.getCodigoCliente());
					// si no existe crea
					if (bloqueo == null) {
						bloqueo = new TwebBloqueoPorValidacion();
						bloqueo.setCodigoCliente(cliente.getCodigoCliente());
						bloqueo.setIp(ip);
						bloqueo.setIntentos(1);
						bloqueo.setEstado("A");
						bloqueo.setRegistradoPor("REGISTRO");
						bloqueo.setFechaRegistro(new Date());
						convenioWebCliente.setConvenioWeb(bloqueo.getIntentos()
								.toString());
						twebBloqueoPorValidacionEAO.crear(bloqueo);
					} else {
						bloqueo.setIntentos(bloqueo.getIntentos() + 1);
						bloqueo.setIp(ip);
						bloqueo.setFechaActualizacion(new Date());
						convenioWebCliente.setConvenioWeb(bloqueo.getIntentos()
								.toString());
						twebBloqueoPorValidacionEAO.actualizar(bloqueo);
					}
					cx.commit();
				}

			} else {
				convenioWebCliente.setCodigoCliente(0);
				convenioWebCliente.setNombreCliente("NO ENCONTRADO");
			}
			return convenioWebCliente;
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

			return convenioWebCliente;
		}
	}

	 
	public TwebConvenios obtenerConvenio(String convenioWeb,
			Integer codigoCliente) {
		return this.twebConvenioEAO.obtenerConvenio(convenioWeb, codigoCliente);
	}

	 
	public InformacionCliente consultaCliente(String cedula) {
		List<InformacionCliente> listaInformacionCliente = this.twebConvenioEAO
				.consultaCliente(cedula);
		InformacionCliente informacionCliente;
		if (!listaInformacionCliente.isEmpty()) {
			informacionCliente = listaInformacionCliente.get(0);
		} else {
			informacionCliente = new InformacionCliente(0, "NO ENCONTRADO",
					"NO REGISTRA", "NO REGISTRA");
		}
		return informacionCliente;
	}

	 
	public String registrarConvenio(TwebConvenios convenio) {
		String numeroConvenio = this.twebConvenioEAO.generacionNumeroConvenio();
		if (numeroConvenio != null) {
			convenio.setConvenioWeb(numeroConvenio);
			this.twebConvenioEAO.crear(convenio);
		}
		return numeroConvenio;
	}

	 
	public List<TwebConvenios> listaConveniosVigentes() {
		return this.twebConvenioEAO.conveniosVigentes();
	}

	 
	public void actualizarConvenio(TwebConvenios convenio) {
		this.twebConvenioEAO.actualizar(convenio);
	}

}
