package edu.uade.ida.deposito.service.integration;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;
import edu.uade.ida.deposito.service.integration.core.JMSClient;
import edu.uade.ida.deposito.service.integration.core.JMSClientConfiguration;
import edu.uade.ida.deposito.util.config.ConfigHolder;
import edu.uade.ida.deposito.util.config.ConfigModulo;
import edu.uade.ida.deposito.util.config.JmsEndpointConfig;

@Stateless
@LocalBean
public class PortalService implements PortalServiceLocal, PortalServiceRemote {

	@Inject
	private Logger log;
	
	@Inject
	private ConfigHolder config;
	
	@Inject
	private JMSClient jmsClient;
	
	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
	@Override
	public void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		log.info("Notificando a Portales sobre nuevo artículo...");
		List<JmsEndpointConfig> conf = config.getAsyncServers(ConfigModulo.PORTAL);
		
		String body = buildJsonBody(notificacionNuevoArticulo);
		
		try {
			JMSClientConfiguration config = null;
			for (JmsEndpointConfig jms : conf) {
				log.info("Enviando: " + body + " a despacho " + jms.getProviderUrl() + " en queue " + jms.getJmsQueue() + " con user " + jms.getUser() + " y pass " + jms.getPassword());
				config = new JMSClientConfiguration(body, jms.getJmsQueue(), jms.getProviderUrl(), jms.getUser(), jms.getPassword());
				jmsClient.invoke(config);
			}
			
		} catch (Exception e) {
			log.log(Level.WARNING, "Error notificando a portales de nuevo articulo", e);
		}
	}
	
	private String buildJsonBody(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		return new Gson().toJson(notificacionNuevoArticulo);
	}

}
