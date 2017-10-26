/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumEspecie {
	PRODUTO_MASCULINO(1),
	PRODUTO_FEMININO(2),
	PRODUTO_INFANTIL(3);
	
	private final Integer especie;

	EnumEspecie(Integer e){
		this.especie = e;
	}
	
	public Integer getEspecie() {
		return especie;
	}
	
}
