/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumTipoProduto {
	PRODUTO_VITRINE(1),
	PRODUTO_DESTAQUE(2);
	
	private final Integer tipo;
	
	EnumTipoProduto(Integer t) {
		tipo = t;
	}

	public Integer getTipo() {
		return tipo;
	}
	
}
