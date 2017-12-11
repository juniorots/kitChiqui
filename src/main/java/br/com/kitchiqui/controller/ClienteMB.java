/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Cleanup;
import br.com.kitchiqui.base.ClienteDAO;
import br.com.kitchiqui.base.MalaDiretaDAO;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.EnumEnvio;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.Constantes;
import br.com.kitchiqui.util.EnviarEmail;
import br.com.kitchiqui.util.Util;

@ManagedBean(name="clienteMB")
@SessionScoped
public class ClienteMB extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
    private Collection<Cliente> listaCliente = new ArrayList();
   
    @ManagedProperty(value="#{produtoMB}")
    private ProdutoMB produtoMB;
    
    /**
     * Responsavel por alterar as informacoes do Cliente logado
     */
    public void alterarCliente() {
        if ( !validarDados() ) return;
        
        @Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        getCliente().setSenha( Util.cifrar( getCliente().getSenha() ) );
        Cliente usAlterado = dao.update( getCliente() );
        entityManager.getTransaction().commit();
        
        Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Dados alterados com sucesso.");
        Util.gravarClienteSessao( usAlterado );
        setCliente ( Util.captarClienteSessao() );
        
//        mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hum...", "descritivo aqui...");
//        RequestContext.getCurrentInstance().showMessageInDialog(mensagem);
    }
    
    /**
     * Tratando da recuperacao de conta
     */
    public void direcionarRecuperarConta() {
    	Util.forward(RECUPERA_SENHA);
    }
    
    /**
     * Tratando da insercao do produto novo no carrinho
     */
    @PostConstruct
    public void adicionarCarrinho(String... origem) {
    	
    	boolean adicionado = false;
    	
    	for (Produto p : getCliente().getListaCarrinho()) {
    		if (!Util.equalsProduto(p, this.produtoMB.getProduto())) {
    			getCliente().getListaCarrinho().add(this.produtoMB.getProduto());
    			adicionado = true;
    		} else {
    			if (!p.getQuantidade().equals(this.produtoMB.getProduto().getQuantidade())) {
					p.setQuantidade(this.produtoMB.getProduto().getQuantidade());
					adicionado = true;
    			}
    		}
    	}
    	
    	if (Util.isEmpty(getCliente().getListaCarrinho())) 
    		if (!Util.isEmpty(this.produtoMB.getProduto().getId())) {
    			getCliente().getListaCarrinho().add(this.produtoMB.getProduto());
    			adicionado = true;
    		}

    	// last actions...
    	if (adicionado)
    		if (origem.length == 0 || !origem[0].equals("botaoComprar"))
    			Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Ok, colocamos no seu carrinho!");
    }
    
    /**
     * Gerenciando as compras realizadas
     */
    public void primeiroPassoCompra() {
    	
    	/*
    	 * Com o parametro abaixo, desconsidera-se a mensagem informativa ao operador
    	 */
    	adicionarCarrinho("botaoComprar");
    	
    	if ( isProblemaCartao() )
    		return;
    	
    	Util.forward(PRIMEIRO_PASSO_COMPRAS);
    }
    
    /**
     * Gerenciando as compras realizadas
     */
    public void segundoPassoCompra() {
    	// Tratando campos 
    	getCliente().getEndereco().setNomeRua(getTmpRua());
    	getCliente().getEndereco().setNomeCidade(getTmpCidade());
    	getCliente().getEndereco().setBairro(getTmpBairro());
    	getCliente().getEndereco().setEstado(getTmpEstado());
    	
    	// validacoes
    	if ( isProblemaDados() )
    		return;
    	
    	if (getCliente().getEndereco().getModoEnvio().equals(EnumEnvio.TAXA_GRATIS.getTipo())) {
    		getCliente().getEndereco().setPrecoModoEnvio(0.0D);
    	}
    	
    	Util.forward(SEGUNDO_PASSO_COMPRAS);
    }
    
    /**
     * Gerenciando as compras realizadas
     */
    public void terceiroPassoCompra() {
    	if ( isProblemaCartao() )
    		return;
    	
    	Util.forward(TERCEIRO_PASSO_COMPRAS);
    }
    
    public void quartoPassoCompra() {
    	Util.forward(QUARTO_PASSO_COMPRAS);
    }
    
    /**
     * Responsavel por persistir as informacoes digitadas na base
     */
    public void salvarCliente() {
        
        if ( !validarDados() ) return;
        
        if ( !continuarRegistro( getCliente() ) ) {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Falha no cadastro", "E-mail já registrado no sistema.");
        	Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "E-mail já registrado no sistema.");
            return;
        }
        
        @Cleanup
        final EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entManager = entityFactory.createEntityManager();
        entManager.getTransaction().begin();
        ClienteDAO dao = new ClienteDAO(entManager);
        
        getCliente().setSenha( Util.cifrar( getCliente().getSenha() ) );
        Cliente usInserido = dao.insert( getCliente() );
        entManager.getTransaction().commit();
        
        if ( !Util.isEmpty( usInserido.getId() ) ) {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_INFO, "Sucesso", "Conta criada.");
        	Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Conta criada.");
            Util.gravarClienteSessao( usInserido );
            setCliente( Util.captarClienteSessao() );
        } else {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Falha no cadastro", "Problema com o sistema, tente em outro momento");
        	Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Problema com o sistema, tente em outro momento");
        }
    }
    
    /*
     * Validando dados inseridos
     */
    public boolean validarDados() {
    	if ( Util.isEmpty(getCliente().getTmpDtNascimento()) 
    			|| Util.isEmpty(getCliente().getNome()) 
    			|| Util.isEmpty(getCliente().getEmail())
    			|| Util.isEmpty(getCliente().getSenha())
    			|| Util.isEmpty(getCliente().getConfirmaSenha()) ) {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "É obrigatório preencher TODOS os campos.");
//    		Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Validação de campos", "É obrigatório preencher TODOS os campos.");
            return false; // fail! :-(
    	}
    	
    	// Tratando campos sensíveis
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		getCliente().setDtNascimento(format.parse(getCliente().getTmpDtNascimento()));
    	} catch (Exception e) {
    		// TODO nothing...
    	}
    	
        if ( !getCliente().getSenha().equalsIgnoreCase( getCliente().getConfirmaSenha() )) {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Problema com as senhas", "Oops... Senhas divergentes");
        	Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Oops... Senhas divergentes");
            return false; // fail! :-(
        }
        
        if ( !validarEmail() ) return false; // fail! :-(
        
        return true; // passou! :-)
    }
    
    /**
     * Tratando da validacao de emails...
     * @return 
     */
    public boolean validarEmail() {
        if ( !Util.validarEmail( getCliente().getEmail() ) ) {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Problema com e-mail", "Oops... E-mail inválido");
        	Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Oops... E-mail inválido");
            return false; // fail! :-(
        }
        return true; // acerto! :-)
    }
    
    /**
     * Responsavel por verificar se o e-mail ja nao esta inserido na base de dados
     * @param Cliente
     * @return 
     */
    private boolean continuarRegistro(Cliente cliente) {
        
        @Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        return Util.isEmpty( dao.findByStringField("email", cliente.getEmail(), true, 0, 1) );
    }
    
    /**
     * Credenciando Cliente
     */
    public void validarCliente() {

        @Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        
        HashMap<String, String> campos = new HashMap();
        campos.put("email", getCliente().getEmail() );
        campos.put("senha", Util.cifrar( getCliente().getSenha() ) );

        ArrayList<Cliente> retorno = (ArrayList<Cliente>) dao.findByStringFields(campos, true, 0, 1);
        
        if (!Util.isEmpty( retorno ) ) {
            Cliente retornoCliente = retorno.get(0);
            Util.gravarClienteSessao( retornoCliente );
            setCliente( retornoCliente );
        } else {
            getCliente().setEmail("");
//            Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Falha", "E-mail ou Senha inválidos.");
            Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "E-mail ou Senha inválido.");
        }
    }
    
    /**
     * Tratando validacao no momento de recuperacao de senhas...
     * @return
     */
    public boolean validarRecuperarSenha() {
    	if ( Util.isEmpty(getCliente().getTmpDtNascimento())
    			|| Util.isEmpty(getCliente().getEmail()) )   {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "É obrigatório o preenchimento de TODOS os campos.");
            return false; // fail! :-(
    	}
    	
    	// Tratando campos sensíveis
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	try {
    		getCliente().setDtNascimento(format.parse(getCliente().getTmpDtNascimento()));
    	} catch (Exception e) {
    		// TODO nothing...
    	}
    	
    	return true; // sucess! :-)
    }
    
    /**
     * Quando o usuario apresenta interesse em 
     * receber noticias de produtos, especifica-se com
     * a mala direta
     */
    public void tratarMalaDireta() {
    	@Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        MalaDiretaDAO dao = new MalaDiretaDAO(entityManager);
        if (Util.isEmpty(dao.findByStringField("email", getMalaDireta().getEmail(), true, 0, 1))) {
        	dao.insert( getMalaDireta() );
        	entityManager.getTransaction().commit();
        }
        Util.montarMensagem(FacesMessage.SEVERITY_INFO, "OK, pegamos o seu e-mail. Havendo novidade te contamos.");
    }
    
    /**
     * Tratando da solicitacao de recuperacao de conta
     */
    public void recuperarConta() {
        
        FacesMessage mensagem = null;
        
        if ( ! validarRecuperarSenha() ) return;
        
        if ( !validarEmail() ) return;
        
        @Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
        
        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        
        HashMap<String, String> campos = new HashMap();
        HashMap<String, Date> campoData = new HashMap();
        campos.put("email", getCliente().getEmail() );
        campoData.put("dtNascimento", getCliente().getDtNascimento() );
        
        Cliente retorno = (Cliente) dao.findByStringDateOperatorEqual(campos, campoData, true, 0, 1);
        
        if ( !Util.isEmpty( retorno ) ) {
    
            Random random = new Random();
            String novaSenha = Util.cifrarRecuperacao( String.valueOf( random.nextInt( 1000000 ) ) );
            retorno.setSenha( Util.cifrar( novaSenha ) );
            
            dao.update( retorno );
            entityManager.getTransaction().commit();
            
            ArrayList emails = new ArrayList();
            emails.add( retorno.getEmail() );
            
            EnviarEmail.recuperarSenha(emails, novaSenha);
            
            Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Uma senha automática fora enviado para o e-mail informado, após a sua validação procure alterá-la.");
        } else {
            Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Informações inexistentes em nossa base de dados, favor tentar novamente.");
        }
        setCliente(new Cliente());
    }
    
    /**
     * Tratando do fechamento da sessao aberta pelo Cliente
     * @return 
     */
    public void sairSistema() {
        setCliente( new Cliente() );
        Util.gravarClienteSessao( getCliente() );
        Util.forward( Constantes.INICIO_SISTEMA );
    }
    
    public Collection<Cliente> getListaCliente() {
        if (listaCliente == null) {
            return new ArrayList<Cliente>();
        }
    
        return listaCliente;
    }

    public void setListaCliente(Collection<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
    /**
     * Com a responsabilidade de capturar todos os valores
     * comprados pelo cliente
     * @return
     */
    public String getResumoSubtotal() {
    	String retorno = "0,00";
    	Double tmp = 0.0;
    	try {
	    	for (Produto p : getCliente().getListaCarrinho()) {
	    		tmp += p.getPreco() * p.getQuantidade();
	    	}
	    	NumberFormat nf = new DecimalFormat("###,##0.00");
			retorno = nf.format(tmp);
    	} catch (Exception e) {
    		retorno = "0,00";
    	}
    	return "R$ " + retorno;
    }
    
    public String getResumoCustoEntrega() {
    	String retorno = "0,00";
    	try {
	    	NumberFormat nf = new DecimalFormat("###,##0.00");
			retorno = nf.format(getCliente().getEndereco().getPrecoModoEnvio());
    	} catch (Exception e) {
    		retorno = "0,00";
    	}
    	return "R$ " + retorno;
    }
    
    public String getResumoTotal() {
    	String retorno = "0,00";
    	Double tmp = 0.0;
    	try {
    		for (Produto p : getCliente().getListaCarrinho()) {
	    		tmp += p.getPreco() * p.getQuantidade();
	    	}
    		tmp += getCliente().getEndereco().getPrecoModoEnvio();
    		NumberFormat nf = new DecimalFormat("###,##0.00");
			retorno = nf.format(tmp);
    	} catch (Exception e) {
    		retorno = "0,00";
    	}
    	return "R$ " + retorno;
    }

	public ProdutoMB getProdutoMB() {
		return produtoMB;
	}

	public void setProdutoMB(ProdutoMB produtoMB) {
		this.produtoMB = produtoMB;
	}
}
