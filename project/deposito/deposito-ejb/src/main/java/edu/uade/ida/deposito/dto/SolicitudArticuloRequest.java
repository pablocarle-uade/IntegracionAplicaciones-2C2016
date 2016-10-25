package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class SolicitudArticuloRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idModulo;
	private String codArticulo;
	private int cantidad;
	
	public SolicitudArticuloRequest() {
		super();
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}
}
