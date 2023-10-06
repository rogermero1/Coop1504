package ec.fin.online15.aplicacion.paginas;

import java.util.ArrayList;
import java.util.List;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

import ec.fin.online15.aplicacion.generales.ConstantesMemoria;
import ec.fin.online15.aplicacion.generales.NombresConstantesMemoria;
import ec.fin.online15.aplicacion.qualificadores.QualificadorGestorMenuSimple;
import ec.fin.online15.aplicacion.transversales.interfaces.IBeanGestorMenu;
import ec.fin.online15.backend.seguridad.modelo.entidades.TwebOpcion;
import ec.fin.online15.integracion.beans.BeanServiciosAutorizacion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

@QualificadorGestorMenuSimple
@RequestScoped
public class BeanGestorMenuHorizontal extends BaseManagedBean implements IBeanGestorMenu {

	private static final long serialVersionUID = 1L;

	// @Inject
	// private BeanConfiguracionesGenerales beanConfiguracionesGeneral;

	@Inject
	private BeanServicioUsuario servicioUsuario; 

	@Inject
	private BeanServiciosAutorizacion beanServiciosAutorizacion;

	public MenuModel getMenu() {
		// consultar estado de procesos de cierre
		return cargarMenu();
	}

	private MenuModel cargarMenu() {
		System.out.println("generacion de menu dinamico final ...");
		MenuModel menu = new DefaultMenuModel();
		List<TwebOpcion> opcionesPadre = getOpcionesPadre();
		if (opcionesPadre != null) {
			for (TwebOpcion opcionPadre : opcionesPadre) {
				//System.out.println("OPC-PADRE: " + opcionPadre.getDescripcion());
				if (opcionPadre != null) {
					DefaultSubMenu submenu = new DefaultSubMenu(opcionPadre.getOpcion());
					submenu.setIcon(opcionPadre.getRutaImagen());// fa-home
					List<TwebOpcion> opcionesHijas = getOpcionesHijas(opcionPadre);
					if (opcionesHijas != null) {
						for (TwebOpcion opcionHija : opcionesHijas) {
							//System.out.println("OPC-HIJA: " + opcionHija.getDescripcion());
							if (opcionHija != null) {
								submenu.getElements().add(getElementoSubmenu(opcionHija));
							}
						}
					}
					menu.addElement(submenu);
				}
			}
		}
		return menu;
	}

	private MenuElement getElementoSubmenu(TwebOpcion opcionHija) {
		List<TwebOpcion> opcionesHijas = getOpcionesHijas(opcionHija);
		//System.out.println("OP-HILA " + opcionHija.getDescripcion() + "tamaño " + opcionesHijas.size());
		if (opcionesHijas.isEmpty()){//(opcionesHijas == null) {// opcionesHijas.size() < 1
			//System.out.println("XSI");
			DefaultMenuItem menItm = new DefaultMenuItem(opcionHija.getOpcion());
			// System.out.println("procesar el evento final");
			menItm.setImmediate(true);
			menItm.setValue(opcionHija.getOpcion());
			menItm.setAjax(false);
			// System.out.println("Ruta Imagen"+opcionHija.getRutaImagen());
			System.out.println("ACCION HIJA: " + opcionHija.getAccion());
			menItm.setIcon(opcionHija.getRutaImagen());
			menItm.setTitle(opcionHija.getDescripcion());
			if (opcionHija.getValidaOnLine().equals("S") && this.servicioUsuario.getControlBatch().equals("S")) {
				menItm.setUrl(this.getContextPath() + ConstantesMemoria
						.getValorParametro(NombresConstantesMemoria.RUTA_CONTROL_BATCH, String.class));
			} else {
				menItm.setUrl(this.getContextPath() + opcionHija.getAccion());
			}
//			menItm.getAttributes()
//					.put(beanConfiguracionesGeneral
//							.getValorConstanteConfiguracion(NombresConstantesMemoria.NOMBRE_PARAMETRO_OPCION_MENU),
//							opcionHija);
			ExpressionFactory factory = this.getApplication().getExpressionFactory();
			MethodExpression methodExpression = factory.createMethodExpression(this.getFacesContext().getELContext(),
					"#{menuMB.procesarEventoMenu}", String.class, new Class[] {});
			// menItm.setActionExpression(methodExpression);
			return menItm;
		} else {
			// si tiene hijas entonces es un submenu
			//System.out.println("XNO");
			DefaultSubMenu menItm = new DefaultSubMenu(opcionHija.getOpcion());
			menItm.setIcon(opcionHija.getRutaImagen());
			for (TwebOpcion opcion : opcionesHijas) {
				if (opcion != null)
					menItm.getElements().add(getElementoSubmenu(opcion));
			}
			return menItm;
		}
	}

	private List<TwebOpcion> getOpcionesPadre() {
		// Se deb cargar desde un ejb
		// la consulta debe utilizar el usuario principal y traer las opciones
		// que tiene permitidas
		List<TwebOpcion> opcionesPadres = new ArrayList<TwebOpcion>();
		opcionesPadres = beanServiciosAutorizacion.getIServicioAutorizacionWs()
				.listaOpcionesMenuPadre(this.getHttpSession().getId(), this.getNombreUsuarioAutenticado(), "H");
		return opcionesPadres;
	}

	private List<TwebOpcion> getOpcionesHijas(TwebOpcion opcionPadre) {
		// Se deb cargar desde un ejb
		// Se debe verificar que estas opciones son permitidas para el usuario
		// getUserPrincipal
		List<TwebOpcion> listaRamasMenu = new ArrayList<TwebOpcion>();
		listaRamasMenu = beanServiciosAutorizacion.getIServicioAutorizacionWs().listaOpcionesHijas(
				this.getHttpSession().getId(), opcionPadre.getId(), this.getNombreUsuarioAutenticado(), "H");
		return listaRamasMenu;
	}

}
