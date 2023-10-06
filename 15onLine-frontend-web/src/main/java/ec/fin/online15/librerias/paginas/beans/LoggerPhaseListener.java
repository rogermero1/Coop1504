package ec.fin.online15.librerias.paginas.beans;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoggerPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 6609953457160715514L;

	@Override
	public void afterPhase(PhaseEvent arg0) {
		System.out.println(arg0.getPhaseId()+" "+arg0.getFacesContext().getViewRoot().getLocale());
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		if(arg0.getPhaseId()!=PhaseId.RESTORE_VIEW)
			System.out.println(arg0.getPhaseId()+" "+arg0.getFacesContext().getViewRoot().getLocale());
		else
			System.out.println(arg0.getPhaseId()+" "+arg0.getFacesContext().getViewRoot());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
