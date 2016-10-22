package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class SolicitudArticuloRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idModulo;
	private String idArticulo;
	private int cantidad;
	
	public SolicitudArticuloRequest() {
		super();
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
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
}
