/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import javax.persistence.Entity;

import br.com.kitchiqui.framework.persistence.DomainObject;
import br.com.kitchiqui.util.Util;

@Entity
public class Endereco extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	/*
	 * Dos modos de envio:
	 * 01 - Frete Gratis - DEFAULT
	 */
	private Integer modoEnvio = 1;
	
	private Double precoModoEnvio;
	
	// obrigatorio
	private String cep;

	// obrigatorio
	private String nomeRua;
	
	// obrigatorio
	private String numero;
	
	private String complemento;
	
	// obrigatorio
	private String Bairro;
	
	// obrigatorio
	private String nomeCidade;
	
	// obrigatorio
	private String estado;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getModoEnvio() {
		return modoEnvio;
	}

	public void setModoEnvio(Integer modoEnvio) {
		this.modoEnvio = modoEnvio;
	}

	public Double getPrecoModoEnvio() {
		if (this.precoModoEnvio == null) {
			return 0.0;
		}
		return precoModoEnvio;
	}
	
	public String getPrecoModoEnvioFormatado() {
		return "R$ " + Util.formatarValorMoeda( getPrecoModoEnvio() );
	}

	public void setPrecoModoEnvio(Double precoModoEnvio) {
		this.precoModoEnvio = precoModoEnvio;
	}
}
