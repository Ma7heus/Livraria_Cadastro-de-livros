package com.livraria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario_livraria")
public class Usuario {
	
	@Id
	@SequenceGenerator(name = "Generator_Usuario",sequenceName = "idUsuario",allocationSize = 1)
	@GeneratedValue(generator = "Generator_Usuario", strategy = GenerationType.SEQUENCE)
	private Long idUsuario;		
	private String email;
	private String senha;
	
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	
}
