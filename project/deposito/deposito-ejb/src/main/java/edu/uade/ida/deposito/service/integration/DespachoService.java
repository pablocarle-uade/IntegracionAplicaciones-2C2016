package edu.uade.ida.deposito.service.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;
import edu.uade.ida.deposito.util.config.ConfigHolder;
import edu.uade.ida.deposito.util.config.ConfigModulo;
import edu.uade.ida.deposito.service.integration.core.JMSClient;
import edu.uade.ida.deposito.service.integration.core.JMSClientConfiguration;

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

    // Config example:
	// JMSClientConfiguration("json_payload_here", "/jms/queue/ColaSolicitudesArticulos", "http-remoting://192.168.0.43:8080", "20000", "jmsuser", "jmsuser"); 

	@Override
	public void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		log.info("Notificando a Despachos sobre nuevo artículo...");
		// TODO consider n modules
		// TODO Fill with data from config holder, but now this is ok to change and test connection to other modules
		// JMSClientConfiguration config = new JMSClientConfiguration("", "destination", "providerUrl", "timeout", "user", "");

		JMSClientConfiguration config = new JMSClientConfiguration("json_payload_here", "/jms/queue/ColaSolicitudesArticulos", 
											"http-remoting://192.168.0.43:8080", "jmsuser", "jmsuser");

		try {
			jmsClient.invoke(config);
		} catch (Exception ex) {
			log.info("Error notificando a Despachos sobre nuevo artículo: " + ex.getMessage());
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
			
			String body = buildJsonBody(entregaArticulo);
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

	private String buildJsonBody(EntregaArticuloDTO entregaArticulo) {
		return new Gson().toJson(entregaArticulo);
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
