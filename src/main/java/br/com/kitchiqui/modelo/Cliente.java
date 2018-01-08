/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.modelo;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import br.com.kitchiqui.framework.persistence.DomainObject;
import br.com.kitchiqui.util.Util;

@Entity
public class Cliente extends DomainObject {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String nome;
	
	private String nomeCompleto;
	
	private String nrCpf;
	
	private String nrRG;
	
	private String nrTelefone;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	    
	@Transient
	private String confirmaSenha;
	
	@Temporal(DATE)
    @NotNull
    private Date dtNascimento;
	
	private String tmpDtNascimento;
	
	@OneToOne (fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL )
	@LazyToOne (LazyToOneOption.NO_PROXY)
	private Endereco endereco;
	
	@OneToOne (fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL )
	@LazyToOne (LazyToOneOption.NO_PROXY)
	private Pagamento pagamento;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="cliente_has_produtos", joinColumns=
			{@JoinColumn(name="cliente_id")}, inverseJoinColumns=
				{@JoinColumn(name="produto_id")})
	private List<Produto> listaCarrinho = new LinkedList<>();
	
	@Transient
	private int itensCarrinho;

	@Transient
	private boolean carrinhoVazio;
	
	public int getItensCarrinho() {
		int cont = 0;
		try {
			for (Produto p : getListaCarrinho()) {
				if (!p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.SOLICITADO.getTipo())) 
					continue;
				cont++;
			}
				
			itensCarrinho = cont;
		} catch (Exception e) {
			this.itensCarrinho = 0;
		}
		return this.itensCarrinho;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getTmpDtNascimento() {
		return tmpDtNascimento;
	}

	public void setTmpDtNascimento(String tmpDtNascimento) {
		this.tmpDtNascimento = tmpDtNascimento;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String getNrRG() {
		return nrRG;
	}

	public void setNrRG(String nrRG) {
		this.nrRG = nrRG;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Endereco getEndereco() {
		if (endereco == null) {
			this.endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Pagamento getPagamento() {
		if (pagamento == null) {
			pagamento = new Pagamento();
		}
			
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public List<Produto> getListaCarrinho() {
		return listaCarrinho;
	}

	public void setListaCarrinho(List<Produto> listaCarrinho) {
		if (Util.isEmpty(listaCarrinho)) {
			this.listaCarrinho.clear();
		} else { 
			this.listaCarrinho = listaCarrinho;
		}
	}

	public void setItensCarrinho(int itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}

	public boolean isCarrinhoVazio() {
		boolean vazio = true;
		for (Produto p : getListaCarrinho()) {
			if (p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.SOLICITADO.getTipo()))
				vazio = false;
		}
		return vazio;
	}

	public void setCarrinhoVazio(boolean carrinhoVazio) {
		this.carrinhoVazio = carrinhoVazio;
	}
}
