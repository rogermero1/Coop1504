package ec.fin.online15.backend.webbanking.modelo.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.consultas.modelo.entidades.webbanking.ListadoOpciones;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebPerfilTransaccional;
import ec.fin.online15.backend.webbanking.modelo.repositorios.TwebPerfilTransaccionalEAO;

@Stateless
public class ServicioPerfilTransaccional   {


	@EJB
	private TwebPerfilTransaccionalEAO perfilClienteEAO;

	public List<TwebPerfilTransaccional> listaPerfilCliente(
			Integer codigoCliente) {
		return perfilClienteEAO
				.consultaPerfilTransaccionalCliente(codigoCliente);
	}

	public void actualizaPerfilCliente(TwebPerfilTransaccional perfilCliente) {
		perfilClienteEAO.actualizar(perfilCliente);
	}

	public List<TwebPerfilTransaccional> consultaPerfilCanalTransaccionalCliente(
			Integer codigoCliente, Integer idCanalElectronico) {
		return perfilClienteEAO.consultaPerfilCanalTransaccionalCliente(
				codigoCliente, idCanalElectronico);
	}

	public List<TwebPerfilTransaccional> consultaCuposTransaccionalInicial(
			Integer codigoCliente) {
		return perfilClienteEAO
				.consultaCuposTransaccionalInicial(codigoCliente);
	}

	public List<ListadoOpciones> listaCanalesElectronicos() {
		return perfilClienteEAO.listaCanalesElectronicos();
	}

	public List<TwebPerfilTransaccional> consultaPerfilTransaccionesAcumuladas(
			Integer codigoCliente, Integer idTipoCanal) {
		return perfilClienteEAO.consultaPerfilTransaccionesAcumuladas(
				codigoCliente, idTipoCanal);
	}
}
