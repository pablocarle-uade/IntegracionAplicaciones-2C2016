package edu.uade.ida.deposito.service.integration;

import javax.ejb.Local;

@Local
public interface LogisticaMonitoreoServiceLocal {

	public abstract void enviarAudit(NivelAudit nivel, String mensaje);
	
}
