package com.livraria;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.livraria.DAO.AutorDAO;
import com.livraria.DAO.LivroDAO;

@ManagedBean
public class LivroBean {
	@Inject
	LivroDAO livroDAO;
	@Inject 
	AutorDAO autorDAO;

	private Livro livro = new Livro();

	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores(){
		return autorDAO.buscarTodos();
	}

	public void gravar() {
		System.out.println("Gravou com sucesso"+ this.livro.getTitulo());
		livroDAO.inserir(livro);
	}
}
