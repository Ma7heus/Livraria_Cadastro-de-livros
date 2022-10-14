package com.livraria.beans;

import java.util.List;
import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.livraria.Usuario;
import com.livraria.DAO.UsuarioDAO;

@ManagedBean
@ViewScoped
public class LoginBean {
	@Inject
	UsuarioDAO usuarioDAO;
	private Long usuarioID;

	private Usuario usuario = new Usuario();

	public String logar() {
		System.out.println("Fazendo login do usuario " + this.usuario.getEmail());
		if (autenticaUsuario()) {
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
