package com.livraria.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.livraria.Usuario;
import com.livraria.DAO.UsuarioDAO;

@Named
@ViewScoped
public class LoginBean  implements Serializable{
	@Inject
	UsuarioDAO usuarioDAO;
	private Long usuarioID;

	private Usuario usuario = new Usuario();

	public String logar() {
		System.out.println("Fazendo login do usuario " + this.usuario.getEmail());
		if (autenticaUsuario()) {
			//gravar informação de usuario autenticado  = true
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			
			return "livro?faces-redirect=true";
		}

		return null;
	}

	private boolean autenticaUsuario() {
		List<Usuario> listaUsuario = usuarioDAO.buscarTodos();
		boolean emailExiste = false;
		boolean senhaCorreta = false;
		for (Usuario u : listaUsuario) {
			if (u.getEmail().equals(this.usuario.getEmail())) {
				emailExiste = true;
				if (u.getSenha().equals(usuario.getSenha())) {
					senhaCorreta = true;
				}
			}
		}
		if (emailExiste == false) {
			FacesContext.getCurrentInstance().addMessage("usuario",
					new FacesMessage("Usuario " + this.usuario.getEmail() + " nao cadastrado!"));
			return false;
		}
		if (senhaCorreta == false) {
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Senha incorreta!"));
			return false;
		}
		return true;
	}

	public void cadastrar() {
		List<Usuario> listaUsuario = usuarioDAO.buscarTodos();
		boolean usuarioExiste = false;
		for (Usuario u : listaUsuario) {
			if (u.getEmail().equals(this.usuario.getEmail())) {
				usuarioExiste = true;
				break;
			}
		}
		if (usuarioExiste) {
			FacesContext.getCurrentInstance().addMessage("usuario",
					new FacesMessage("O usuario " + this.usuario.getEmail() + " Ja existe."));
		} else {
			System.out.println("Cadastrando usuario " + this.usuario.getEmail());
			usuarioDAO.inserir(this.usuario);
			FacesContext.getCurrentInstance().addMessage("usuario",
					new FacesMessage("Usuario " + this.usuario.getEmail() + " cadastrado."));
			this.usuario = new Usuario();
		}

	}
	
	
	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");		
		
		return "login?faces-redirect=true";
	}
	
	
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

}
