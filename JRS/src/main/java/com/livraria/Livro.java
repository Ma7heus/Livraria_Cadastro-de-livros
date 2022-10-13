package com.livraria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro {
	
	@Id
	@SequenceGenerator(name = "generator_livro",sequenceName = "idLivro",allocationSize = 1)
	@GeneratedValue(generator = "generator_livro",strategy = GenerationType.SEQUENCE )
	private Long idLivro;
	
	
	private String titulo;
	
	private String isbn;
	
	@Column (precision = 18, scale = 2)
	private BigDecimal preco;
	@Temporal(TemporalType.DATE)
	private Calendar datalancamento = Calendar.getInstance();

	@ManyToMany
	@JoinTable(name = "livro_autores", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn (name = "autor_id"))
	private List<Autor> autores = new ArrayList<>();

	public Livro() {
	}

	
	public List<Autor> getAutores() {
		return autores;
	}


	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}


	public Long getIdLivro() {
		return idLivro;
	}


	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public void addAutor(Autor autor) {
		this.autores.add(autor);
		
	}
	
	public Calendar getDatalancamento() {
		return datalancamento;
	}

	public void setDatalancamento(Calendar datalancamento) {
		this.datalancamento = datalancamento;
	}
}