package edu.uade.ida.deposito.service.integration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.SolicitudCompraDTO;

/**
 * Session Bean implementation class FabricaService
 */
@Stateless
@LocalBean
public class FabricaService implements FabricaServiceLocal {

    /**
     * Default constructor. 
     */
    public FabricaService() {
    	super();
    }

	@Override
	public SolicitudCompraDTO crearSolicitudCompra(ArticuloDTO articulo, int cantidadSolicitada) {
		// TODO Auto-generated method stub
		return null;
	}
}
