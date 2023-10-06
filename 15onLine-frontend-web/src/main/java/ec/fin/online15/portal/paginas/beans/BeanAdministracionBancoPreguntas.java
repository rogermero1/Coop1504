package ec.fin.online15.portal.paginas.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ec.fin.online15.aplicacion.paginas.BeanConfiguracionesGenerales;
import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebBancoPregunta;
import ec.fin.online15.integracion.beans.BeanServiciosMantenimientoBancoPreguntas;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;

//Ese man te esta enviando un nuevo request por el set properti listenr, entonces se ejecuta el metodo postconstruct, e inicializa el objeto
//dale ahi para ver como se comporta
@Named("administracionBancoPreguntasMB")
@SessionScoped
public class BeanAdministracionBancoPreguntas extends BaseManagedBean implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BeanConfiguracionesGenerales configuracionesGenerales;

	@Inject
	private BeanServiciosMantenimientoBancoPreguntas serviciosMantenimientoBancoPreguntas;

	private List<TwebBancoPregunta> listadoPreguntas;
	private TwebBancoPregunta bancoPregunta;
	private boolean accionEliminar;

	@PostConstruct
	public void init() {
		this.inicializacion();
	}

	public void inicializacion() {
		bancoPregunta = new TwebBancoPregunta();
		bancoPregunta.setId(null);
		listadoPreguntas = serviciosMantenimientoBancoPreguntas
				.getIServicioBancoPreguntasWs().listadoBancoPreguntas(
						this.getHttpSession().getId());
		accionEliminar = true;

	}

	public List<TwebBancoPregunta> getListadoPreguntas() {
		return listadoPreguntas;
	}

	public void setListadoPreguntas(List<TwebBancoPregunta> listadoPreguntas) {
		this.listadoPreguntas = listadoPreguntas;
	}

	public TwebBancoPregunta getBancoPregunta() {
		return bancoPregunta;
	}

	public void setBancoPregunta(TwebBancoPregunta bancoPregunta) {
		this.bancoPregunta = bancoPregunta;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public void setAccionEliminar(boolean accionEliminar) {
		this.accionEliminar = accionEliminar;
	}

	public void creaBancoPreguntaMb(ActionEvent evento) {
		if (bancoPregunta.getId() != null) {
			System.out.println("Id para actualizar " + bancoPregunta.getId());
			System.out.println("actualizacion de preguntas");
			this.actualizaPreguntaMb();
		} else {
			System.out.println("creacion de preguntas ");
			this.creaPreguntaMb();
		}
	}

	private void creaPreguntaMb() {
		try {
			this.bancoPregunta.setEstado("A");
			this.bancoPregunta.setFechaRegistro(new Date());
			this.bancoPregunta.setRegistradoPor(this.getUsuarioAutenticado()
					.toString());
			serviciosMantenimientoBancoPreguntas.getIServicioBancoPreguntasWs()
					.crearPregunta(this.getHttpSession().getId(),
							this.bancoPregunta);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	private void actualizaPreguntaMb() {
		try {
			this.bancoPregunta.setEstado("A");
			this.bancoPregunta.setFechaActualizacion(new Date());
			this.bancoPregunta.setActualizadoPor(this.getUsuarioAutenticado()
					.toString());
			serviciosMantenimientoBancoPreguntas.getIServicioBancoPreguntasWs()
					.actualizarPregunta(this.getHttpSession().getId(),
							this.bancoPregunta);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} catch (Exception e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void eliminaPreguntaMb(ActionEvent event) throws ExcepcionAplicacion {
		try {
			this.bancoPregunta.setFechaActualizacion(new Date());
			this.bancoPregunta.setActualizadoPor(this.getUsuarioAutenticado()
					.toString());
			this.bancoPregunta.setEstado("I");
			System.out.println("Eliminar pregunta");
			serviciosMantenimientoBancoPreguntas.getIServicioBancoPreguntasWs()
					.actualizarPregunta(this.getHttpSession().getId(),
							this.bancoPregunta);
			this.configuracionesGenerales.mensajeTransaccionExitosa();
			inicializacion();
		} catch (Throwable e) {
			e.printStackTrace();
			this.configuracionesGenerales.mensajeTransaccionError();
		}
	}

	public void onRowSelect(SelectEvent event) {
		this.accionEliminar = false;
	}

	public void onRowUnselect(UnselectEvent event) {
		this.accionEliminar = true;
	}

}
