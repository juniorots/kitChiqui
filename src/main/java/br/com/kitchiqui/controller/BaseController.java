/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import br.com.kitchiqui.modelo.Blog;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.Util;

public class BaseController {
	
	// section forwarding...	
	protected static final String DETALHE_PRODUTO = "/detalheProduto.xhtml";
	protected static final String FILTRO_PRODUTO = "/filtroProduto.xhtml";
	protected static final String PAGINA_PRINCIPAL = "/index.xhtml";
	protected static final String KIT_BLOG = "/kitBlog.xhtml";
	protected static final String RECUPERA_SENHA = "/recuperaSenha.xhtml";
	protected static final String PRIMEIRO_PASSO_COMPRAS = "/fecharCompraPasso01.xhtml";
	
	private Cliente cliente= null;
	private Produto produto = null;
	private Blog blog = null;
	private String tmpPrimeiro;
	private String tmpSegundo;
	
	public void resetarConfig() {
		try {
			tmpPrimeiro = "";
			getProduto().setTitulo("");
		} catch (Exception e) {
			// TODO nothing...
		}
	}
	
	public void paginaPrincipal(){
		resetarConfig();
		Util.forward(PAGINA_PRINCIPAL);
	}
	
	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Cliente getCliente() {
	   if ( Util.isEmpty( this.cliente ) ) {
		   this.cliente = new Cliente();
	   }
	   	return this.cliente;
	  }

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Blog getBlog() {
		if (this.blog == null) {
			blog = new Blog();
		}
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getTmpPrimeiro() {
		return tmpPrimeiro;
	}

	public void setTmpPrimeiro(String tmpPrimeiro) {
		this.tmpPrimeiro = tmpPrimeiro;
	}

	public String getTmpSegundo() {
		return tmpSegundo;
	}

	public void setTmpSegundo(String tmpSegundo) {
		this.tmpSegundo = tmpSegundo;
	}
	
}
