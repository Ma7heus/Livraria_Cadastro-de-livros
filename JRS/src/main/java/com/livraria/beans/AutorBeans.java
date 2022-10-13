package com.livraria.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.livraria.Autor;
import com.livraria.Livro;
import com.livraria.DAO.AutorDAO;
import com.livraria.util.RedirectView;

@ManagedBean
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
		
		System.out.println("Gravou com sucesso "+ this.autor.getNome());
		autorDAO.inserir(autor);
		this.autor = new Autor();
		return new RedirectView("livro");
	}
	
	
	
}
