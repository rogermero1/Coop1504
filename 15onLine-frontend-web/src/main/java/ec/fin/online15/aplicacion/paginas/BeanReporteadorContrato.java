package ec.fin.online15.aplicacion.paginas;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.sql.DataSource;

import ec.fin.online15.backend.webbanking.modelo.entidades.TwebConvenios;
import ec.fin.online15.integracion.beans.BeanServiciosConvenio;
import ec.fin.online15.librerias.paginas.beans.BeanReporteadorGenerico;
import ec.fin.online15.librerias.paginas.beans.JSFUtils;
import ec.fin.online15.librerias.paginas.reportes.FormatoReporte;
import net.sf.jasperreports.engine.JRDataSource;

@ManagedBean(name = "beanReporteContrato")
@ViewScoped
public class BeanReporteadorContrato extends BeanReporteadorGenerico {


	@Resource(lookup = "java:/seedPoolDatos")
	private DataSource lConexion;
	
	private TwebConvenios convenioSeleccionado;
	
	@Inject
	private BeanServiciosConvenio servicioConvenio;
	
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() {
		setFormatoReporte(FormatoReporte.PDF);
	}

	@Override
	protected Map<String, Object> recuperarParametros(ActionEvent pActionEvent) {

		HashMap<String, Object> lParametro = new HashMap<String, Object>();
		return lParametro;
	}

	@Override
	protected File recuperarArchivoJasper() {
		//return new File(getContext().getExternalContext().getRealPath("/WEB-INF/reportes/rep_contrato_1504.jasper"));
		String urlBaseReportes = System.getProperty("urReportes");
		System.out.println("Archivo propiedades " + urlBaseReportes);
		return new File(urlBaseReportes+"rep_contrato_1504.jasper");
	}

	@Override
	protected JRDataSource recuperarDataSource() {
		return null;
	}

	public void exportarXLS(ActionEvent levento) {

			try {
				setFormatoReporte(FormatoReporte.XLS);
				exportar(levento);
			} catch (Throwable e) {
				e.printStackTrace();
			}
	}

	public void exportarPDF(ActionEvent levento) {
			try {
				setFormatoReporte(FormatoReporte.PDF);
				exportar(levento);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
	}

	@Override
	protected String getNombreArchivo() {
		return "convenio_" + convenioSeleccionado.getNumeroIdentificacion() + new Date().getTime();
	}

	@Override
	protected Connection getConexionDB() {
		try {
			return lConexion.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	protected void aniadirParametrosGenerales(Map<String, Object> p_map) {
		
		p_map.put("ID_CONVENIO", convenioSeleccionado.getId());
	}
	
	public void imprimirPDF(ActionEvent levento) {
		convenioSeleccionado = (TwebConvenios)levento.getComponent().getAttributes().get("ID_COVENIO");
		exportarPDF(levento);
	}
	
	public void imprimirPDFNumeroConvenio(ActionEvent levento) {
		String numeroConvenio = (String)levento.getComponent().getAttributes().get("NO_COVENIO");
		String cedula = (String)levento.getComponent().getAttributes().get("CEDULA");
		convenioSeleccionado = servicioConvenio.getIServicioConvenioWs().consultaConvenioWebCliente(JSFUtils.getHttpSession().getId(), cedula, numeroConvenio);
		exportarPDF(levento);
	}
	
}
