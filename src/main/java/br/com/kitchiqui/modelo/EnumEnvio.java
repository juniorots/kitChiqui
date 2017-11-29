/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumEnvio {
	TAXA_GRATIS(1);
	
	private final Integer tipo;

	EnumEnvio(Integer e){
		this.tipo = e;
	}
	
	public Integer getTipo() {
		return tipo;
	}
}
