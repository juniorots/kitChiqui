/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumStatusCompra {
	PROCESSANDO(1),
	FINALIZADO(2),
	CANCELADO(3),
	PENDENTE(4),
	EM_ESPERA(5),
	SOLICITADO(6);
	
	private final Integer tipo;

	EnumStatusCompra(Integer e){
		this.tipo = e;
	}
	
	public Integer getTipo() {
		return tipo;
	}
	
	public static String descricaoStatus(Integer cod) {
		switch (cod) {
			case 1:
				return "Processando";
			case 2:
				return "Finalizado";
			case 3:
				return "Cancelado";
			case 4:
				return "Pendente";
			case 5:
				return "Em espera";	
			default:
				return "Solicitado";
		}
	}
	
}
