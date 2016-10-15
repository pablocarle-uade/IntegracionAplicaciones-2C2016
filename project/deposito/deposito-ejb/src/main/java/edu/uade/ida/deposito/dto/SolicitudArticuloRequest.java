package edu.uade.ida.deposito.dto;

public class SolicitudArticuloRequest {

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
