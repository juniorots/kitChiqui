/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import br.com.kitchiqui.framework.persistence.DomainObject;
import br.com.kitchiqui.util.Util;

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
	
	private Integer qtdEstoque;
	
	/*
	 * PRODUTO_VITRINE = 1;
	 * PRODUTO_DESTAQUE = 2;
	 */
	@NotNull
	private Integer tipo;
	
	private Double preco;
	
	private String strPreco;
	
	private Boolean disponivel;
	
	/*
	 * Secao de informacoes utilizadas no descritivo detalhado do produto
	 */
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch (FetchMode.SELECT)
	private List<ImagemGrandeProduto> listaGrandeProduto;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch (FetchMode.SELECT)
	private List<ImagemPequenoProduto> listaPequenoProduto;
	
	private String tituloDescritivo;
	
	@Lob
	private String anotacaoPrincipalDescritivo;
	
	@Lob
	private String anotacaoDetalhe;
	
	@Lob
	private String anotacaoTecnica;
	
	@Lob
	private String anotacaoComposicao;
	
	/*
	 * Secao de informacoes utilizadas no filtro do produto
	 */
	private Integer especie;
	
	private Integer classe; // Especificar o tipo do produto vendido: Kit ortodontico, Kit periodontal, Kit infantil, Kit standard
	
	@Transient
	private ContadorClasseProduto contadorClasse = new ContadorClasseProduto();
	
	private Double primeiroFiltroPreco;
	
	private Double segundoFiltroPreco;
	
	private String srcImagemCarrinho;
	
	@OneToOne (fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL )
	@LazyToOne (LazyToOneOption.NO_PROXY)
	private CompraProduto compraProduto;
	
	public String getStrPreco() {
		return strPreco;
	}

	public void setStrPreco(String strPreco) {
		this.strPreco = strPreco;
	}

	public ContadorClasseProduto getContadorClasse() {
		return contadorClasse;
	}

	public void setContadorClasse(ContadorClasseProduto contadorClasse) {
		this.contadorClasse = contadorClasse;
	}

	public Double getPrimeiroFiltroPreco() {
		return primeiroFiltroPreco;
	}

	public void setPrimeiroFiltroPreco(Double primeiroFiltroPreco) {
		this.primeiroFiltroPreco = primeiroFiltroPreco;
	}

	public Double getSegundoFiltroPreco() {
		return segundoFiltroPreco;
	}

	public void setSegundoFiltroPreco(Double segundoFiltroPreco) {
		this.segundoFiltroPreco = segundoFiltroPreco;
	}

	public Integer getEspecie() {
		return especie;
	}

	public void setEspecie(Integer especie) {
		this.especie = especie;
	}

	public String getTituloDescritivo() {
		return tituloDescritivo;
	}

	public void setTituloDescritivo(String tituloDescritivo) {
		this.tituloDescritivo = tituloDescritivo;
	}

	public String getAnotacaoPrincipalDescritivo() {
		return anotacaoPrincipalDescritivo;
	}

	public void setAnotacaoPrincipalDescritivo(String anotacaoPrincipalDescritivo) {
		this.anotacaoPrincipalDescritivo = anotacaoPrincipalDescritivo;
	}

	public Double getPreco() {
		return preco;
	}

	public String getPrecoFormatado() {
		return Util.formatarValorMoeda(this.preco);
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

	public List<ImagemGrandeProduto> getListaGrandeProduto() {
		if (listaGrandeProduto == null) {
			listaGrandeProduto = new LinkedList();
		}
		return listaGrandeProduto;
	}

	public void setListaGrandeProduto(
			LinkedList<ImagemGrandeProduto> listaGrandeProduto) {
		this.listaGrandeProduto = listaGrandeProduto;
	}

	public List<ImagemPequenoProduto> getListaPequenoProduto() {
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

	public String getAnotacaoTecnica() {
		return anotacaoTecnica;
	}

	public void setAnotacaoTecnica(String anotacaoTecnica) {
		this.anotacaoTecnica = anotacaoTecnica;
	}

	public String getAnotacaoComposicao() {
		return anotacaoComposicao;
	}

	public void setAnotacaoComposicao(String anotacaoComposicao) {
		this.anotacaoComposicao = anotacaoComposicao;
	}

	public Integer getClasse() {
		return classe;
	}

	public void setClasse(Integer classe) {
		this.classe = classe;
	}

	public String getSrcImagemCarrinho() {
		return srcImagemCarrinho;
	}

	public void setSrcImagemCarrinho(String srcImagemCarrinho) {
		this.srcImagemCarrinho = srcImagemCarrinho;
	}
	
	public String getSubTotal() {
		return "R$ " + Util.formatarValorMoeda(preco * quantidade);
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public CompraProduto getCompraProduto() {
		return compraProduto;
	}

	public void setCompraProduto(CompraProduto compraProduto) {
		this.compraProduto = compraProduto;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
}