/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

public class ContadorClasseProduto {
	private Integer qtdKitStandart;
	
	private Integer qtdKitOrtodontico;
	
	private Integer qtdKitPeriodontal;
	
	private Integer qtdKitPosCirurgico;
	
	private Integer qtdKitEscolar;
	
	public ContadorClasseProduto() {
		qtdKitStandart = 0;
		qtdKitOrtodontico = 0;
		qtdKitPeriodontal = 0;
		qtdKitPosCirurgico = 0;
		qtdKitEscolar = 0;
	}
	
	public Integer getQtdTodosAdulto() {
		return qtdKitStandart + qtdKitOrtodontico + qtdKitPeriodontal + qtdKitPosCirurgico;
	}
	
	public Integer getQtdTodosInfantil() {
		return qtdKitStandart + qtdKitEscolar;
	}

	public Integer getQtdKitStandart() {
		return qtdKitStandart;
	}

	public void setQtdKitStandart(Integer qtdKitStandart) {
		this.qtdKitStandart = qtdKitStandart;
	}

	public Integer getQtdKitOrtodontico() {
		return qtdKitOrtodontico;
	}

	public void setQtdKitOrtodontico(Integer qtdKitOrtodontico) {
		this.qtdKitOrtodontico = qtdKitOrtodontico;
	}

	public Integer getQtdKitPeriodontal() {
		return qtdKitPeriodontal;
	}

	public void setQtdKitPeriodontal(Integer qtdKitPeriodontal) {
		this.qtdKitPeriodontal = qtdKitPeriodontal;
	}

	public Integer getQtdKitPosCirurgico() {
		return qtdKitPosCirurgico;
	}

	public void setQtdKitPosCirurgico(Integer qtdKitPosCirurgico) {
		this.qtdKitPosCirurgico = qtdKitPosCirurgico;
	}

	public Integer getQtdKitEscolar() {
		return qtdKitEscolar;
	}

	public void setQtdKitEscolar(Integer qtdKitEscolar) {
		this.qtdKitEscolar = qtdKitEscolar;
	}
}
