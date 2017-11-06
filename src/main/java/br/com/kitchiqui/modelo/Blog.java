/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity
public class Blog extends DomainObject {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String titulo;
	
	private String autor;
	
	@Temporal(DATE)
	private Date dtPublicacao;
	
	private String subTitulo;
	
	@Lob
	private String texto;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
