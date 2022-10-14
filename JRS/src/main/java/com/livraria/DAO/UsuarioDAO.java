package com.livraria.DAO;

import java.util.Objects;

import javax.ejb.Stateless;

import com.livraria.Usuario;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario, Long> {

	public UsuarioDAO() {
		super(Usuario.class);
	}
		
}
