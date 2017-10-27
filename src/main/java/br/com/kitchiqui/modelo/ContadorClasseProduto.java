/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public class ContadorClasseProduto {
	private Integer qtdEstojo;
	
	private Integer qtdEscova;
	
	private Integer qtdKitMontado;
	
	public ContadorClasseProduto() {
		qtdEstojo = 0;
		qtdEscova = 0;
		qtdKitMontado = 0;
	}
	
	public Integer getQtdTodos() {
		return qtdEstojo + qtdEscova + qtdKitMontado;
	}

	public Integer getQtdEstojo() {
		return qtdEstojo;
	}

	public void setQtdEstojo(Integer qtdEstojo) {
		this.qtdEstojo = qtdEstojo;
	}

	public Integer getQtdEscova() {
		return qtdEscova;
	}

	public void setQtdEscova(Integer qtdEscova) {
		this.qtdEscova = qtdEscova;
	}

	public Integer getQtdKitMontado() {
		return qtdKitMontado;
	}

	public void setQtdKitMontado(Integer qtdKitMontado) {
		this.qtdKitMontado = qtdKitMontado;
	}
}
