package com.livraria.beans;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.livraria.Autor;
import com.livraria.Livro;
import com.livraria.DAO.AutorDAO;

@ManagedBean
public class AutorBeans {
	@Inject
	AutorDAO autorDAO;

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}


	public void gravar() {
		System.out.println("Gravou com sucesso "+ this.autor.getNome());
		autorDAO.inserir(autor);
		this.autor = new Autor();
	}
}
