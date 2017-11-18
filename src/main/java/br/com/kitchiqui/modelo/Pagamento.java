/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import javax.persistence.Entity;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity
public class Pagamento extends DomainObject {
	private static final long serialVersionUID = 1L;

	/*
	 * Dos tipos de pagamento:
	 * 01 - PayPayl;
	 * 02 - Cartao de Credito
	 */
	private Integer tipoPagamento;
	
	private String nomeContidoCartao;
	
	private String numeroCartao;
	
	private Integer mesValidadeCartao;
	
	private Integer anoValidadeCartao;
	
	private Integer codigoSeguranca;
	
	private Integer nrParcelas;
	
	private String nomeTitular;

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getNomeContidoCartao() {
		return nomeContidoCartao;
	}

	public void setNomeContidoCartao(String nomeContidoCartao) {
		this.nomeContidoCartao = nomeContidoCartao;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Integer getMesValidadeCartao() {
		return mesValidadeCartao;
	}

	public void setMesValidadeCartao(Integer mesValidadeCartao) {
		this.mesValidadeCartao = mesValidadeCartao;
	}

	public Integer getAnoValidadeCartao() {
		return anoValidadeCartao;
	}

	public void setAnoValidadeCartao(Integer anoValidadeCartao) {
		this.anoValidadeCartao = anoValidadeCartao;
	}

	public Integer getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(Integer codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Integer getNrParcelas() {
		return nrParcelas;
	}

	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	
}