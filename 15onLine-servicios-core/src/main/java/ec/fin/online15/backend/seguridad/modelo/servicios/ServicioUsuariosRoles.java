package ec.fin.online15.backend.seguridad.modelo.servicios;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOGenerico;
import ec.fin.online15.backend.librerias.servicios.ServicioMantenimientoEntidad;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebRol;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuario;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebUsuarioRol;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebRolEAO;
import ec.fin.online15.backend.seguridad.modelo.repositorios.TwebUsuariosRolesEAO;

@Stateless
public class ServicioUsuariosRoles extends ServicioMantenimientoEntidad<TwebUsuarioRol, Long> {

	@EJB
	private TwebUsuariosRolesEAO usuarioRolEAO;

	@EJB
	private TwebRolEAO twebRolEAO;

	@Override
	protected EAOGenerico<TwebUsuarioRol, Long> getEAO() {
		return usuarioRolEAO;
	}

	public void inactivarRolesUsuario(TwebUsuario usuario) {
		usuarioRolEAO.inactivarRolesUsuario(usuario);
	}

	public List<TwebUsuarioRol> rolesUsuario(Long idUsuario) {
		return this.usuarioRolEAO.rolesUsuario(idUsuario);
	}

	public Integer registrarUsuarioTester(TwebUsuario usuario, String usuarioTransaccion) {
		try {
			TwebRol rolTest = new TwebRol();
			rolTest = this.twebRolEAO.buscarPorId(5L);

			TwebUsuarioRol usuarioRol = new TwebUsuarioRol();
			usuarioRol.setUsuario(usuario);
			usuarioRol.setRol(rolTest);
			usuarioRol.setRegistradoPor(usuarioTransaccion);
			usuarioRol.setEstado("A");
			usuarioRol.setFechaRegistro(new Date());
			usuarioRol.setObservacion("CREACION DE USUARIO");
			usuarioRol.setDescripcion(rolTest.getObservacion());
			this.usuarioRolEAO.crear(usuarioRol);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Integer anularUsuarioTester(TwebUsuario usuario, String usuarioTransaccion) {
		try {
			TwebUsuarioRol usuarioRol = new TwebUsuarioRol();
			usuarioRol = this.usuarioRolEAO.obtenerRolUsuarioTest(usuario.getId());
			if (usuarioRol != null) {
				usuarioRol.setActualizadoPor(usuarioTransaccion);
				usuarioRol.setFechaActualizacion(new Date());
				usuarioRol.setEstado("I");
				this.usuarioRolEAO.actualizar(usuarioRol);
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
