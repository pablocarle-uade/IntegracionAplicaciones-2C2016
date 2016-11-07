package edu.uade.ida.deposito.util.config;

import java.util.ArrayList;
import java.util.List;

public class LogisticaMonitoreoConfig {
	
	private List<Server> servers;
	
	public List<Server> getServers() {
		return servers;
	}

	public static class Server {
		
		private String id;
		private String modo;
		private JmsEndpointConfig asyncParams;
		private RestEndpointConfig syncParams;
		
		public String getId() {
			return id;
		}
		public String getModo() {
			return modo;
		}
		public JmsEndpointConfig getAsyncParams() {
			return asyncParams;
		}
		public RestEndpointConfig getSyncParams() {
			return syncParams;
		}
		public boolean hasAsync() {
			return asyncParams != null;
		}
	}

	public List<JmsEndpointConfig> getAsyncServers() {
		List<JmsEndpointConfig> retList = new ArrayList<>();
		if (servers != null) {
			for (Server s : servers) {
				if (s.hasAsync()) {
					retList.add(s.getAsyncParams());
				}
			}
		}
		return retList;
	}

	public boolean isAsync() {
		if (servers != null && !servers.isEmpty()) {
			return servers.get(0).modo.equals("async");
		}
		return false;
	}
}
