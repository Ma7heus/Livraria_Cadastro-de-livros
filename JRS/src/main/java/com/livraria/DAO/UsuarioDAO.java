package com.livraria.DAO;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.Stateless;

import com.livraria.Usuario;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario, Long> implements Serializable{

	public UsuarioDAO() {
		super(Usuario.class);
	}
		
}
