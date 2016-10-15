package edu.uade.ida.deposito.service;

import java.util.List;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;

@Local
public interface SolicitudArticulosMgrBeanLocal {
	
	public List<SolicitudArticuloDTO> getSolicitudesStockPendientes();

}
