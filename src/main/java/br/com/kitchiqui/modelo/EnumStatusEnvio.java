/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public enum EnumStatusEnvio {
	SEM_ACAO(0),
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
}
