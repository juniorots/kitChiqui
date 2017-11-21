/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumClasseProduto {
	KIT_STANDART(1),
	KIT_ORTODONTICO(2),
	KIT_PERIODONTAL(3),
	KIT_POSCIRURGICO(4),
	KIT_ESCOLAR(5);
	
	private final Integer classe;
	
	EnumClasseProduto(Integer c) {
		this.classe = c;
	}

	public Integer getClasse() {
		return classe;
	}
	
}
