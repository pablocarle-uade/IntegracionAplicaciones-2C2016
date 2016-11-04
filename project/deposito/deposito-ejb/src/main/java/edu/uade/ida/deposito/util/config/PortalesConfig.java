package edu.uade.ida.deposito.util.config;

import java.util.List;

public class PortalesConfig {

	private List<Server> servers;
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public static class Server {
		private String id;
		private JmsEndpointConfig jmsEndpoint;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public JmsEndpointConfig getJmsEndpoint() {
			return jmsEndpoint;
		}
		public void setJmsEndpoint(JmsEndpointConfig jmsEndpoint) {
			this.jmsEndpoint = jmsEndpoint;
		}
	}
}
