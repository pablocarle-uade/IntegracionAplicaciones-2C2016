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

/**
 * Session Bean implementation class DespachoService
 */
@Stateless
@LocalBean
public class DespachoService implements DespachoServiceRemote, DespachoServiceLocal {

	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
//	@Inject
//	private ConfigHolder config;
	
	@Inject
	private Logger log;
	
    public DespachoService() {
    	super();
    }

	@Override
	public void noticarNuevoArticulo(NotificacionNuevoArticuloDTO notificacionNuevoArticulo) {
		
	}
    
	@Override
	public void notificarEntregaArticulo(EntregaArticuloDTO entregaArticulo) {
		// REST
		String restEndpoint = ""; //TODO Obtener de config, segun entregaArticulo.getIdModuloSolicitante()
		try {
			URL url = new URL("");
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
				//TODO Notificar logistica y monitoreo
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
