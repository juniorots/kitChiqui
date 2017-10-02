/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.Produto;
import lombok.Cleanup;

@ManagedBean
@SessionScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto = null;
	private Collection<Produto> listaProdutos = new ArrayList<>();
	
	public ProdutoMB() {
		
		/*
         * Trabalhando no conteudo...
         */
        @Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        
        Produto p = new Produto();
        p.setTitulo("TESTE DE PRODUTO");
        p.setSrcImagem("img/home/banner-slider/shoe1.png");
        
        this.listaProdutos.add(p);
//        this.listaProdutos.addAll( dao.selectAll() );
	}
	
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Collection<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(Collection<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
}
