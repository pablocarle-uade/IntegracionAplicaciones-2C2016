package edu.uade.ida.deposito.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SolicitudStock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSolicitudStock;
	@OneToOne
	private EstadoSolicitudStock estado;
	
	public SolicitudStock() {
		super();
	}
	
	public SolicitudStock(EstadoSolicitudStock estado) {
		super();
		this.estado = estado;
	}

	public int getIdSolicitudStock() {
		return idSolicitudStock;
	}

	public EstadoSolicitudStock getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitudStock estado) {
		this.estado = estado;
	}

	public void setIdSolicitudStock(int idSolicitudStock) {
		this.idSolicitudStock = idSolicitudStock;
	}
}
