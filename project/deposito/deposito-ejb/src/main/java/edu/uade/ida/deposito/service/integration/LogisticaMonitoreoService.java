package edu.uade.ida.deposito.service.integration;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.MensajeAuditDTO;
import edu.uade.ida.deposito.service.LoggerLocal;
import edu.uade.ida.deposito.service.integration.core.JMSClient;
import edu.uade.ida.deposito.service.integration.core.JMSClientConfiguration;
import edu.uade.ida.deposito.util.EscapeUtil;
import edu.uade.ida.deposito.util.NivelAudit;
import edu.uade.ida.deposito.util.config.ConfigHolder;
import edu.uade.ida.deposito.util.config.ConfigModulo;
import edu.uade.ida.deposito.util.config.JmsEndpointConfig;
import edu.uade.ida.deposito.util.config.LogisticaMonitoreoConfig;

/**
 * Session Bean implementation class LogisticaMonitoreoService
 */
@Stateless
@LocalBean
public class LogisticaMonitoreoService implements LogisticaMonitoreoServiceLocal {

	private enum Modo { SYNC, ASYNC };
	
	private static Gson gson = new Gson();
	
	@Inject
	private LoggerLocal log;
	
	@Inject
	private JMSClient jmsClient;
	
	@Inject
	private ConfigHolder config;
	
    public LogisticaMonitoreoService() {
    	super();
    }
    
	@Override
	public void enviarAudit(NivelAudit nivel, String mensaje) {
		Modo modo = getModo();
		switch (modo) {
		case ASYNC:
			enviarAuditAsync(nivel, mensaje);
			break;
		case SYNC:
			enviarAuditSync(nivel, mensaje);
			break;
		default:
			throw new RuntimeException("No se reconoce modo " + modo);
		}
	}
	
	private void enviarAuditSync(NivelAudit nivel, String mensaje) {
		String auditServerRestEndpointUrl = config.getRESTEndpointURL(ConfigModulo.LOGISTICA);
		MensajeAuditDTO mad = buildMensaje(nivel, mensaje);
		String body = gson.toJson(mad);
		log.info(this, "Enviar sync a audit [" + body + "] " + " a " + auditServerRestEndpointUrl);
		
		try {
			URL url = new URL(auditServerRestEndpointUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			OutputStream os = con.getOutputStream();
			os.write(body.getBytes(Charset.forName("UTF-8")));
			os.flush();
			os.close();
			
			int responseCode = con.getResponseCode();
			log.info(this, "Envio sync a audit volvio con codigo [" + responseCode + "]");
			
			con.disconnect();
		} catch (IOException e) {
			log.log(this, Level.WARNING, "Error al enviar mensaje de audit sync", e);
			e.printStackTrace();
		}
	}
	
	private void enviarAuditAsync(final NivelAudit nivel, final String mensaje) {
		new Thread(new Runnable() {
		    public void run() {
				List<JmsEndpointConfig> logisticaConf = config.getAsyncServers(ConfigModulo.LOGISTICA);
				try {
					MensajeAuditDTO mad = buildMensaje(nivel, mensaje);
					String body = gson.toJson(mad);
					for (JmsEndpointConfig logistica : logisticaConf) {
						log.info(this, "Enviando mensaje de auditoria con " + mad);
						JMSClientConfiguration conf = new JMSClientConfiguration(body, logistica.getJmsQueue(), logistica.getProviderUrl(), logistica.getUser(), logistica.getPassword());
						jmsClient.invoke(conf);
					}
				} catch (Exception e) {
					log.log(this, Level.WARNING, "Error al invocar", e);
					e.printStackTrace();
				}
		    }
		}).start();
	}
	
	private MensajeAuditDTO buildMensaje(NivelAudit nivel, String mensaje) {
		SimpleDateFormat sdf = new SimpleDateFormat(MensajeAuditDTO.FORMATO_FECHA);
		String fecha = sdf.format(Calendar.getInstance().getTime());
		return new MensajeAuditDTO(fecha, "Deposito", config.getIdDeposito(), EscapeUtil.escapeJsonStringValue(nivel.toString() + " - " + mensaje));
	}
	
	private Modo getModo() {
		Modo modo = Modo.SYNC;
		LogisticaMonitoreoConfig conf = config.getLogisticaMonitoreoConfig();
		if (conf.isAsync()) {
			modo = Modo.ASYNC;
		}
		return modo;
	}
}
