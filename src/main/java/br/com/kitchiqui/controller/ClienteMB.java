/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.kitchiqui.base.ClienteDAO;
import br.com.kitchiqui.base.MalaDiretaDAO;
import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.CompraProduto;
import br.com.kitchiqui.modelo.EnumEnvio;
import br.com.kitchiqui.modelo.EnumStatusCompra;
import br.com.kitchiqui.modelo.EnumStatusEnvio;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.BackUp;
import br.com.kitchiqui.util.Constantes;
import br.com.kitchiqui.util.EnviarEmail;
import br.com.kitchiqui.util.Util;
import lombok.Cleanup;

@ManagedBean(name="clienteMB")
@SessionScoped
public class ClienteMB extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
    private Collection<Cliente> listaCliente = new ArrayList();
   
    @ManagedProperty(value="#{produtoMB}")
    private ProdutoMB produtoMB;
    
    /**
     * Tratativa para envio do e-mail customizado
     */
    public void enviarEmailCustomizado() {
    	boolean status = false;
    	
    	status = EnviarEmail.enviarEmailCustomizado(getCliente().getEmailFiltro(), getTmpCorpoEmail());
    	
    	if (status) {
			Util.montarMensagem(FacesMessage.SEVERITY_INFO, "E-mail enviado :-)");
		} else {
			Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Encontramos um problema no envio do E-mail :-(");
		}
    }
    
    /**
     * Tratando de limpeza de itens que foram solicitados
     * no entanto não estiverem concluidos sua fase de aquisicao
     * pelo sistema
     */
    public void excluirProdutoSolicitado() {
    	List<Produto> tmp = new ArrayList<>();
    	for (Produto p : getCliente().getListaCarrinho()) {
    		if (!p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.SOLICITADO.getTipo())) 
    			tmp.add(p);
    	}
    	getCliente().setListaCarrinho(tmp);
    }
    
    /*
     * Atualizando informativo ao usuario
     */
    public void notificarCliente() {
    	boolean status = false;
    	if (getTmpStatusEnvio().equals(EnumStatusEnvio.SEM_ACAO.getTipo().toString())) 
    		return;
    	
    	if (!Util.isEmpty(getClienteGestao())) {
    		if (getTmpStatusEnvio().equals(EnumStatusEnvio.AGUARDANDO_PAGAMENTO.getTipo().toString()))
    			status = EnviarEmail.enviarEmailComercial(getClienteGestao(), EnumStatusEnvio.AGUARDANDO_PAGAMENTO.getTipo());
    		
    		if (getTmpStatusEnvio().equals(EnumStatusEnvio.PREPARANDO_ENVIO.getTipo().toString()))
    			status = EnviarEmail.enviarEmailComercial(getClienteGestao(), EnumStatusEnvio.PREPARANDO_ENVIO.getTipo());
    		
    		if (getTmpStatusEnvio().equals(EnumStatusEnvio.CONFIRMANDO_ENTREGA.getTipo().toString()))
    			status = EnviarEmail.enviarEmailComercial(getClienteGestao(), EnumStatusEnvio.CONFIRMANDO_ENTREGA.getTipo());
    		
    		if (status) {
    			Util.montarMensagem(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso :-)");
    		} else {
    			Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Encontramos um problema no envio do E-mail :-(");
    		}
    	}
    }
    
    /**
     * Responsavel por alterar as informacoes do Cliente logado
     */
    public void alterarCliente( String... origem ) {
      
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        tratarAdicionaisEndereco();
        Cliente usAlterado = dao.update( getCliente() );
        
        entityManager.getTransaction().commit();
        
        if (origem.length == VAZIO || !origem[0].equals("fecharCompra")) 
        	Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Dados atualizados");
        Util.gravarClienteSessao( usAlterado );
//        setCliente ( Util.captarClienteSessao() );
        
//        mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hum...", "descritivo aqui...");
//        RequestContext.getCurrentInstance().showMessageInDialog(mensagem);
    }
    
    /**
     * Util para carregar uma lista de nomes a serem
     * exibidos em um auto-complete
     * @param nome
     * @return
     */
    public List<String> hotListaCliente(String nome) {
    	
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        ArrayList<String> retorno = new ArrayList<>();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        
        List<Cliente> tmp = (ArrayList<Cliente>) dao.selectAll();
        for (Cliente c : tmp) {
        	if (!Util.isEmpty(c.getNomeCompleto()) 
        			&& c.getNomeCompleto().toLowerCase().contains(nome))
        		retorno.add(c.getNomeCompleto());
        }
        return retorno;
    }
    
    /*
     * Util na secao de gerenciamento de cliente, pesquisa conforme filtro especificado
     */
    public void pesquisarCliente() {
    	
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        List<Cliente> tmp = new ArrayList<>();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        
        if (Util.isEmpty(getCliente().getNomeFiltro()) 
        	&& Util.isEmpty(getCliente().getEmailFiltro()) ) {
        	setClientePesquisado(true);
        	setClienteGestao(null);
        	return;
        }
        
        setClienteGestao(null);
        HashMap<String, String> campos = new HashMap<>();
        if (!Util.isEmpty(getCliente().getNomeFiltro()))
        	campos.put("nomeCompleto", getCliente().getNomeFiltro() );

        if (!Util.isEmpty(getCliente().getEmailFiltro()))
        	campos.put("email", getCliente().getEmailFiltro() );
        
        tmp = dao.findByStringFields(campos, true, 0, 1);
        if (!Util.isEmpty(tmp)) 
        	setClienteGestao(tmp.get(0));
        
        setClientePesquisado(Util.isEmpty(getClienteGestao()));
    }
    
    /**
     * Util para carregar uma lista de emails a serem
     * exibidos em um auto-complete
     * @param nome
     * @return
     */
    public List<String> hotListaEmail(String email) {
    	
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        ArrayList<String> retorno = new ArrayList<>();
        
        ClienteDAO dao = new ClienteDAO(entityManager);

        ArrayList<Cliente> tmp = (ArrayList<Cliente>) dao.selectAll();
        for (Cliente c : tmp) {
        	if (c.getEmail().toLowerCase().contains(email))
        		retorno.add(c.getEmail());
        }
        return retorno;
    }
    
    /*
     * Quando o operador deseja alterar a senha
     */
    public void alterarSenha() {
    	
    	if (!getCliente().getSenha().equals(getCliente().getConfirmaSenha())) {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Ops... Senhas diferentes");
    		return;
    	}
    	
        @Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        getCliente().setSenha(Util.cifrar(getCliente().getSenha()));
        Cliente usAlterado = dao.update( getCliente() );
        
        entityManager.getTransaction().commit();
        
    	Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Senha alterada com sucesso");
        Util.gravarClienteSessao( usAlterado );
    }
    
    /**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void tratarGerenciamentoCliente() {
    	limparClienteGestao();
    	Util.forward(GERENCIAR_CLIENTE);
    }
    
    /**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void tratarPainelControle() {
    	limparClienteGestao();
    	Util.forward(PAINEL_CONTROLE);
    }

    public void limparClienteGestao() {
    	setClienteGestao(null);
    	getCliente().setNomeFiltro(null);
    	getCliente().setEmailFiltro(null);
    	setClientePesquisado(true);
    	excluirProdutoSolicitado();
    }
    
    
    /**
     * Tratando da insercao do produto novo no carrinho
     */
    public void adicionarCarrinho(String... origem) {
    	
    	boolean adicionado = false;
    	CompraProduto cp = new CompraProduto();
    	cp.setCodCompra(EnumStatusCompra.SOLICITADO.getTipo());
    	cp.setStatusCompra(EnumStatusCompra.descricaoStatus(cp.getCodCompra()));
    	List<Produto> listTmp = new ArrayList<>();
    	listTmp.clear();
    	
    	for (Produto p : getCliente().getListaCarrinho()) {

    		if (!Util.equalsProduto(p, this.produtoMB.getProduto())) {
    			this.produtoMB.getProduto().setCompraProduto(cp);
    			listTmp.add(this.produtoMB.getProduto());
    			adicionado = true;
    		} else {
    			
    			if (p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.PROCESSANDO.getTipo())) {
    				Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Ops, já estamos processando esse pedido!");
    				return;
				}
    			
    			if (!p.getQuantidade().equals(this.produtoMB.getProduto().getQuantidade())) {
					p.setQuantidade(this.produtoMB.getProduto().getQuantidade());
					adicionado = true;
    			}
    			if (p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.CANCELADO.getTipo())
    				|| p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.FINALIZADO.getTipo())) {
    				this.produtoMB.getProduto().setCompraProduto(cp);
    				listTmp.add(this.produtoMB.getProduto());
        			adicionado = true;
    			}
    		}
    	}
    	
    	if (Util.isEmpty(getCliente().getListaCarrinho())) 
    		if (!Util.isEmpty(this.produtoMB.getProduto().getId())) {
    			this.produtoMB.getProduto().setCompraProduto(cp);
    			getCliente().getListaCarrinho().add(this.produtoMB.getProduto());
    			adicionado = true;
    		}
    	
    	if (!Util.isEmpty(listTmp)) {
    		boolean contido = false;
			for (Produto p1 : listTmp) {
				for (Produto p2 : getCliente().getListaCarrinho()) {
					if (Util.equalsProduto(p1, p2))
						contido = true;
				}
				if (!contido)
					getCliente().getListaCarrinho().add(p1);
			}
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
    	
    	if (getCliente().isCarrinhoVazio()) {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Ops... Vimos que seu carrinho está vazio!");
    		return;
    	}
    	
    	if ( isProblemaCartao() )
    		return;
    	
    	procuraPorCEP();
    	
    	Util.forward(PRIMEIRO_PASSO_COMPRAS);
    }
    
    /**
     * Gerenciando as compras realizadas
     */
    public void segundoPassoCompra() {
    	
    	if (getCliente().isCarrinhoVazio()) {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Ops... Vimos que seu carrinho está vazio!");
    		return;
    	}
    	
    	// Tratando campos 
    	tratarAdicionaisEndereco();
    	
    	// Tratando da seguridade das informacoes do cartao
    	getCliente().getPagamento().setNomeTitular("");
    	getCliente().getPagamento().setNumeroCartao("");
    	getCliente().getPagamento().setCodigoSeguranca(null);
    	
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
    	
    	if (getCliente().isCarrinhoVazio()) {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Ops... Vimos que seu carrinho está vazio!");
    		return;
    	}
    	
    	Util.forward(TERCEIRO_PASSO_COMPRAS);
    }
    
    /**
     * Finalizando de fato a compra
     */
    public void quartoPassoCompra() {
    	
    	if (getCliente().isCarrinhoVazio()) {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Ops... Vimos que seu carrinho está vazio!");
    		return;
    	}
    	
    	for ( Produto p: getCliente().getListaCarrinho() ) {
    		if (p.getCompraProduto() == null) {
    			CompraProduto cp = new CompraProduto();
    			p.setCompraProduto(cp);
    		}
    		p.getCompraProduto().setCodCompra(EnumStatusCompra.PROCESSANDO.getTipo());
    		p.getCompraProduto().setStatusCompra(EnumStatusCompra.descricaoStatus(p.getCompraProduto().getCodCompra()));
    		p.getCompraProduto().setDtCompra(Calendar.getInstance().getTime());
    		
    		// gerenciando estoque
    		p.setQtdEstoque(p.getQtdEstoque() - p.getQuantidade());
    		if (p.getQtdEstoque() == VAZIO) {
    			p.setDisponivel(false);
    			EnviarEmail.produtoEmFalta(p);
    		}
    	}
    	
    	alterarCliente("fecharCompra");
    	
    	// Enviar notificacao da compra
    	EnviarEmail.enviarEmailComercial(Util.captarClienteSessao(), EnumStatusEnvio.AGUARDANDO_PAGAMENTO.getTipo());
    	
    	Util.forward(QUARTO_PASSO_COMPRAS);
    }
    
    /**
     * Trando da remocao de um item que ainda esta em modo
     * de analise
     */
    public void retirarProdutoCarrinho() {
    	List<Produto> tmpList = new ArrayList<>();
    	tmpList.clear();
    	for (Produto p : getCliente().getListaCarrinho()) {
    		if (!p.getId().equals(UUID.fromString(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduto")))) {
    			tmpList.add(p);
    		}
    	}
    	getCliente().getListaCarrinho().clear();
    	getCliente().getListaCarrinho().addAll(tmpList);
    }
    
    /**
     * Util na utilizacao de backup, visando persistencia em base de dados.
     * @param cliente
     */
    public static void gravarClienteBackUp(Cliente cliente) {
    	@Cleanup
        final EntityManager entManager = getInstanceEntity();
        
        entManager.getTransaction().begin();
        ClienteDAO dao = new ClienteDAO(entManager);
    
        dao.insert( cliente );
        entManager.getTransaction().commit();
    }
    
    /**
     * Util na utilizacao de backup, visando persistencia em base de dados.
     * @param cliente
     */
    public static void atualizarClienteBackUp(Cliente cliente) {
    	@Cleanup
        final EntityManager entManager = getInstanceEntity();
        
        entManager.getTransaction().begin();
        ClienteDAO dao = new ClienteDAO(entManager);
    
        dao.update( cliente );
        entManager.getTransaction().commit();
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
        final EntityManager entManager = getInstanceEntity();
        entManager.getTransaction().begin();
        ClienteDAO dao = new ClienteDAO(entManager);
        
        getCliente().setSenha( Util.cifrar( getCliente().getSenha() ) );
        Cliente usInserido = dao.insert( getCliente() );
        entManager.getTransaction().commit();
        
        if ( !Util.isEmpty( usInserido.getId() ) ) {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_INFO, "Sucesso", "Conta criada.");
        	Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Conta criada");
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
    	
        if ( !getCliente().getSenha().equals( getCliente().getConfirmaSenha() )) {
//            Util.montarMensagemModal(FacesMessage.SEVERITY_ERROR, "Problema com as senhas", "Oops... Senhas divergentes");
        	Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Oops... Senhas diferentes");
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
    	getCliente().setEmail(getCliente().getEmail().toLowerCase());
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
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        boolean retorno = false;
        
        retorno = Util.isEmpty( dao.findByStringField("email", cliente.getEmail(), true, 0, 1) );
        return retorno;
    }
    
    /**
	 * Util para utilizacao de backUp - restauracao do sistema
	 * @param produto
	 * @return
	 */
	public Cliente pesquisaBackUp(Cliente cliente) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = null;
        Cliente retorno = null;
        
        dao = new ClienteDAO(entityManager);
        retorno = dao.findByStringField("email", cliente.getEmail(), true, 0, 1).get(0);
        return retorno;
	}
    
    /**
     * Credenciando Cliente
     */
    public void validarCliente() {
        @Cleanup
        final EntityManager entityManager = getInstanceEntity();
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
            Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "E-mail ou Senha inválido");
        }
    }
    
    /**
     * Tratando validacao no momento de recuperacao de senhas...
     * @return
     */
    public boolean validarRecuperarSenha() {
    	if ( Util.isEmpty(getCliente().getTmpDtNascimento())
    			|| Util.isEmpty(getCliente().getEmail()) )   {
    		Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "É obrigatório o preenchimento de TODOS os campos");
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
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        MalaDiretaDAO dao = new MalaDiretaDAO(entityManager);
        if (Util.isEmpty(dao.findByStringField("email", getMalaDireta().getEmail(), true, 0, 1))) {
        	dao.insert( getMalaDireta() );
        	entityManager.getTransaction().commit();
        }
        Util.montarMensagem(FacesMessage.SEVERITY_INFO, "OK, pegamos o seu e-mail. Havendo novidade te contamos.");
        getMalaDireta().setEmail(null);
    }
    
    /**
     * Tratando da solicitacao de recuperacao de conta
     */
    public void recuperarConta() {
        
        if ( ! validarRecuperarSenha() ) return;
        
        if ( !validarEmail() ) return;
        
        @Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        
        HashMap<String, String> campos = new HashMap<>();
        HashMap<String, Date> campoData = new HashMap<>();
        campos.put("email", getCliente().getEmail() );
        campoData.put("dtNascimento", getCliente().getDtNascimento() );
        
        Cliente retorno = (Cliente) dao.findByStringDateOperatorEqual(campos, campoData, true, 0, 1);
        
        if ( !Util.isEmpty( retorno ) ) {
    
            Random random = new Random();
            String novaSenha = Util.cifrarRecuperacao( String.valueOf( random.nextInt( 1000000 ) ) );
            retorno.setSenha( Util.cifrar( novaSenha ) );
            
            dao.update( retorno );
            entityManager.getTransaction().commit();
            
            ArrayList<String> emails = new ArrayList<>();
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
    	setCliente(null);
        Util.gravarClienteSessao( null );
        Util.forward( Constantes.INICIO_SISTEMA );
    }
    
    /**
     * No gerenciamento do produto, util quando o operador for atualizar o status do pedido.
     */
    public void atualizarStatusPedido() {
    	
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        if (Util.isEmpty(getTmpUltimoStatusPedido()) 
        		|| getTmpUltimoStatusPedido().equals(EnumStatusCompra.INEXISTENTE.getTipo().toString()))
        	return;
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        setProduto(dao.selectById(UUID.fromString(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduto"))));
        getProduto().getCompraProduto().setCodCompra(Integer.parseInt(getTmpUltimoStatusPedido()));        
        getProduto().getCompraProduto().setStatusCompra(EnumStatusCompra.descricaoStatus(getProduto().getCompraProduto().getCodCompra()));

        dao.update(getProduto());
        entityManager.getTransaction().commit();
        
        // update search...
        pesquisarCliente();
    }
    
    /**
     * Com a responsabilidade de tratar o backUp de informacoes
     * Uteis ao sistema
     */
    public void tratarBackUp() {
    	
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ClienteDAO dao = new ClienteDAO(entityManager);
        
        List<Cliente> clientes = dao.selectAll();
        try {
	        BackUp.gravarDadosCliente(clientes);
//	        Util.montarMensagem(FacesMessage.SEVERITY_INFO, "Back Up realizado com sucesso"); // acertô!
        } catch (NullPointerException ne) {
        	ne.printStackTrace();
//        	Util.montarMensagem(FacesMessage.SEVERITY_ERROR, "Falha no processo de Back Up"); // deu ruim!
        }
    }
    
    /**
     * Util para capturar os valores que forem
     * buscados automaticamente pelo sistema e inserindo tais
     * valores no objeto correspondente ao endereco
     */
    public void tratarAdicionaisEndereco() {
    	if (!Util.isEmpty(getTmpRua())) 
    		getCliente().getEndereco().setNomeRua(getTmpRua());
    	
    	if (!Util.isEmpty(getTmpCidade()))
    		getCliente().getEndereco().setNomeCidade(getTmpCidade());
    	
    	if (!Util.isEmpty(getTmpBairro()))
    		getCliente().getEndereco().setBairro(getTmpBairro());
    	
    	if (!Util.isEmpty(getTmpEstado()))
    		getCliente().getEndereco().setEstado(getTmpEstado());
    }
    
    /**
     * Com a responsabilidade de capturar todos os valores
     * comprados pelo cliente
     * @return
     */
    public String getResumoSubtotal() {
    	Double tmp = 0.0;
    	for (Produto p : getCliente().getListaCarrinho()) {
    		if (!p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.SOLICITADO.getTipo()))
    			continue;
    		
    		tmp += p.getPreco() * p.getQuantidade();
    	}
    	return "R$ " + Util.formatarValorMoeda(tmp);
    }
    
    public String getResumoCustoEntrega() {
    	return "R$ " + Util.formatarValorMoeda(getCliente().getEndereco().getPrecoModoEnvio());
    }
    
    public String getResumoTotal() {
    	Double tmp = 0.0;
		for (Produto p : getCliente().getListaCarrinho()) {
			if (!p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.SOLICITADO.getTipo()))
    			continue;
			
    		tmp += p.getPreco() * p.getQuantidade();
    	}
		tmp += getCliente().getEndereco().getPrecoModoEnvio();
    	return "R$ " + Util.formatarValorMoeda(tmp);
    }

	public ProdutoMB getProdutoMB() {
		return produtoMB;
	}

	public void setProdutoMB(ProdutoMB produtoMB) {
		this.produtoMB = produtoMB;
	}
	
	/**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void tratarEmailPersonalizado() {
    	setTmpCorpoEmail(null);
    	Util.forward(DADOS_PESSOAIS_EMAIL);
    }
	
	/**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void verificarDadosPessoais() {
    	Util.forward(DADOS_PESSOAIS_CLIENTE);
    }
    
    /**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void verificarDadosPessoaisAlteraSenha() {
    	Util.forward(DADOS_PESSOAIS_CLIENTE_ALTERA_SENHA);
    }
    
    /**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void verificarDadosPessoaisEndereco() {
    	procuraPorCEP();
    	Util.forward(DADOS_PESSOAIS_CLIENTE_ENDERECO);
    }
    
    /**
     * Secao de configuracoes das informacoes base do cliente
     */
    public void verificarDadosPessoaisPedidos() {
    	excluirProdutoSolicitado();
    	Util.forward(DADOS_PESSOAIS_CLIENTE_PEDIDOS);
    }
    
    /**
     * Tratando da recuperacao de conta
     */
    public void direcionarRecuperarConta() {
    	Util.forward(RECUPERA_SENHA);
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
    
    public void tratarSelecaoStatusPedido() {
    	setTmpUltimoStatusPedido(getTmpStatusPedido()); 
    }
}
