package edu.uade.ida.deposito.data;

import java.io.Serializable;

public class SolicitudArticuloRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codArticulo;
	private int cantidad;

	public SolicitudArticuloRequest() {
		super();
	}
	
	public SolicitudArticuloRequest(int codArticulo, int cantidad) {
		super();
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "SolicitudArticuloRequest [codArticulo=" + codArticulo + ", cantidad=" + cantidad + "]";
	}

	public int getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
