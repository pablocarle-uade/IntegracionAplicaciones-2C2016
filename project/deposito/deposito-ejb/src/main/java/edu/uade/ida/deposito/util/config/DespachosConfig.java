package edu.uade.ida.deposito.util.config;

import java.util.ArrayList;
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
		
		private String id;
		private RestEndpointConfig restEndpoint;
		private JmsEndpointConfig jmsEndpoint;

		public RestEndpointConfig getRestEndpoint() {
			return restEndpoint;
		}
		public JmsEndpointConfig getJmsEndpoint() {
			return jmsEndpoint;
		}
		public String getId() {
			return id;
		}
		public boolean hasAsync() {
			return jmsEndpoint != null;
		}
	}

	public List<JmsEndpointConfig> getAsyncServers() {
		List<JmsEndpointConfig> retList = new ArrayList<>();
		if (servers != null) {
			for (Server s : servers) {
				if (s.hasAsync()) {
					retList.add(s.getJmsEndpoint());
				}
			}
		}
		return retList;
	}
}
