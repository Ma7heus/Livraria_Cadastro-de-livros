package com.livraria.DAO;

import javax.ejb.Stateless;

import com.livraria.Livro;

@Stateless
public class LivroDAO extends GenericDAO<Livro, Long> {

	public LivroDAO() {
		super(Livro.class);
	}

}
