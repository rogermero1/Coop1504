package ec.fin.online15.backend.webbanking.modelo.repositorios;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebMovimientos;

@Stateless
@LocalBean
public class TwebMovimientosEAO extends EAOSeed<TwebMovimientos, Long> {

}
