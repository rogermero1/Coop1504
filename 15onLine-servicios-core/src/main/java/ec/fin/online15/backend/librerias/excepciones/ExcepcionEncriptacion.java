package ec.fin.online15.backend.librerias.excepciones;



public class ExcepcionEncriptacion extends ExcepcionAplicacion {

	private static final long serialVersionUID = -3654889342085728586L;
	
	public ExcepcionEncriptacion(Throwable pCausa)
	{
		super("encriptacion","Fallo al encriptar la clave",pCausa.getMessage(),pCausa);
	}  
}
