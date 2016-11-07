package edu.uade.ida.deposito.service.integration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.util.config.ConfigHolder;

/**
 * Session Bean implementation class DespachoService
 */
@Stateless
@LocalBean
public class DespachoService implements DespachoServiceRemote, DespachoServiceLocal {

	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
	@Inject
	private ConfigHolder config;
	
	@Inject
	private Logger log;
	
    public DespachoService() {
    	super();
    }

	@Override
	public void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo) {
		// REST
		String restEndpoint = ""; //TODO Obtener de config
		try {
			URL url = new URL("");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			
		} catch (IOException e) {
			log.log(Level.WARNING, "Error en comunicacion con despacho para entrega de articulos", e);
			e.printStackTrace();
		}
	}
}
