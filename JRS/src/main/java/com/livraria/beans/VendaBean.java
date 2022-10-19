package com.livraria.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.livraria.Livro;
import com.livraria.Venda;
import com.livraria.DAO.LivroDAO;
import com.livraria.DAO.VendaDAO;


@Named
@ViewScoped
public class VendaBean implements Serializable{
	
	@Inject
	VendaDAO vendaDAO;
	@Inject
	LivroDAO livroDAO;
	
	
	
	public BarChartModel getVendasModel() {

	    BarChartModel model = new BarChartModel();
	    model.setAnimate(true);

	    ChartSeries vendaSerie = new ChartSeries();
	    vendaSerie.setLabel("Vendas 2016");
	    List<Venda> vendas = getVendas();
	    	for (Venda venda : vendas) {
				vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
			}
	    
	    model.addSeries(vendaSerie);
	    
	    
	    
	

	    return model;
	}
	

	public List<Venda> getVendas(){
		List<Livro> livros = livroDAO.buscarTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		
		for (Livro livro : livros) {
			Random random = new Random(1234);
			Integer quantidade = random.nextInt(500);	
			
			vendas.add(new Venda(livro,quantidade));
		}		
		return vendas;		
	}
	
}
