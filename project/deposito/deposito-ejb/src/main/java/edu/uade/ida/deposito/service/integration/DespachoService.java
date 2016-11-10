package edu.uade.ida.deposito.service.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.EntregaArticuloRequestDTO;
import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;
import edu.uade.ida.deposito.service.integration.core.JMSClient;
import edu.uade.ida.deposito.service.integration.core.JMSClientConfiguration;
import edu.uade.ida.deposito.util.NivelAudit;
import edu.uade.ida.deposito.util.config.ConfigHolder;
import edu.uade.ida.deposito.util.config.ConfigModulo;
import edu.uade.ida.deposito.util.config.JmsEndpointConfig;

/**
 * Session Bean implementation class DespachoService
 */
@Stateless
@LocalBean
public class DespachoService implements DespachoServiceRemote, DespachoServiceLocal {

	@Inject
	private JMSClient jmsClient;
	
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
	public void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		log.info("Notificando a Despachos sobre nuevo artículo...");
		List<JmsEndpointConfig> endpointConfigs = config.getAsyncServers(ConfigModulo.DESPACHO);
		// NotificacionNuevoArticuloDTO to json
		String body = buildJsonBody(notificacionNuevoArticulo);
		try {
			for (JmsEndpointConfig jms : endpointConfigs) {
				log.info("Enviando: " + body + " a despacho " + jms.getProviderUrl());
				JMSClientConfiguration clientConfig = new JMSClientConfiguration(body, jms.getJmsQueue(),
						jms.getProviderUrl(), jms.getUser(), jms.getPassword());

				jmsClient.invoke(clientConfig);
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Error notificando a despachos de nuevo artículo", e);
			lms.enviarAudit(NivelAudit.WARN, "Error notificando a portales de nuevo artículo");
		}
	}
    
	@Override
	public void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo) {
		// REST
		String restEndpoint = config.getRESTEndpointURL(ConfigModulo.DESPACHO, entregaArticulo.getIdModuloSolicitante());
		try {
			URL url = new URL(restEndpoint);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			
			String body = buildJsonBody(new EntregaArticuloRequestDTO(config.getIdDeposito(), entregaArticulo.getCodArticulo(), entregaArticulo.getCantidadAsignada()));
			OutputStream os = con.getOutputStream();
			os.write(body.getBytes("UTF-8"));
			os.flush();
			
			int responseCode = con.getResponseCode();
			BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));
			if (responseCode < 200 || responseCode > 299 || !verificarRetornoDespacho(bf)) {
				log.warning("Envio de notificación con error. Codigo " + responseCode);
				lms.enviarAudit(NivelAudit.WARN, "No se pudo enviar notificacion a despacho de entrega de articulo " + entregaArticulo.getIdModuloSolicitante());
			}
			bf.close();
		} catch (IOException e) {
			log.log(Level.WARNING, "Error en comunicacion con despacho para entrega de articulos", e);
			e.printStackTrace();
		}
	}

	private boolean verificarRetornoDespacho(BufferedReader bf) throws IOException {
		Gson gson = new Gson();
		String ln = "";
		StringBuilder builder = new StringBuilder();
		while ((ln = bf.readLine()) != null) {
			builder.append(ln);
		}
		DespachoResponse dr = gson.fromJson(builder.toString(), DespachoResponse.class);
		return Boolean.parseBoolean(dr.getProcesado());
	}

	private String buildJsonBody(EntregaArticuloRequestDTO entregaArticulo) {
		return new Gson().toJson(entregaArticulo);
	}
	
	private String buildJsonBody(NotificacionNuevoArticuloDTO notificacionNuevoArticuloDTO) {
		return new Gson().toJson(notificacionNuevoArticuloDTO);
	}
	
	private static class DespachoResponse implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String procesado;
		
		@SuppressWarnings("unused")
		public DespachoResponse() {
			super();
		}

		public String getProcesado() {
			return procesado;
		}

		@SuppressWarnings("unused")
		public void setProcesado(String procesado) {
			this.procesado = procesado;
		}
	}

}
