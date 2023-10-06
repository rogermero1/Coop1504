package ec.fin.online15.aplicacion.paginas;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGestorMenuSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGestorMenu;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@Named("menuMB")
@SessionScoped
public class BeanMenu extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private @QualificadorGestorMenuSimple
	IBeanGestorMenu beanGestorMenu;

	@Inject
	private BeanConfiguracionesGenerales beanConfiguracionesGeneral;

	private String rutaOpcion;

	private String urlOpcion;

	private MenuModel modeloMenu;

	@PostConstruct
	public void init() {
		modeloMenu = beanGestorMenu.getMenu();
	}

	public MenuModel getModeloMenu() {
		return modeloMenu;
	}

	public void setModeloMenu(MenuModel modeloMenu) {
		this.modeloMenu = modeloMenu;
	}

	public String procesarEventoMenu() {
		procesarEventoMenu(null);
		return null;
	}

	public void procesarEventoMenu(ActionEvent event)
			throws AbortProcessingException {
		System.out.println("Rutas de menu seleccionado");

		if (event.getSource().getClass() == MenuItem.class) {
			MenuItem sourceItem = (MenuItem) event.getSource();
//			TwebOpcion opcionActual = (TwebOpcion) sourceItem
//					.getAttributes()
//					.get(beanConfiguracionesGeneral
//							.getValorConstanteConfiguracion(NombresConstantesMemoria.NOMBRE_PARAMETRO_OPCION_MENU));

//			rutaOpcion = getRutaOpcion(opcionActual);
//			urlOpcion = opcionActual.getAccion();
			// Aqui se puede auditar la opcion que selecciona el cliente
		}
	}

	private String getRutaOpcion(TwebOpcion opcionActual) {
		String rutaOpcion = "";
		rutaOpcion = getOpcionPadre(opcionActual, rutaOpcion);
		rutaOpcion = rutaOpcion.substring(0, rutaOpcion.length() - 2);
		return rutaOpcion.toUpperCase();
	}

	private String getOpcionPadre(TwebOpcion opcion, String nombre) {
		if (opcion.getOpcionPadre() != null) {
			nombre = opcion.getOpcion() + " - " + nombre;
			return getOpcionPadre(opcion.getOpcionPadre(), nombre);
		}
		return opcion.getOpcion() + " - " + nombre;
	}

	public String getRutaOpcion() {
		return rutaOpcion;
	}

	public void setRutaOpcion(String rutaOpcion) {
		this.rutaOpcion = rutaOpcion;
	}

	public String getUrlOpcion() {
		return urlOpcion;
	}

	public void setUrlOpcion(String urlOpcion) {
		this.urlOpcion = urlOpcion;
	}
}
