package ar.edu.uade.ia.grupo1.deposito.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Articulo {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private 
	Long id;
	private String descripcion;
	
	public Articulo(String descripcion) {
		this.setDescripcion(descripcion); 
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
