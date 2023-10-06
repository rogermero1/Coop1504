package ec.fin.online15.backend.librerias.entidades;

import java.io.Serializable;

public interface EntidadReconocible<Id extends Serializable> 
{
	public Id getId();
	public void setId(Id id);
}
