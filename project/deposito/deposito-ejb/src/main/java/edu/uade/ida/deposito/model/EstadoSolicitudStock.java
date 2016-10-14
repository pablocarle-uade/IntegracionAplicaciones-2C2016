package edu.uade.ida.deposito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EstadoSolicitudStock {

	@Id
	private int idEstadoSolicitudStock;
	@Column
	private String nombre;
	
	public EstadoSolicitudStock() {
		super();
	}

	public int getIdEstadoSolicitudStock() {
		return idEstadoSolicitudStock;
	}

	public void setIdEstadoSolicitudStock(int idEstadoSolicitudStock) {
		this.idEstadoSolicitudStock = idEstadoSolicitudStock;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
