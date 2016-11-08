package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class RecepcionCompraItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int codArticulo;
	private int cantidadEntregada;
	
	public RecepcionCompraItemDTO() {
		super();
	}
	
	public RecepcionCompraItemDTO(int codArticulo, int cantidadEntregada) {
		super();
		this.codArticulo = codArticulo;
		this.cantidadEntregada = cantidadEntregada;
	}

	public int getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}

	public int getCantidadEntregada() {
		return cantidadEntregada;
	}

	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}
}
