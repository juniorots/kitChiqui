package br.com.kitchiqui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.Produto;
import lombok.Cleanup;

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
        this.listaProdutos.addAll( dao.selectAll() );
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
