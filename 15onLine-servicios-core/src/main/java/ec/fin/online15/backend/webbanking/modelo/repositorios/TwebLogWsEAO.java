package ec.fin.online15.backend.webbanking.modelo.repositorios;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ec.fin.online15.backend.librerias.repositorios.EAOSeed;
import ec.fin.online15.backend.webbanking.modelo.entidades.TwebLogWs;

@Stateless
@LocalBean
public class TwebLogWsEAO extends EAOSeed<TwebLogWs, Long> {

}
