package edu.uade.ida.deposito.service.integration;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class DespachoService
 */
@Stateless
@LocalBean
public class DespachoService implements DespachoServiceRemote, DespachoServiceLocal {

    /**
     * Default constructor. 
     */
    public DespachoService() {
        // TODO Auto-generated constructor stub
    }

}
