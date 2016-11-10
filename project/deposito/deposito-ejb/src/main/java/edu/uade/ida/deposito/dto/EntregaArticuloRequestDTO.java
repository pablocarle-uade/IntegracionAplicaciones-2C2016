package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class EntregaArticuloRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idDeposito;
	private int codArticulo;
	private int cantidad;

	public EntregaArticuloRequestDTO() {
	}
	
	public EntregaArticuloRequestDTO(String idDeposito, int codArticulo, int cantidad) {
		super();
		this.idDeposito = idDeposito;
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
	}
	
	public String getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
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
}
