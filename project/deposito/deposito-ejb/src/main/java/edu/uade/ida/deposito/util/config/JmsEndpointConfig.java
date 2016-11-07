package edu.uade.ida.deposito.util.config;

public class JmsEndpointConfig {

	private String jmsQueue;
	private String jmsTopic;
	private String user;
	private String password;
	
	public JmsEndpointConfig() {
		super();
	}

	public String getJmsQueue() {
		return jmsQueue;
	}

	public String getJmsTopic() {
		return jmsTopic;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
