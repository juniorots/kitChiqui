/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Cleanup;
import br.com.kitchiqui.base.ParceiroDAO;
import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.EnumTipoProduto;
import br.com.kitchiqui.modelo.Parceiro;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.Util;

@ManagedBean
@SessionScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto = null;
	private Collection<Produto> listaVitrine = new ArrayList();
	private Collection<Produto> listaDestaque = new ArrayList();
	private Collection<Parceiro> listaParceiros = new ArrayList();
	private Collection<Produto> listaFiltro = new ArrayList();

	// section forwarding...	
	private static final String DETALHE_PRODUTO = "/detalheProduto.xhtml";
	private static final String PAGINA_PRINCIPAL = "/index.xhtml";
	
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
        ParceiroDAO parDAO = new ParceiroDAO(entityManager);
        
        for (Produto p : dao.selectAll()) {
        	if (p.getTipo().equals(EnumTipoProduto.PRODUTO_VITRINE.getTipo())) { 
        		this.listaVitrine.add( p );
        	}
        	if (p.getTipo().equals(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo())) { 
        		this.listaDestaque.add( p );
        	}
        }
        
        for (Parceiro par : parDAO.selectAll()) {
        	this.listaParceiros.add( par );
        }
	}
	
	/**
	 * Tratando especificacao do produto
	 * @return
	 */
	public void detalharProduto() {
		
		@Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        this.produto = dao.selectById(UUID.fromString(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduto")));
        
		Util.forward(DETALHE_PRODUTO);
	}
	
	public void paginaPrincipal(){
		Util.forward(PAGINA_PRINCIPAL);
	}
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Collection<Produto> getListaFiltro() {
		return listaFiltro;
	}

	public void setListaFiltro(Collection<Produto> listaFiltro) {
		this.listaFiltro = listaFiltro;
	}

	public Collection<Parceiro> getListaParceiros() {
		return listaParceiros;
	}

	public void setListaParceiros(Collection<Parceiro> listaParceiros) {
		this.listaParceiros = listaParceiros;
	}

	public Collection<Produto> getListaDestaque() {
		return listaDestaque;
	}

	public void setListaDestaque(Collection<Produto> listaDestaque) {
		this.listaDestaque = listaDestaque;
	}

	public Collection<Produto> getListaVitrine() {
		return listaVitrine;
	}
	public void setListaVitrine(Collection<Produto> listaVitrine) {
		this.listaVitrine = listaVitrine;
	}
	
}
