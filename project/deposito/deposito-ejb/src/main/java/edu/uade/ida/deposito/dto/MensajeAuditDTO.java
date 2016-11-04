package edu.uade.ida.deposito.dto;

import java.io.Serializable;

public class MensajeAuditDTO implements Serializable {

	public static final String FORMATO_FECHA = "yyyy-MM-dd'T'hh:mm:ss";
	
	private static final long serialVersionUID = 1L;
	
	private String fecha;
	private String tipo;
	private String modulo;
	private String descripcion;
	
	public MensajeAuditDTO(String fecha, String tipo, String modulo, String descripcion) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.modulo = modulo;
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
