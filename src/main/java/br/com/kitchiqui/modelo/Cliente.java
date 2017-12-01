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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.kitchiqui.framework.persistence.DomainObject;

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
	
	@OneToOne
	private Endereco endereco;
	
	@OneToOne
	private Pagamento pagamento;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch (FetchMode.SELECT)
	private List<Produto> listaCarrinho;
	
	@Transient
	private int itensCarrinho;

	public int getItensCarrinho() {
		
		try {
			itensCarrinho = getListaCarrinho().size();
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
		if (listaCarrinho == null) {
			listaCarrinho = new LinkedList<>();
		}
		return listaCarrinho;
	}

	public void setListaCarrinho(List<Produto> listaCarrinho) {
		this.listaCarrinho = listaCarrinho;
	}
}
