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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.kitchiqui.base.BlogDAO;
import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.Blog;
import br.com.kitchiqui.modelo.EnumClasseProduto;
import br.com.kitchiqui.modelo.EnumTipoProduto;
import br.com.kitchiqui.modelo.Parceiro;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.Util;
import lombok.Cleanup;

@ManagedBean(name="produtoMB")
@SessionScoped
public class ProdutoMB extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Collection<Produto> listaVitrine = new ArrayList();
	private Collection<Produto> listaDestaque = new ArrayList();
	private Collection<Parceiro> listaParceiros = new ArrayList();
	private Collection<Blog> listaBlog = new ArrayList();
	private Collection<Produto> listaFiltro = new ArrayList();

	private String primeiroFiltro;
	private String segundoFiltro;
	
	private boolean bloquearFiltroEspecie;

	public ProdutoMB() {

		/*
         * Trabalhando no conteudo...
         */
        @Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
//        ParceiroDAO parDAO = new ParceiroDAO(entityManager);
        BlogDAO blogDAO = new BlogDAO(entityManager);
        
        for (Produto p : dao.selectAll()) {
        	if (p.getTipo().equals(EnumTipoProduto.PRODUTO_VITRINE.getTipo())) { 
        		this.listaVitrine.add( p );
        	}
        	if (p.getTipo().equals(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo())) { 
        		this.listaDestaque.add( p );
        	}
        }
	        // Deve-se habilitar quando novamente for inserir os parceiros no site
//	        for (Parceiro par : parDAO.selectAll()) {
//	        	this.listaParceiros.add( par );
//	        }
        
        for (Blog blog : blogDAO.selectAll()) {
        	this.getListaBlog().add(blog);
        }
	}
	
	/**
	 * Tratando da atualizacao do produto
	 */
	public void atualizarProduto() {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        if (!Util.isEmpty(getProduto().getStrPreco())) {
        	getProduto().setPreco(Double.parseDouble(getProduto().getStrPreco().replaceAll("[R$]", "").replaceAll("[,]", ".")));
        }
        dao.update(getProduto());
        entityManager.getTransaction().commit();
        
        Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Dados atualizados");
	}
	
	/**
	 * Util para utilizacao de backUp - restauracao do sistema
	 * @param produto
	 * @return
	 */
	public static Produto pesquisaBackUp(Produto produto) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        Produto retorno = null;
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        retorno = dao.findByStringField("srcImagem", produto.getSrcImagem(), true, 0, 1).get(0); 
        return retorno;
	}
	
	/**
	 * Tratando especificacao do produto
	 * @return
	 */
	public void detalharProduto() {
		
      @Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();	
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        setProduto(dao.selectById(UUID.fromString(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduto"))));
        
        Util.forward(DETALHE_PRODUTO);
	}
	
	/**
	 * Quando o operador faz uso de texto para pesquisar produtos
	 */
	public void filtrarTexto() {
        @Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
                
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        listaFiltro.clear();
        
        if (Util.isEmpty(getProduto().getTitulo())) {
        	getProduto().setTitulo("");
        }
        
        for (Produto p : dao.findByStringField("titulo", getProduto().getTitulo(), true, 0, 0)) {
        	if (!p.getTipo().equals(EnumTipoProduto.PRODUTO_VITRINE.getTipo())){
        		listaFiltro.add(p);
        	}
        }
        
        this.bloquearFiltroEspecie = true;
        Util.forward(FILTRO_PRODUTO);
	}
	
	/**
	 * Trabalhando nos filtros de produtos
	 */
	public void filtrarProduto() {
      @Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
                
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        setProduto(null);

        Object tmp = null;
        String tmp2 = "";
        try {
		    tmp = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idClasse");
		    tmp2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("especie");
        } catch (Exception e) {
        	 setTmpPrimeiro("");
        }
        
        if ( tmp2 != null && !tmp2.equals("null") && !tmp2.equals("") ) {
        	getProduto().setEspecie(Integer.parseInt(tmp2));
        } else {
        	getProduto().setEspecie(null);
        }

        contabilizarClasseProduto(dao);
        listaFiltro.clear();
        
        if ( tmp != null && !tmp.equals("null") && !tmp.equals("")) {
        	getProduto().setClasse(Integer.parseInt(tmp.toString()));
        } else {
        	getProduto().setClasse(null);
        }
        	
        Util.tratarRangePreco(getProduto(), this.primeiroFiltro, this.segundoFiltro);
        setTmpPrimeiro(this.primeiroFiltro);
        setTmpSegundo(this.segundoFiltro);
        
        for (Produto p : dao.selectUsingFilter(getProduto())) {
        	if (!p.getTipo().equals(EnumTipoProduto.PRODUTO_VITRINE.getTipo()))
        		listaFiltro.add(p);
        }
        
        this.bloquearFiltroEspecie = Util.isEmpty(getProduto().getEspecie());
		Util.forward(FILTRO_PRODUTO);
	}
	
	/**
	 * Responsavel por classificar os produtos, conforme sua tipificacao, ex: conjuntos de estojos, escovas, kits montados entre outros
	 */
	public void contabilizarClasseProduto(ProdutoDAO dao) {
		getProduto().setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
		getProduto().setClasse(EnumClasseProduto.KIT_STANDART.getClasse());
		getProduto().getContadorClasse().setQtdKitStandart(dao.selectUsingFilter(getProduto()).size());
		
		getProduto().setClasse(EnumClasseProduto.KIT_ORTODONTICO.getClasse());
		getProduto().getContadorClasse().setQtdKitOrtodontico(dao.selectUsingFilter(getProduto()).size());
		
		getProduto().setClasse(EnumClasseProduto.KIT_PERIODONTAL.getClasse());
		getProduto().getContadorClasse().setQtdKitPeriodontal(dao.selectUsingFilter(getProduto()).size());
		
		getProduto().setClasse(EnumClasseProduto.KIT_POSCIRURGICO.getClasse());
		getProduto().getContadorClasse().setQtdKitPosCirurgico(dao.selectUsingFilter(getProduto()).size());
		
		getProduto().setClasse(EnumClasseProduto.KIT_ESCOLAR.getClasse());
		getProduto().getContadorClasse().setQtdKitEscolar(dao.selectUsingFilter(getProduto()).size());
		getProduto().setClasse(null);
	}
	
	/**
	 * Responsavel por direcionar ao blog com assunto especifidado pelo operador
	 */
	public void chamarBlog() {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        BlogDAO dao = new BlogDAO(entityManager);
        setBlog(null);
        getBlog().setId(UUID.fromString( (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idBlog"))) );
        setBlog(dao.selectUsingFilter(getBlog()).get(0));
		
		Util.forward(KIT_BLOG);
	}
	
	public boolean isBloquearFiltroEspecie() {
		return bloquearFiltroEspecie;
	}

	public void setBloquearFiltroEspecie(boolean bloquearFiltroEspecie) {
		this.bloquearFiltroEspecie = bloquearFiltroEspecie;
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

	public String getPrimeiroFiltro() {
		return primeiroFiltro;
	}

	public void setPrimeiroFiltro(String primeiroFiltro) {
		this.primeiroFiltro = primeiroFiltro;
	}

	public String getSegundoFiltro() {
		return segundoFiltro;
	}

	public void setSegundoFiltro(String segundoFiltro) {
		this.segundoFiltro = segundoFiltro;
	}

	public Collection<Blog> getListaBlog() {
		return listaBlog;
	}

	public void setListaBlog(Collection<Blog> listaBlog) {
		this.listaBlog = listaBlog;
	}
}
