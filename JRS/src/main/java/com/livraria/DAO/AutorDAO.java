package com.livraria.DAO;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.livraria.Autor;

@Stateless
public class AutorDAO extends GenericDAO<Autor, Long> implements Serializable {

	public AutorDAO() {
		super(Autor.class);
	}

}
