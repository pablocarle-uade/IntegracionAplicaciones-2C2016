package com.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Articulo {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	Long id;
	String descripcion;
	
	public Articulo(String descripcion) {
		this.descripcion = descripcion; 
	}
}
