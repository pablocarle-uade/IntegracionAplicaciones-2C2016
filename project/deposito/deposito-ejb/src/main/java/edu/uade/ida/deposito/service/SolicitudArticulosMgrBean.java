package edu.uade.ida.deposito.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;

/**
 * Session Bean implementation class SolicitudArticulosMgrBean
 */
@Stateless
@LocalBean
public class SolicitudArticulosMgrBean implements SolicitudArticulosMgrBeanLocal {
	
    /**
     * Default constructor. 
     */
    public SolicitudArticulosMgrBean() {
    	super();
    }

	@Override
	public List<SolicitudArticuloDTO> getSolicitudesStockPendientes() {
		// TODO Auto-generated method stub
		return null;
	}
}
