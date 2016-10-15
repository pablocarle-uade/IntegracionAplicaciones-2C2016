package edu.uade.ida.deposito.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

public interface GenericRepository<T, ID extends Serializable> {
	
	public T buscarPorId(Class<T> clasz, ID id);
	
	public List<T> buscarTodos(Class<T> clasz);
	
	public List<T> buscarMuchos(Query q);
	
	public T buscarUno(Query q);
	
	public void guardar(T t);
	
	public void actualizar(T t);
	
	public void eliminar(T t);
	
}
