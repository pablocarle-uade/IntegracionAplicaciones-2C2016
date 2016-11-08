package edu.uade.ida.deposito.service.integration;

import javax.ejb.Local;

import edu.uade.ida.deposito.util.NivelAudit;

@Local
public interface LogisticaMonitoreoServiceLocal {

	public abstract void enviarAudit(NivelAudit nivel, String mensaje);
	
}
