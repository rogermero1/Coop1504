package ec.fin.online15.librerias.paginas.reportes;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import ec.fin.online15.backend.librerias.excepciones.ExcepcionAplicacion;
import ec.fin.online15.librerias.paginas.beans.BaseManagedBean;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

public abstract class ReporteManagedBean extends BaseManagedBean {

	private static final long serialVersionUID = 1L;

	private FormatoReporte formatoReporte;

	public FormatoReporte getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(FormatoReporte formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	protected Object getParametro(String pNombreParametro,
			ActionEvent pActionEvent) {
		return pActionEvent.getComponent().getAttributes()
				.get(pNombreParametro);
	}

	protected abstract Map<String, Object> recuperarParametros(
			ActionEvent pActionEvent);

	protected abstract File recuperarArchivoJasper();

	protected JRDataSource recuperarDataSource() {
		return null;
	}

	private String getNombreArchivoExtensionArchivo() {
		String lNombreArchivo = getNombreArchivo();
		if (lNombreArchivo == null) {
			File lReporteJasper = recuperarArchivoJasper();
			lNombreArchivo = lReporteJasper.getName();
			int lIndice = lNombreArchivo.lastIndexOf('.');
			if (lIndice != -1)
				lNombreArchivo = lNombreArchivo.substring(0, lIndice);
		}
		lNombreArchivo = lNombreArchivo + "." + formatoReporte.getExtension();
		return lNombreArchivo;
	}

	protected String getNombreArchivo() {
		return null;
	}

	public void exportar(ActionEvent evento) {
		try {
			// Parametros del reporte
			Map<String, Object> lParametros = recuperarParametros(evento);
			aniadirParametrosGenerales(lParametros);
			// Archivo del reporte
			File lReporteJasper = recuperarArchivoJasper();
			// Creo objeto reporte
			JasperReport lReporte = (JasperReport) JRLoader
					.loadObject(lReporteJasper);
//este es quien exporta
			//simon, el problema era q slo funcionaban en la maquina q corria el jboss
			
			
			//ya loco dejame ver un ejemplo que tengo
			
			//cuando presionas el boton no hace nada o te sale un error
			//no hace nada
			//ya loco dejame ver si se arregal
			
			//prueba ahi esa nota loco
			//man solo le cambiastes lo del ajax??
			//simon funco ahi? simon eso era
			//jajajajajajajaja propio solo en mi maquia servia por ese ajax
			//gracias loco, y pilas cuando tendras chance para q me ayudes con lo de bce?
		
			if (formatoReporte == null)
				formatoReporte = FormatoReporte.PDF;

			switch (formatoReporte) {
			case HTML:
				exportarHtml(lReporte, lParametros);
				break;
			case PDF:
				exportarPDF(lReporte, lParametros);
				break;
			case CSV:
				exportarCSV(lReporte, lParametros);
				break;
			case RTF:
				exportarRtf(lReporte, lParametros);
				break;
			case TXT:
				exportarTXT(lReporte, lParametros);
				break;
			case XLS:
				exportarXls(lReporte, lParametros);
				break;
			default:
				throw new ExcepcionAplicacion("exp-1",
						"Error formatoNoEspecificado");
			}
			getFacesContext().responseComplete();
		} catch (Exception e) {
			aniadirMensajeError(e);
		}
	}

	@SuppressWarnings("deprecation")
	protected void exportarHtml(JasperReport pReporte,
			Map<String, Object> pParametros) throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a html
		JRHtmlExporter exporter = new JRHtmlExporter();

		setSessionAttribute(
				ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
				lVisualizador);

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
				getOutputWriter());
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				getContextPath() + "/image?image=");

		exporter.exportReport();
	}

	protected void exportarPDF(JasperReport pReporte,
			Map<String, Object> pParametros) throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a pdf
		JRPdfExporter exporter = new JRPdfExporter();
		// Los objetos para manipular el requerimiento y respuesta
		HttpServletResponse lHttpServletResponse = getHttpServletResponse();
		lHttpServletResponse.setContentType("application/pdf");
		lHttpServletResponse.setHeader("Content-Disposition",
				"attachment; filename=\"" + getNombreArchivoExtensionArchivo()
						+ "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				getOutputStream());

		exporter.exportReport();
	}

	protected void exportarRtf(JasperReport pReporte,
			Map<String, Object> pParametros) throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a pdf
		JRRtfExporter exporter = new JRRtfExporter();
		// Los objetos para manipular el requerimiento y respuesta
		HttpServletResponse lHttpServletResponse = getHttpServletResponse();
		lHttpServletResponse.setContentType("application/rtf");
		lHttpServletResponse.setHeader("Content-Disposition",
				"attachment; filename=\"" + getNombreArchivoExtensionArchivo()
						+ "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				getOutputStream());
		exporter.exportReport();
	}

	protected void exportarXls(JasperReport pReporte,
			Map<String, Object> pParametros) throws JRException, IOException {
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);
		// El objeto que exportara el formato a xls
		JRXlsExporter lExporter = new JRXlsExporter();
		// JExcelApiExporter exporter = new JExcelApiExporter();
		// Los objetos para manipular el requerimiento y respuesta del servlet
		HttpServletResponse lHttpServletResponse = getHttpServletResponse();
		lHttpServletResponse.setContentType("application/xls");
		lHttpServletResponse.setHeader("Content-Disposition",
				"attachment; filename=\"" + getNombreArchivoExtensionArchivo()
						+ "\"");

		lExporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		lExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				getOutputStream());
		lExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				Boolean.TRUE);
		lExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
				Boolean.TRUE);
		lExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);
		lExporter.setParameter(
				JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		lExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				Boolean.FALSE);

		lExporter.exportReport();
	}

	protected void exportarTXT(JasperReport pReporte,
			Map<String, Object> pParametros) throws JRException, IOException {
		// Para ignorar la paginacion
		pParametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);

		// El objeto que exportara el formato a txt
		JRTextExporter exporter = new JRTextExporter();
		// Los objetos para manipular el requerimiento y respuesta del servlet
		HttpServletResponse lHttpServletResponse = getHttpServletResponse();
		lHttpServletResponse.setContentType("application/txt");
		lHttpServletResponse.setHeader("Content-Disposition",
				"inline; filename=\"" + getNombreArchivoExtensionArchivo()
						+ "\"");

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
				getOutputWriter());

		exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH,
				new Integer(8));
		exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT,
				new Integer(8));

		exporter.exportReport();
	}

	protected void exportarCSV(JasperReport pReporte,
			Map<String, Object> pParametros) throws JRException, IOException {
		// Para ignorar la paginacion
		pParametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		// Creo el visializador
		JasperPrint lVisualizador = crearVisualizador(pReporte, pParametros);

		// Los objetos para manipular el requerimiento y respuesta del servlet
		HttpServletResponse lHttpServletResponse = getHttpServletResponse();
		lHttpServletResponse.setContentType("application/csv");
		lHttpServletResponse.setHeader("Content-Disposition",
				"inline; filename=\"" + getNombreArchivoExtensionArchivo()
						+ "\"");

		// El objeto que exportara el formato a csv
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, lVisualizador);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
				getOutputWriter());
		exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "|");
		exporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");
		exporter.exportReport();
	}

	private OutputStream getOutputStream() throws IOException {
		return getHttpServletResponse().getOutputStream();
	}

	private JasperPrint crearVisualizador(JasperReport l_reporte,
			Map<String, Object> l_parametros) throws JRException {
		JRDataSource lJrDataSource = recuperarDataSource();
		JasperPrint lJasperPrint = null;
		if (lJrDataSource == null)
			lJasperPrint = JasperFillManager
					.fillReport(l_reporte, l_parametros,
							recuperarConexionReporte()/* (Connection) null */);
		else
			lJasperPrint = JasperFillManager.fillReport(l_reporte,
					l_parametros, lJrDataSource);
		return lJasperPrint;
	}

	private void aniadirParametrosGenerales(Map<String, Object> p_map) {

	}

	protected Connection recuperarConexionReporte() {
		return null;
	}

	// protected Connection recuperarConexionReporte() {
	// try {
	// Context ctx = new InitialContext();
	// DataSource ds = (DataSource)ctx.lookup("java:/seedPoolDatos");
	// return ds.getConnection();
	// } catch (Throwable e) {
	// e.printStackTrace();
	// this.aniadirMensajeError("Error general",
	// "Por favor, comuniquese con sistemas");
	// }
	// return null;
	// }

}
