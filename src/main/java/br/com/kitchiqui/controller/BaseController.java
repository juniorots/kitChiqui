/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.util.Util;

public class BaseController {
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
