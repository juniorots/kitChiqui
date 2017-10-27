/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumClasseProduto {
	ESTOJO(1),
	KIT_MONTADO(2),
	ESCOVA(3);
	
	private final Integer classe;
	
	EnumClasseProduto(Integer c) {
		this.classe = c;
	}

	public Integer getClasse() {
		return classe;
	}
	
}
