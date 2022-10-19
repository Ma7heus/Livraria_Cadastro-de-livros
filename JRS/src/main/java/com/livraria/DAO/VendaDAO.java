package com.livraria.DAO;

import java.io.Serializable;

import com.livraria.Venda;

public class VendaDAO extends GenericDAO<Venda,Long> implements Serializable{

	public VendaDAO() {
		super(Venda.class);
	}

}
