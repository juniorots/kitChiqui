/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumAssuntoBlog {
	ESCOVA(1),
	FIO_DENTAL(2),
	PASTA_DENTE(3);
	
	private final Integer tipo;

	EnumAssuntoBlog(Integer e){
		this.tipo = e;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
}
