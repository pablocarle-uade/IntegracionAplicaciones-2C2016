package edu.uade.ida.deposito.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

public class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID> {

	public GenericRepositoryImpl() {
		super();
	}

	@Override
	public T buscarPorId(Class<T> clasz, ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> buscarTodos(Class<T> clasz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> buscarMuchos(Query q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T buscarUno(Query q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardar(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(T t) {
		// TODO Auto-generated method stub
		
	}
}
