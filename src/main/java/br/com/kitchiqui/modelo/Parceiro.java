/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity 
public class Parceiro extends DomainObject {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String srcImagem;

	public String getSrcImagem() {
		return srcImagem;
	}

	public void setSrcImagem(String srcImagem) {
		this.srcImagem = srcImagem;
	}
}
