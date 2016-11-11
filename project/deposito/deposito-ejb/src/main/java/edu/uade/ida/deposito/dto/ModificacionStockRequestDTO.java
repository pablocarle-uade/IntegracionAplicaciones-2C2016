package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class ModificacionStockRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idArticulo;
	private Integer nuevoStock;
	
	public Long getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(Long idArticulo) {
		this.idArticulo = idArticulo;
	}
	public Integer getNuevoStock() {
		return nuevoStock;
	}
	public void setNuevoStock(Integer nuevoStock) {
		this.nuevoStock = nuevoStock;
	}

}
