/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity 
public class Produto extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String srcImagem;
	
	@NotNull
	private String titulo;
	
	private String subTitulo;
	
	private String descritivo;
	
	private Integer quantidade;
	
	@NotNull
	private Integer tipo;
	
	private Double preco;
	
	/*
	 * Secao de informacoes utilizadas no descritivo detalhado do produto
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private LinkedList<ImagemGrandeProduto> listaGrandeProduto;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private LinkedList<ImagemPequenoProduto> listaPequenoProduto;
	
	private String anotacaoDetalhe;
	
	private String anotacaoTécnica;
	
	private String anotacaoComposicao;
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getSrcImagem() {
		return srcImagem;
	}

	public void setSrcImagem(String srcImagem) {
		this.srcImagem = srcImagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getDescritivo() {
		return descritivo;
	}

	public void setDescritivo(String descritivo) {
		this.descritivo = descritivo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public LinkedList<ImagemGrandeProduto> getListaGrandeProduto() {
		if (listaGrandeProduto == null) {
			listaGrandeProduto = new LinkedList();
		}
		return listaGrandeProduto;
	}

	public void setListaGrandeProduto(
			LinkedList<ImagemGrandeProduto> listaGrandeProduto) {
		this.listaGrandeProduto = listaGrandeProduto;
	}

	public LinkedList<ImagemPequenoProduto> getListaPequenoProduto() {
		if (listaPequenoProduto == null) {
			listaPequenoProduto = new LinkedList();
		}
		return listaPequenoProduto;
	}

	public void setListaPequenoProduto(
			LinkedList<ImagemPequenoProduto> listaPequenoProduto) {
		this.listaPequenoProduto = listaPequenoProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getAnotacaoDetalhe() {
		return anotacaoDetalhe;
	}

	public void setAnotacaoDetalhe(String anotacaoDetalhe) {
		this.anotacaoDetalhe = anotacaoDetalhe;
	}

	public String getAnotacaoTécnica() {
		return anotacaoTécnica;
	}

	public void setAnotacaoTécnica(String anotacaoTécnica) {
		this.anotacaoTécnica = anotacaoTécnica;
	}

	public String getAnotacaoComposicao() {
		return anotacaoComposicao;
	}

	public void setAnotacaoComposicao(String anotacaoComposicao) {
		this.anotacaoComposicao = anotacaoComposicao;
	}	
}
