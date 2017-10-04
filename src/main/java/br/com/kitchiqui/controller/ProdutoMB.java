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
import br.com.kitchiqui.modelo.EnumTipoProduto;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.CargaInicial;
import lombok.Cleanup;

@ManagedBean
@SessionScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto = null;
	private Collection<Produto> listaProdutos = new ArrayList<>();
	
	public ProdutoMB() {

		// Trabalhando na carga inicial
		CargaInicial.carregarVitrinePrincipal();
		
		/*
         * Trabalhando no conteudo...
         */
        @Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        
        for (Produto p : dao.selectAll()) {
        	if (p.getTipo().equals(EnumTipoProduto.PRODUTO_VITRINE.getTipo())) { 
        		this.listaProdutos.addAll( dao.selectAll() );
        	}
        }
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
