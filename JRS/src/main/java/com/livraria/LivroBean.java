package com.livraria;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.livraria.DAO.AutorDAO;
import com.livraria.DAO.LivroDAO;

@ManagedBean
@ViewScoped
public class LivroBean {
	@Inject
	LivroDAO livroDAO;
	@Inject 
	AutorDAO autorDAO;

	private Livro livro = new Livro();
	private Long autorID;

	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores(){
		return autorDAO.buscarTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public void gravarAutor() {
		Autor autor = autorDAO.buscarPorId(this.autorID);		
		livro.addAutor(autor);
		System.out.println("Autor gravado com sucesso!");			
	}
	
	public void gravar() {
		System.out.println("Gravou com sucesso"+ this.livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("É necessario informar um autor para o livro!");
		}		
		livroDAO.inserir(livro);
	}

	public Long getAutorID() {
		return autorID;
	}

	public void setAutorID(Long autorID) {
		this.autorID = autorID;
	}

	
}
