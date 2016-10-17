package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class SolicitudArticuloRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idArticulo;
	private int cantidad;
	
	public SolicitudArticuloRequest() {
		super();
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
