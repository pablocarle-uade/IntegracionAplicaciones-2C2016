package edu.uade.ida.deposito.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import edu.uade.ida.deposito.dto.SolicitudArticuloDTO;
import edu.uade.ida.deposito.util.HasDTO;

@Entity
public class SolicitudArticulo implements HasDTO<SolicitudArticuloDTO> {
	
	public static final transient String ESTADO_PENDIENTE = "pendiente";
	public static final transient String ESTADO_NO_CUMPLIDO = "no_cumplido";
	public static final transient String ESTADO_ENTREGADO = "entregado";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSolicitudStock;
	@Column
	private String estado;
	@Column
	private Date fechaCreacion = Calendar.getInstance().getTime();
	@ManyToOne
	private Articulo articulo;
	@Column
	private int cantidad;
	@Column(nullable=false)
	private String idModuloSolicitante;
	@OneToMany(cascade=CascadeType.ALL)
	private List<EntregaArticulo> entregas;
	
	public SolicitudArticulo() {
		super();
	}
	
	public SolicitudArticulo(Articulo articulo, int cantidad, String estado, String idModuloSolicitante) {
		super();
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.estado = estado;
		this.idModuloSolicitante = idModuloSolicitante;
	}

	public int getIdSolicitudStock() {
		return idSolicitudStock;
	}

	public void setIdSolicitudStock(int idSolicitudStock) {
		this.idSolicitudStock = idSolicitudStock;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getIdModuloSolicitante() {
		return idModuloSolicitante;
	}

	public void setIdModuloSolicitante(String idModuloSolicitante) {
		this.idModuloSolicitante = idModuloSolicitante;
	}

	@Override
	public String toString() {
		return "SolicitudArticulo [idSolicitudStock=" + idSolicitudStock + ", estado=" + estado + ", articulo="
				+ articulo + ", cantidad=" + cantidad + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaCreacion == null) ? 0 : fechaCreacion.hashCode());
		result = prime * result + idSolicitudStock;
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
		SolicitudArticulo other = (SolicitudArticulo) obj;
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null)
				return false;
		} else if (!fechaCreacion.equals(other.fechaCreacion))
			return false;
		if (idSolicitudStock != other.idSolicitudStock)
			return false;
		return true;
	}

	@Override
	public SolicitudArticuloDTO getDTO() { 
		return new SolicitudArticuloDTO(idSolicitudStock, articulo.getDTO(), estado, fechaCreacion, cantidad);
	}
}
