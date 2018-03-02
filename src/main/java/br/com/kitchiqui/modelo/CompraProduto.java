/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;

import br.com.kitchiqui.framework.persistence.DomainObject;

@Entity
public class CompraProduto extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	private String codigoRastreio;
	
	@Temporal(DATE)
	private Date dtCompra;
	
	private Integer codCompra;
	
	private String statusCompra;

	public Date getDtCompra() {
		return dtCompra;
	}

	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}

	public Integer getCodCompra() {
		return codCompra;
	}

	public void setCodCompra(Integer codCompra) {
		this.codCompra = codCompra;
	}

	public String getStatusCompra() {
		return EnumStatusCompra.descricaoStatus(this.codCompra);
	}

	public void setStatusCompra(String statusCompra) {
		this.statusCompra = statusCompra;
	}

	public String getCodigoRastreio() {
		this.codigoRastreio = getId().toString().substring(0, 8);
		return codigoRastreio;
	}

	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}
}
