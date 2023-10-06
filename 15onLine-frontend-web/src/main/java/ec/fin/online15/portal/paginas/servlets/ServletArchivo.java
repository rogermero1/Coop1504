package ec.fin.online15.portal.paginas.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ec.fin.online15.portal.paginas.beans.BeanSolicitudPrestamo;

/**
 * Servlet implementation class ServletArchivo
 */
@WebServlet("/servlet_archivo")
public class ServletArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletFileUpload arhivoRemoto = null;

	@Inject
	@Default
	private BeanSolicitudPrestamo bean;

	public void init() throws ServletException {
		DiskFileItemFactory directorioTemporal = new DiskFileItemFactory();
		File directorioArchvio = (File) getServletContext().getAttribute(
				"tempfile.dir");
		directorioTemporal.setRepository(directorioArchvio);
		this.arhivoRemoto = new ServletFileUpload(directorioTemporal);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException(
					"Content type is not multipart/form-data");
		}

		response.setContentType("text/html");
		PrintWriter lSalidaHtml = response.getWriter();
		lSalidaHtml.write("<html><head></head><body>");
		try {
			List<FileItem> listArchivos = arhivoRemoto.parseRequest(request);
			Iterator<FileItem> iteradorArchivo = listArchivos.iterator();

			String directorio = System.getProperty("directorioArchivos");
			System.out.println("Usando el directorio: " + directorio);

			while (iteradorArchivo.hasNext()) {

				FileItem archivoItem = iteradorArchivo.next();
				if (archivoItem.getName() != null) {

					File directorioCliente = new File(directorio
							+ bean.getDatosCliente().getIdentificacion());
					if (!directorioCliente.exists()) {
						directorioCliente.mkdir();
					}

					String nombreArchivo = archivoItem.getName();

					// System.out.println("inicia");
					// entra 2 veces x null
					System.out.println(archivoItem.getName());

					nombreArchivo = bean.getNombreArchivo()
							+ nombreArchivo.substring(
									nombreArchivo.lastIndexOf("."),
									nombreArchivo.length());
					System.out.println(nombreArchivo);

					File archivoLocal = new File(directorio
							+ bean.getDatosCliente().getIdentificacion() + "/"
							+ nombreArchivo);
					// File archivoLocal = new File("C:/temp/" +
					// archivoItem.getName());

					// File archivoLocal1 = new File("C:/temp/" +
					// archivoItem.getName());
					//
					// if (archivoLocal1.exists())

					archivoItem.write(archivoLocal);
					// lSalidaHtml.write("Archivo " + archivoItem.getName()
					// + " cargado correctamente ");

					System.out.println("Carga");
					if (nombreArchivo.contains("cedula")) {
						bean.setCargaCedula("S");
					} else if (nombreArchivo.contains("Votacion")) {
						bean.setCargaVotacion("S");
					} else if (nombreArchivo.contains("Ingresos")) {
						bean.setCargaIngresos("S");
					}
					lSalidaHtml.write("Archivo cargado correctamente ");

				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			lSalidaHtml.write("Error al realizar la carga del archivo");
		} catch (Exception e) {
			e.printStackTrace();
			lSalidaHtml.write("Error al realizar la carga del archivo");
		}
		lSalidaHtml.write("</body></html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
