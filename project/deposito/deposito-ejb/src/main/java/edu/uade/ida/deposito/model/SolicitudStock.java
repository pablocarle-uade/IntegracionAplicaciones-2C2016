package edu.uade.ida.deposito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class SolicitudStock {
	
	@Id
	private int idSolicitudStock;
	@OneToOne
	private EstadoSolicitudStock estado;
	
	public SolicitudStock() {
		super();
	}

}
