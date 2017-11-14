/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.util.Util;

public class BaseController {
	
	// section forwarding...	
	protected static final String DETALHE_PRODUTO = "/detalheProduto.xhtml";
	protected static final String FILTRO_PRODUTO = "/filtroProduto.xhtml";
	protected static final String PAGINA_PRINCIPAL = "/index.xhtml";
	protected static final String KIT_BLOG = "/kitBlog.xhtml";
	protected static final String RECUPERA_SENHA = "/recuperaSenha.xhtml";
	
	private Cliente cliente= null;
	 
	public Cliente getCliente() {
//		this.cliente = Util.captarClienteSessao();
	   if ( Util.isEmpty( this.cliente ) ) {
		   this.cliente = new Cliente();
	   }
	   	return this.cliente;
	  }

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
