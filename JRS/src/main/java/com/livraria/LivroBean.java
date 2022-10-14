package com.livraria;

import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
	
	public List<Livro> getLivros() {
		return livroDAO.buscarTodos();
	}
	
	public void gravarAutor() {
		Autor autor = autorDAO.buscarPorId(this.autorID);		
		livro.addAutor(autor);
		System.out.println("Autor gravado com sucesso!");			
	}
	
	public void gravar() {
		System.out.println("Gravou com sucesso "+ this.livro.getTitulo());
		if (this.livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("É necessario informar um autor para o livro!"));
		}
		
		if (Objects.isNull(this.livro.getIdLivro())) {
			livroDAO.inserir(this.livro);
			FacesContext.getCurrentInstance().addMessage("livro", new FacesMessage("Livro cadastrado com sucesso!"));
		}else{
			livroDAO.update(this.livro);
			FacesContext.getCurrentInstance().addMessage("livro", new FacesMessage("Livro alterado com sucesso!"));
		}		
		this.livro = new Livro();
	}
	
	
	public void removerAutorDolivro(Autor autor) {
		this.livro.removeAutor(autor);
	}	
	
	public void remover(Livro livro) {
		System.out.println("Removendo livro!");
		livroDAO.delete(livro.getIdLivro());
	}	
	
	public void alterar(Livro livro) {
		System.out.println("Atualizando livro");
		this.livro = livro;
	}	
	
	public Long getAutorID() {
		return autorID;
	}

	public void setAutorID(Long autorID) {
		this.autorID = autorID;
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value ) throws ValidatorException {
		String valor  =  value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 1"));
		}
		
	}
	
	public String formAutor() {
		System.out.println("Chamando o formulario do autor!");
		return "autor?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
}
