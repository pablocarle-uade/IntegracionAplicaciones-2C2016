package edu.uade.ida.deposito.service.out;

import javax.ejb.Local;

@Local
public interface LogisticaMonitoreoServiceLocal {

	public abstract void enviarAudit(NivelAudit nivel, String mensaje);
	
}
