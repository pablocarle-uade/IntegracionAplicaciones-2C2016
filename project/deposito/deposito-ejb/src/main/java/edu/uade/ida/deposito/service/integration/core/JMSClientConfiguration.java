package edu.uade.ida.deposito.service.integration.core;

public class JMSClientConfiguration {

	private String message;
	private String destination;
	private String providerUrl;
	private String user;
	private String password;
	
	public JMSClientConfiguration(String message, String destination, String providerUrl, String user, String password) {
		this.message = message;
		this.destination = destination;
		this.providerUrl = providerUrl;
		this.user = user;
		this.password = password;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
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
