/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import static javax.persistence.TemporalType.DATE;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;

import br.com.kitchiqui.framework.persistence.DomainObject;
import br.com.kitchiqui.util.Util;

@Entity
public class CompraProduto extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	private String codigoRastreio;
	
	@Temporal(DATE)
	private Date dtCompra;
	
	private Integer codCompra;
	
	private String statusCompra;

	public Date getDtCompra() {
		if (dtCompra == null) {
			dtCompra = Calendar.getInstance().getTime();
		}
		return dtCompra;
	}

	public void setDtCompra(Date dtCompra) {
		this.dtCompra = dtCompra;
	}

	public Integer getCodCompra() {
		if (codCompra == null) {
			codCompra = 0;
		}
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
		return codigoRastreio;
	}

	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}
}
