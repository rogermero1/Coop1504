package ec.fin.online15.backend.librerias.repositorios;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.fin.online15.backend.librerias.entidades.EntidadBasica;

public class EAOSeed<E extends EntidadBasica<Id>, Id extends Serializable>
		extends EAOEntidad<E, Id> {
	@PersistenceContext(unitName = "seed_pu")
	private EntityManager adminEntidad;

	protected EntityManager getEntityManager() {
		return adminEntidad;
	}
}
