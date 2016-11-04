package edu.uade.ida.deposito.util.config;

import java.util.List;

public class DespachosConfig {

	private List<Server> servers;
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public static class Server {
		
		private RestEndpointConfig restEndpoint;
		private JmsEndpointConfig jmsEndpoint;

		public RestEndpointConfig getRestEndpoint() {
			return restEndpoint;
		}
		public void setRestEndpoint(RestEndpointConfig restEndpoint) {
			this.restEndpoint = restEndpoint;
		}
		public JmsEndpointConfig getJmsEndpoint() {
			return jmsEndpoint;
		}
		public void setJmsEndpoint(JmsEndpointConfig jmsEndpoint) {
			this.jmsEndpoint = jmsEndpoint;
		}
	}
}
