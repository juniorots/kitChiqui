package br.com.kitchiqui.modelo;

public enum EnumTipoEmail {
	RECUPERACAO_SENHA(1),
	COMPRA_PRODUTO(2),
	NOTIFICACAO_ADMINISTRADOR(3),
	INFORMATIVO_CLIENTE(4);
	
	private final Integer tipo;

	EnumTipoEmail(Integer e){
		this.tipo = e;
	}
	
	public Integer getTipo() {
		return tipo;
	}
}
