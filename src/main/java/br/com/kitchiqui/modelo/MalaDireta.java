/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import javax.persistence.Entity;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity
public class MalaDireta extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
