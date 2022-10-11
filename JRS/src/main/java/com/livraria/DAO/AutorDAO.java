package com.livraria.DAO;

import javax.ejb.Stateless;

import com.livraria.Autor;

@Stateless
public class AutorDAO extends GenericDAO<Autor, Long> {

	public AutorDAO() {
		super(Autor.class);
	}

}
