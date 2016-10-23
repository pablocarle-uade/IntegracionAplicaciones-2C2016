package edu.uade.ida.deposito.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uade.ida.deposito.data.SolicitudArticuloRepository;
import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.util.DTOUtil;

/**
 * Session Bean implementation class SolicitudArticulosMgrBean
 */
@Stateless
@LocalBean
public class SolicitudArticulosMgrBean implements SolicitudArticulosMgrBeanLocal {
	
	@Inject
	private SolicitudArticuloRepository sar;
	
    /**
     * Default constructor. 
     */
    public SolicitudArticulosMgrBean() {
    	super();
    }

	@Override
	public List<SolicitudArticuloDTO> getSolicitudesStockPendientes() {
		return DTOUtil.getDTOs(sar.getPorEstado("pendiente", "no_cumplido"), SolicitudArticuloDTO.class);
	}
}
