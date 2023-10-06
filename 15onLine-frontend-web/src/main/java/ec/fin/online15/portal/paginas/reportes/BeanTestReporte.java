package ec.fin.online15.portal.paginas.reportes;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ec.fin.online15.librerias.paginas.reportes.FormatoReporte;
import ec.fin.online15.librerias.paginas.reportes.ReporteManagedBean;

@Named("testReporteMB")
@SessionScoped
public class BeanTestReporte extends ReporteManagedBean {

	private static final long serialVersionUID = 1L;

	private Connection con;

	@PostConstruct
	private void init() {
		System.out.println("INIT");
		this.setFormatoReporte(FormatoReporte.PDF);
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/seedPoolDatos");
			con = ds.getConnection();
		} catch (Throwable e) {
			e.printStackTrace();
			this.aniadirMensajeError("Error general",
					"Por favor, comuniquese con sistemas");
		}
	}

	@Override
	protected Map<String, Object> recuperarParametros(ActionEvent pActionEvent) {
		Map<String, Object> parametro = new HashMap<String, Object>();
		parametro.put("P_NOMBRES", "WELLINGTON MACIAS");
		parametro.put("P_IDENTIFICACION", "6969696969");
		parametro.put("P_DIRECCION", "CASA");
		parametro.put("P_TELEFONO", "0123456789");
		parametro.put("P_CONVENIO", "000000");
		System.out.println("PARAMETROS");
		return parametro;
	}

	@Override
	protected File recuperarArchivoJasper() {
		try {
			String current = new java.io.File("\\.").getCanonicalPath();
			System.out.println("Current dir:" + current);

			System.out.println("Working Directory = "
					+ System.getProperty("user.dir"));

			System.out.println(this.getClass().getClassLoader().getResource("")
					.getPath());

			String internalPath = this.getClass().getName()
					.replace(".", File.separator);
			String externalPath = System.getProperty("user.dir")
					+ File.separator + "src";
			String workDir = externalPath
					+ File.separator
					+ internalPath.substring(0,
							internalPath.lastIndexOf(File.separator));

			System.out.println(internalPath);
			System.out.println(externalPath);
			System.out.println(workDir);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("BUSCA FILE");
		return new File("\\reportes\\rep_contrato.jasper");// ("D:\\reportes\\rep_contrato.jasper");
	}

	@Override
	protected Connection recuperarConexionReporte() {
		System.out.println("CONECTA");
		return this.con;
		// try {
		// Context ctx = new InitialContext();
		// DataSource ds = (DataSource) ctx.lookup("java:/seedPoolDatos");
		// return ds.getConnection();
		// } catch (Throwable e) {
		// e.printStackTrace();
		// this.aniadirMensajeError("Error general",
		// "Por favor, comuniquese con sistemas");
		// }
		// return null;
	}

	private Date fechaHora;

	public Date getFechaHora() {
		fechaHora = new Date();
		System.out.println("HORAAAA : " + fechaHora);
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}

}
