package ec.fin.online15.loginmodule;

import java.util.List;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionLogin;

public interface IServiciosLoginModule {
	public String getClaveUsuario(String pUsuario);

	public List<String> getNombreRoles(String pUsuario);

	public boolean validarUsuarioPassword(String pUsuario, String pPassword) throws ExcepcionLogin;

}
