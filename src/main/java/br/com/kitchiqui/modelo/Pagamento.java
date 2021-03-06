/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.swing.text.MaskFormatter;

import br.com.kitchiqui.framework.persistence.DomainObject;
import br.com.kitchiqui.util.Util;

@Entity
public class Pagamento extends DomainObject {

	private static final long serialVersionUID = 1L;

	/*
	 * Dos tipos de pagamento:
	 * 01 - PayPal;
	 * 02 - Cartao de Credito DEFAULT;
	 * 03 - Cartao de Débito
	 */
	private Integer tipoPagamento = EnumTipoPagamento.CARTAO_CREDITO.getTipo();
	
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

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
	public String getNumeroCartaoFormatado() {
		try {
			MaskFormatter mf = new MaskFormatter("AAAA AAAA AAAA AAAA");
			return mf.valueToString(this.numeroCartao);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return "";
		}
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Integer getMesValidadeCartao() {
		if (mesValidadeCartao == null) {
			mesValidadeCartao = 1;
		}
		return mesValidadeCartao;
	}

	public void setMesValidadeCartao(Integer mesValidadeCartao) {
		this.mesValidadeCartao = mesValidadeCartao;
	}

	public Integer getAnoValidadeCartao() {
		if (anoValidadeCartao == null) {
			SimpleDateFormat formato = new SimpleDateFormat("YYYY");
			anoValidadeCartao = Integer.parseInt(formato.format(Calendar.getInstance().getTime()));
		}
		return anoValidadeCartao;
	}

	public void setAnoValidadeCartao(Integer anoValidadeCartao) {
		this.anoValidadeCartao = anoValidadeCartao;
	}

	public Integer getCodigoSeguranca() {
		if (codigoSeguranca == null) {
			codigoSeguranca = 0;
		}
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(Integer codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Integer getNrParcelas() {
		if (nrParcelas == null) {
			nrParcelas = 1;
		}
		return nrParcelas;
	}

	public void setNrParcelas(Integer nrParcelas) {
		this.nrParcelas = nrParcelas;
	}

	public String getNomeTitular() {
		if (!Util.isEmpty(this.nomeTitular)) {
			return nomeTitular.toUpperCase();
		} else {
			return "";
		}
			
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}
	
	public String getFinalCartaoCredito() {
		try {
			return this.numeroCartao.substring((this.numeroCartao.length()-4), this.numeroCartao.length());
		} catch (Exception e) {
			return "****";
		}
	}
}