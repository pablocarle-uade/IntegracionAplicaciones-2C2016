package edu.uade.ida.deposito.dto;

import java.io.Serializable;
import java.util.List;

public class RecepcionCompraDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRecepcionCompra;
	private int idSolicitudCompra;

	private List<RecepcionCompraItemDTO> items;
	
	public RecepcionCompraDTO() {
		super();
	}

	public RecepcionCompraDTO(int idRecepcionCompra, int idSolicitudCompra, List<RecepcionCompraItemDTO> items) {
		super();
		this.idRecepcionCompra = idRecepcionCompra;
		this.idSolicitudCompra = idSolicitudCompra;
		this.items = items;
	}

	public int getIdRecepcionCompra() {
		return idRecepcionCompra;
	}

	public void setIdRecepcionCompra(int idRecepcionCompra) {
		this.idRecepcionCompra = idRecepcionCompra;
	}

	public int getIdSolicitudCompra() {
		return idSolicitudCompra;
	}

	public void setIdSolicitudCompra(int idSolicitudCompra) {
		this.idSolicitudCompra = idSolicitudCompra;
	}

	public List<RecepcionCompraItemDTO> getItems() {
		return items;
	}

	public void setItems(List<RecepcionCompraItemDTO> items) {
		this.items = items;
	}
}
