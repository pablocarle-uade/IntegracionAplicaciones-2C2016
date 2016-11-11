package edu.uade.ida.deposito.rest;

import java.io.Serializable;

public class GenericResponseDTO implements Serializable {
	
	private static final long serialVersionUID = -4085454396383153752L;
	
	private String status = "OK";

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}