/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	/*
	 * PRODUTO_VITRINE = 1;
	 * PRODUTO_DESTAQUE = 2;
	 */
	@NotNull
	private Integer tipo;
	
	private Double preco;
	
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
	
	private Integer classe; // Especificar o tipo do produto vendido: Escova, Kit montado, Estojo
	
	@Transient
	private ContadorClasseProduto contadorClasse = new ContadorClasseProduto();
	
	private Double primeiroFiltroPreco;
	
	private Double segundoFiltroPreco;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	private String srcImagemCarrinho;
	
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
		try {
			NumberFormat nf = new DecimalFormat("###,##0.00");
			return "R$ " + nf.format(preco);
		} catch (Exception e) {
			return "R$ 0,00";
		}
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSrcImagemCarrinho() {
		return srcImagemCarrinho;
	}

	public void setSrcImagemCarrinho(String srcImagemCarrinho) {
		this.srcImagemCarrinho = srcImagemCarrinho;
	}
	
	public String getSubTotal() {
		NumberFormat nf = new DecimalFormat("###,##0.00");
		return "R$ " + nf.format(preco * quantidade);
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
}
