package br.com.kitchiqui.modelo;

public enum EnumTipoPagamento {
	PAYPAL(1),
	CARTAO_CREDITO(2),
	CARTAO_DEBITO(3);
	
	private final Integer tipo;

	EnumTipoPagamento(Integer e){
		this.tipo = e;
	}
	
	public Integer getTipo() {
		return tipo;
	}
}
