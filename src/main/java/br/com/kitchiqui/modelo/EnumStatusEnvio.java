/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumStatusEnvio {
	AGUARDANDO_PAGAMENTO(1),
	PREPARANDO_ENVIO(2),
	CONFIRMANDO_ENTREGA(3);
	
	private final Integer tipo;

	EnumStatusEnvio(Integer e){
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
