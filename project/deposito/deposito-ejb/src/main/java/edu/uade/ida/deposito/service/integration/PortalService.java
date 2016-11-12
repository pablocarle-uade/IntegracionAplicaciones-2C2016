package edu.uade.ida.deposito.service.integration;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.service.integration.core.JMSClient;
import edu.uade.ida.deposito.service.integration.core.JMSClientConfiguration;
import edu.uade.ida.deposito.util.NivelAudit;
import edu.uade.ida.deposito.util.config.ConfigHolder;
import edu.uade.ida.deposito.util.config.ConfigModulo;
import edu.uade.ida.deposito.util.config.JmsEndpointConfig;

@Stateless
@LocalBean
public class PortalService implements PortalServiceLocal, PortalServiceRemote {

	@Inject
	private LoggerLocal log;
	
	@Inject
	private ConfigHolder config;
	
	@Inject
	private JMSClient jmsClient;
	
	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
	@Override
	public void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		log.info(this, "Notificando a Portales sobre nuevo artículo...");
		List<JmsEndpointConfig> endpointConfigs = config.getAsyncServers(ConfigModulo.PORTAL);
		
		String body = buildJsonBody(notificacionNuevoArticulo);
		try {
			for (JmsEndpointConfig jms : endpointConfigs) {
				log.info(this, "Enviando: " + body + " a portal " + jms.getProviderUrl() + " en queue " + jms.getJmsQueue() + " con user " + jms.getUser() + " y pass " + jms.getPassword());
				JMSClientConfiguration clientConfig = new JMSClientConfiguration(body, jms.getJmsQueue(),
						jms.getProviderUrl(), jms.getUser(), jms.getPassword());

				jmsClient.invoke(clientConfig);
			}
		} catch (Exception e) {
			log.log(this, Level.WARNING, "Error notificando a portales de nuevo artículo", e);
			lms.enviarAudit(NivelAudit.WARN, "Error notificando a portales de nuevo artículo");
		}
	}
	
	private String buildJsonBody(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		return new Gson().toJson(notificacionNuevoArticulo);
	}

}
