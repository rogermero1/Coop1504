package ec.fin.online15.librerias.paginas.reportes;

public enum FormatoReporte {
	HTML("html"), PDF("pdf"), XLS("xls"), RTF("rtf"), CSV("csv"), TXT("txt");

	private String extension;

	private FormatoReporte(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}

}
