/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Cleanup;
import br.com.kitchiqui.base.ClienteDAO;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.util.Constantes;
import br.com.kitchiqui.util.EnviarEmail;
import br.com.kitchiqui.util.Util;

@ManagedBean
@SessionScoped
public class ClienteMB extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
    private Collection<Cliente> listaCliente = new ArrayList();
   
    /**
     * Responsavel por alterar as informacoes do Cliente logado
     */
    public void alterarCliente() {
        FacesMessage mensagem = null;
        
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
     * Gerenciando as compras realizadas
     */
    public void primeiroPassoCompra() {
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
    	
    	Util.forward(SEGUNDO_PASSO_COMPRAS);
    }
    
    /**
     * Gerenciando as compras realizadas
     */
    public void terceiroPassoCompra() {
    	Util.forward(TERCEIRO_PASSO_COMPRAS);
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
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "É obrigatório preencher TODOS os campos.");
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
    
}
