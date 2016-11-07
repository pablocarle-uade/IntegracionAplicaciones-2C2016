package edu.uade.ida.deposito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import edu.uade.ida.deposito.dto.EntregaArticuloDTO;
import edu.uade.ida.deposito.util.HasDTO;

@Entity
public class EntregaArticulo implements HasDTO<EntregaArticuloDTO> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEntregaArticulo;
	@ManyToOne
	private SolicitudArticulo sa;
	@Column
	private int cantidadEntrega;
	@Column
	private String idModuloSolicitante;
	
	public EntregaArticulo() {
		super();
	}
	
	public EntregaArticulo(SolicitudArticulo sa, int cantidadEntrega, String idModuloSolicitante) {
		super();
		this.sa = sa;
		this.cantidadEntrega = cantidadEntrega;
		this.idModuloSolicitante = idModuloSolicitante;
	}
	
	public int getIdEntregaArticulo() {
		return idEntregaArticulo;
	}

	public void setIdEntregaArticulo(int idEntregaArticulo) {
		this.idEntregaArticulo = idEntregaArticulo;
	}

	public SolicitudArticulo getSa() {
		return sa;
	}

	public void setSa(SolicitudArticulo sa) {
		this.sa = sa;
	}

	public int getCantidadEntrega() {
		return cantidadEntrega;
	}

	public void setCantidadEntrega(int cantidadEntrega) {
		this.cantidadEntrega = cantidadEntrega;
	}
	
	public String getIdModuloSolicitante() {
		return idModuloSolicitante;
	}

	public void setIdModuloSolicitante(String idModuloSolicitante) {
		this.idModuloSolicitante = idModuloSolicitante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEntregaArticulo;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntregaArticulo other = (EntregaArticulo) obj;
		if (idEntregaArticulo != other.idEntregaArticulo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EntregaArticulo [idEntregaArticulo=" + idEntregaArticulo + ", sa=" + sa + ", cantidadEntrega="
				+ cantidadEntrega + "]";
	}

	@Override
	public EntregaArticuloDTO getDTO() {
		EntregaArticuloDTO dto = new EntregaArticuloDTO();
		dto.setCantidadAsignada(cantidadEntrega);
		dto.setIdArticulo(sa.getArticulo().getId().intValue());
		dto.setCodArticulo(Integer.valueOf(sa.getArticulo().getCodArticulo()));
		dto.setIdSolicitudArticulo(sa.getIdSolicitudStock());
		return dto;
	}
}
