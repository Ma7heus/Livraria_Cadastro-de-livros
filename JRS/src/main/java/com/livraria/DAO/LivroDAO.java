package com.livraria.DAO;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.livraria.Livro;

@Stateless
public class LivroDAO extends GenericDAO<Livro, Long> implements Serializable {

	public LivroDAO() {
		super(Livro.class);
	}

}
