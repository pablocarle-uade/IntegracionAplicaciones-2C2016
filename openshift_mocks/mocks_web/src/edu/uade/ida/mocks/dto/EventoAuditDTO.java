package edu.uade.ida.mocks.dto;

public class EventoAuditDTO {

	private String fecha;
	private String tipo;
	private String modulo;
	private String descripcion;

	public EventoAuditDTO() {
		super();
	}
	
	public EventoAuditDTO(String fecha, String tipo, String modulo, String descripcion) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.modulo = modulo;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EventoAuditDTO [fecha=" + fecha + ", tipo=" + tipo + ", modulo=" + modulo + ", descripcion="
				+ descripcion + "]";
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
