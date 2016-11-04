package edu.uade.ida.deposito.service.out;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.google.gson.Gson;

import edu.uade.ida.deposito.dto.MensajeAuditDTO;
import edu.uade.ida.deposito.util.ConfigHolder;
import edu.uade.ida.deposito.util.EscapeUtil;

/**
 * Session Bean implementation class LogisticaMonitoreoService
 */
@Stateless
@LocalBean
public class LogisticaMonitoreoService implements LogisticaMonitoreoServiceLocal {

	private enum Modo { SYNC, ASYNC };
	
	private static Gson gson = new Gson();
	
	@Inject
	private Logger log;
	
	@Resource(mappedName = "java:/jms/queue/ColaSolicitudesArticulos") //TODO Obtener por contexto porque hay que levantar de config
	private Queue csa;

	@Resource(mappedName = "/ConnectionFactory")
	private ConnectionFactory factory;
	
	ConfigHolder config; //TODO Inject
	
    public LogisticaMonitoreoService() {
    	super();
    }
    
    @PostConstruct
    public void onPostConstruct() {
    	//TODO Conexion? 
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
		String auditServerRestEndpointUrl = ""; //TODO Obtener config
		MensajeAuditDTO mad = buildMensaje(nivel, mensaje);
		String body = gson.toJson(mad);
		log.info("Enviar sync a audit [" + body + "]");
		try {
			URL url = new URL(auditServerRestEndpointUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			OutputStream os = con.getOutputStream();
			os.write(body.getBytes(Charset.forName("UTF-8")));
			os.flush();
			os.close();
			
			int responseCode = con.getResponseCode();
			log.info("Envio sync a audit volvio con codigo [" + responseCode + "]");
			
			con.disconnect();
		} catch (IOException e) {
			log.log(Level.WARNING, "Error al enviar mensaje de audit sync", e);
			e.printStackTrace();
		}
	}
	
	private void enviarAuditAsync(NivelAudit nivel, String mensaje) {
		try {
			Connection con = factory.createConnection();
			Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			con.start();
			MessageProducer producer = session.createProducer(csa);
			TextMessage tm = session.createTextMessage();
			tm.setText(gson.toJson(buildMensaje(nivel, mensaje)));
			producer.send(tm);
			con.close();
		} catch (JMSException e) {
			log.log(Level.WARNING, "Error enviando audit async", e);
			e.printStackTrace();
		}
	}
	
	private MensajeAuditDTO buildMensaje(NivelAudit nivel, String mensaje) {
		SimpleDateFormat sdf = new SimpleDateFormat(MensajeAuditDTO.FORMATO_FECHA);
		String fecha = sdf.format(Calendar.getInstance().getTime());
		return new MensajeAuditDTO(fecha, nivel.toString(), "deposito", EscapeUtil.escapeJsonStringValue(mensaje));
	}
	
	private Modo getModo() {
		Modo modo = Modo.SYNC;
		//TODO obtener la config
		return modo;
	}

}
