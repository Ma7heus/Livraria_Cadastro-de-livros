package com.livraria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Autor {
	
	@Id
	@SequenceGenerator(name = "Generator_Autor",sequenceName = "idAutor",allocationSize = 1)
	@GeneratedValue(generator = "Generator_Autor", strategy = GenerationType.SEQUENCE)
	private Long idAutor;
	
	private String nome;
	
	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros = new ArrayList<>();

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	
	
	
}
