package com.livraria.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.livraria.Autor;
import com.livraria.DAO.AutorDAO;
import com.livraria.util.RedirectView;

@Named
@ViewScoped //javax.faces.view.ViewScoped; - nao confundir
public class AutorBeans implements Serializable{
	@Inject
	AutorDAO autorDAO;

	private Autor autor = new Autor();
	private Long autorId;
	

	public Autor getAutor() {
		return autor;
	}
	
	public void carregaAutorPeloId() {
		this.autor = autorDAO.buscarPorId(autorId);
	}
	

	public RedirectView gravar() {
		if (this.autor.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("É necessario informar o nome do autor!"));
		}		
		
		if (Objects.isNull(this.autor.getIdAutor())) {
			autorDAO.inserir(this.autor);
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Novo autor cadastrado com sucesso!"));
		}else {
			autorDAO.update(this.autor);
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Autor atualizado com sucesso!"));
		}
		this.autor = new Autor();
		return new RedirectView("livro");
	}
	
	public List<Autor> getAutores(){
		System.out.println("Buscando todos os Autores");
		return autorDAO.buscarTodos();
	}
	
	public void remover(Autor autor) {
		System.out.println("Removendo autor no banco");
		autorDAO.delete(autor.getIdAutor());
		FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Autor removido com sucesso!"));
	}

	public void alterar(Autor autor) {
		this.autor = autor;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

}
