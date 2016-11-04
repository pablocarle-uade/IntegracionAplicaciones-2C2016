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

	public void setJmsQueue(String jmsQueue) {
		this.jmsQueue = jmsQueue;
	}

	public String getJmsTopic() {
		return jmsTopic;
	}

	public void setJmsTopic(String jmsTopic) {
		this.jmsTopic = jmsTopic;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
