package edu.uade.ida.deposito.util.config;

import java.util.List;

public class LogisticaMonitoreoConfig {
	
	private List<Server> servers;
	
	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public static class Server {
		
		private String id;
		private String modo;
		private JmsEndpointConfig asyncParams;
		private RestEndpointConfig syncParams;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getModo() {
			return modo;
		}
		public void setModo(String modo) {
			this.modo = modo;
		}
		public JmsEndpointConfig getAsyncParams() {
			return asyncParams;
		}
		public void setAsyncParams(JmsEndpointConfig asyncParams) {
			this.asyncParams = asyncParams;
		}
		public RestEndpointConfig getSyncParams() {
			return syncParams;
		}
		public void setSyncParams(RestEndpointConfig syncParams) {
			this.syncParams = syncParams;
		}
	}
}
