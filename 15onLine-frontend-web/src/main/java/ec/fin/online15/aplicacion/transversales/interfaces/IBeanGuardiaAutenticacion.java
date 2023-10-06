package ec.fin.online15.aplicacion.transversales.interfaces;

public interface IBeanGuardiaAutenticacion {

	public boolean estaAutenticado();

	public void logout();

	public void redireccionarMensajeNoAutenticado();

	public void invalidarSession();
}
