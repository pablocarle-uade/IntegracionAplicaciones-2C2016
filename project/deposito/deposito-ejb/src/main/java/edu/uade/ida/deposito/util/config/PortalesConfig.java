package edu.uade.ida.deposito.util.config;

import java.util.ArrayList;
import java.util.List;

public class PortalesConfig {

	private List<Server> servers;
	
	public List<Server> getServers() {
		return servers;
	}

	public static class Server {
		private String id;
		private JmsEndpointConfig jmsEndpoint;
		
		public String getId() {
			return id;
		}
		public JmsEndpointConfig getJmsEndpoint() {
			return jmsEndpoint;
		}
		public boolean hasAsync() {
			// TODO Auto-generated method stub
			return false;
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
