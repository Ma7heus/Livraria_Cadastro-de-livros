package com.livraria.DAO;

import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.livraria.Usuario;

@Stateless
public abstract class GenericDAO<T,ID> {

	@PersistenceContext
	EntityManager entityManager;
	
	public Class<T> clazz;

	public GenericDAO(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public void inserir(T entidade) {
		entityManager.persist(entidade);
	}
	public void delete(ID idEntidade) {
		T entidade = entityManager.find(clazz, idEntidade);
		entityManager.remove(entidade);
	}
	public T buscarPorId(ID idEntidade) {
		return entityManager.find(clazz,idEntidade);
	}
	public void update(T entidade) {
		entityManager.merge(entidade);
	}
	public List<T> buscarTodos(){
		return entityManager.createQuery( " select entidade from " + clazz.getSimpleName() + " entidade ",
				clazz).getResultList();
	}
	
	
	
	
}
