package edu.uade.ida.deposito.service.integration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

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

}
