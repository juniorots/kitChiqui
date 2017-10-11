/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity 
public class Produto extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	@NotNull 
	private int idProduto;
	
	@NotNull
	private String srcImagem;
	
	@NotNull
	private String titulo;
	
	private String subTitulo;
	
	private String descritivo;
	
	@NotNull
	private Integer tipo;
	
	private Double preco;
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
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
	
}
