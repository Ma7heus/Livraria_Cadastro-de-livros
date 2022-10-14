package com.livraria.beans;

import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.livraria.Autor;
import com.livraria.Livro;
import com.livraria.DAO.AutorDAO;
import com.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBeans {
	@Inject
	AutorDAO autorDAO;

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
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
	}

	public void alterar(Autor autor) {
		this.autor = autor;
	}
	
}
