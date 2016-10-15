package edu.uade.ida.deposito.service;

import java.util.List;

import javax.ejb.Local;

import edu.uade.ida.deposito.dto.SolicitudStockDTO;

@Local
public interface SolicitudArticulosMgrBeanLocal {
	
	public List<SolicitudStockDTO> getSolicitudesStockPendientes();

}
