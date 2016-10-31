package edu.uade.ida.deposito.model;

public enum TipoDeArticulo {
    ELECTRODOMESTICO("ELECTRO"), MODA("MODA"), MUEBLE("MUEBLE"), NIÑOS("NIÑOS");
	
	private String nombre;
	
	private TipoDeArticulo(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}