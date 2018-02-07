/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.kitchiqui.modelo.Blog;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.MalaDireta;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.Constantes;
import br.com.kitchiqui.util.Util;
import br.com.kitchiqui.util.ValidarCartao;

public class BaseController {
	
	// section forwarding...	
	protected static final String DETALHE_PRODUTO = "/detalheProduto.xhtml";
	protected static final String FILTRO_PRODUTO = "/filtroProduto.xhtml";
	protected static final String PAGINA_PRINCIPAL = "/index.xhtml";
	protected static final String KIT_BLOG = "/kitBlog.xhtml";
	protected static final String RECUPERA_SENHA = "/recuperaSenha.xhtml";
	protected static final String PRIMEIRO_PASSO_COMPRAS = "/compraDadosUsuario.xhtml";
	protected static final String SEGUNDO_PASSO_COMPRAS = "/usuarioMeioPagamento.xhtml";
	protected static final String TERCEIRO_PASSO_COMPRAS = "/resumoDadosCompra.xhtml";
	protected static final String QUARTO_PASSO_COMPRAS = "/confirmaCompraCliente.xhtml";
	protected static final String POLITICA_PRIVACIDADE = "/politicaPrivacidade.xhtml";
	protected static final String TERMOS_USO = "/termoUso.xhtml";
	protected static final String DADOS_PESSOAIS_CLIENTE = "/dadosPessoaisCliente.xhtml";
	protected static final String DADOS_PESSOAIS_CLIENTE_ENDERECO = "/dadosPessoaisEnderecoCliente.xhtml";
	protected static final String DADOS_PESSOAIS_CLIENTE_PEDIDOS = "/dadosPessoaisClientePedidos.xhtml";
	protected static final String DADOS_PESSOAIS_CLIENTE_ALTERA_SENHA = "/dadosPessoaisClienteAlterarSenha.xhtml";
	protected static final String GERENCIAR_CLIENTE = "/dadosPessoaisGerenciar.xhtml";
	
	private Cliente cliente= null;
	private Cliente clienteGestao= null;
	private Produto produto = null;
	private Blog blog = null;
	private MalaDireta malaDireta = null;
	private String tmpPrimeiro;
	private String tmpSegundo;
	
	private String tmpRua;
	private String tmpBairro;
	private String tmpEstado;
	private String tmpCidade;
	
	private String msgCartao;
	private String msgCPF;
	private boolean problemaCartao;
	private boolean problemaDados;
	
	private String tmpStatusEnvio;
	private String tmpStatusPedido;
	
	private String buscarCNPJ;
	private String buscarEndereco;
	private String buscarSiteKitChiqui;
	
	private Map<String, String> estados = new HashMap();
	private Map<Integer, Integer> listaMes = new HashMap();
	private Map<Integer, Integer> listaAno = new HashMap();
	
	private boolean usuarioLogado;
	private boolean clientePesquisado = true;
	
	private static EntityManagerFactory entityManagerFactory;
	
	/**
	 * Reaproveitando instancia de conexao com a base
	 * @return
	 */
	public static EntityManager getInstanceEntity() {
		try {
			if (entityManagerFactory.isOpen()) {
				return entityManagerFactory.createEntityManager();
			} else {
				entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
				return entityManagerFactory.createEntityManager();
			}
		} catch (NullPointerException ne) {
			entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");
			return entityManagerFactory.createEntityManager();
		}
	}
	
	public void resetarConfig() {
		try {
			tmpPrimeiro = "";
			getProduto().setTitulo("");
			getMalaDireta().setEmail("");
		} catch (Exception e) {
			// TODO nothing...
		}
	}
	
	// section forwarding...
	public void paginaPrincipal(){
		resetarConfig();
		Util.forward(PAGINA_PRINCIPAL);
	}
	
	public void politicaPrivacidade() {
		Util.forward(POLITICA_PRIVACIDADE);
	}
	
	public void termoUso() {
		Util.forward(TERMOS_USO);
	}
	// section forwarding...
	
	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Cliente getCliente() {
	   if ( Util.isEmpty( this.cliente ) ) 
		   this.cliente = new Cliente();
	   	return this.cliente;
	  }

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Blog getBlog() {
		if (this.blog == null) {
			blog = new Blog();
		}
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getTmpPrimeiro() {
		return tmpPrimeiro;
	}

	public void setTmpPrimeiro(String tmpPrimeiro) {
		this.tmpPrimeiro = tmpPrimeiro;
	}

	public String getTmpSegundo() {
		return tmpSegundo;
	}

	public void setTmpSegundo(String tmpSegundo) {
		this.tmpSegundo = tmpSegundo;
	}

	public Map<Integer, Integer> getListaMes() {
		for (int i = 1; i <= 12; i++) 
			listaMes.put(i, i);
		return listaMes;
	}

	public void setListaMes(Map<Integer, Integer> listaMes) {
		this.listaMes = listaMes;
	}

	public Map<Integer, Integer> getListaAno() {
		for (int i = 2018; i <= 2040; i++) 
			listaAno.put(i, i);
		return listaAno;
	}

	public void setListaAno(Map<Integer, Integer> listaAno) {
		this.listaAno = listaAno;
	}

	public boolean isProblemaCartao() {
		return problemaCartao;
	}

	public void setProblemaCartao(boolean problemaCartao) {
		this.problemaCartao = problemaCartao;
	}

	public Map<String, String> getEstados() {
		estados.put("", "");
		estados.put("AC", "AC");
		estados.put("AL", "AL");
		estados.put("AP", "AP");
		estados.put("AM", "AM");
		estados.put("BA", "BA");
		estados.put("CE", "CE");
		estados.put("DF", "DF");
		estados.put("ES", "ES");
		estados.put("GO", "GO");
		estados.put("MA", "MA");
		estados.put("MT", "MT");
		estados.put("MS", "MS");
		estados.put("MG", "MG");
		estados.put("PA", "PA");
		estados.put("PB", "PB");
		estados.put("PR", "PR");
		estados.put("PE", "PE");
		estados.put("PI", "PI");
		estados.put("RJ", "RJ");
		estados.put("RN", "RN");
		estados.put("RS", "RS");
		estados.put("RO", "RO");
		estados.put("RR", "RR");
		estados.put("SC", "SC");
		estados.put("SP", "SP");
		estados.put("SE", "SE");
		estados.put("TO", "TO");
		return estados;
	}

	public void setEstados(Map<String, String> estados) {
		this.estados = estados;
	}
	
	public String getTmpRua() {
		return tmpRua;
	}

	public void setTmpRua(String tmpRua) {
		this.tmpRua = tmpRua;
	}

	public String getTmpBairro() {
		return tmpBairro;
	}

	public void setTmpBairro(String tmpBairro) {
		this.tmpBairro = tmpBairro;
	}

	public String getTmpEstado() {
		return tmpEstado;
	}

	public void setTmpEstado(String tmpEstado) {
		this.tmpEstado = tmpEstado;
	}

	public String getTmpCidade() {
		return tmpCidade;
	}

	public void setTmpCidade(String tmpCidade) {
		this.tmpCidade = tmpCidade;
	}

	public String getMsgCartao() {
		return msgCartao;
	}

	public void setMsgCartao(String msgCartao) {
		this.msgCartao = msgCartao;
	}
	
	public String getMsgCPF() {
		return msgCPF;
	}

	public void setMsgCPF(String msgCPF) {
		this.msgCPF = msgCPF;
	}

	public boolean isProblemaDados() {
		return problemaDados;
	}

	public void setProblemaDados(boolean problemaDados) {
		this.problemaDados = problemaDados;
	}

	/*
	 * Realiza a procura de endereco a partir do CEP informado
	 */
	public void procuraPorCEP() {
		try{
	        Document doc = Jsoup.connect(Constantes.BASE_PROCURA_CEP+getCliente().getEndereco().getCep()).timeout(120000).get();
	        Elements urlPesquisa = doc.select("span[itemprop=streetAddress]");
	        for (Element urlEndereco : urlPesquisa) {
	        	setTmpRua(urlEndereco.text());
	        }
	        
	        urlPesquisa = doc.select("td:gt(1)");
	        setTmpBairro(urlPesquisa.get(0).text());
	        
	        urlPesquisa = doc.select("span[itemprop=addressLocality]");
	        for (Element urlEndereco : urlPesquisa) {
	        	setTmpCidade(urlEndereco.text());
	        }
	        
	        urlPesquisa = doc.select("span[itemprop=addressRegion]");
	        for (Element urlEndereco : urlPesquisa) {
	        	setTmpEstado(urlEndereco.text());
	        }
	        
	        if (Util.isEmpty(getCliente().getEndereco().getCep())) {
	        	setTmpRua("");
	        	setTmpBairro("");
	        	setTmpCidade("");
	        	setTmpEstado("");
	        }
        } catch (Exception e) {
        	setTmpRua("");
        	setTmpBairro("");
        	setTmpCidade("");
        	setTmpEstado("");
        }
	}
	
	/**
	 * Tratativa para o numero do cartao
	 */
	public void validarNumeroCartao() {
		setMsgCartao("");
		setProblemaCartao(false);
		if (!(ValidarCartao.getCardID(getCliente().getPagamento().getNumeroCartao().replace(" ", "")) > -1)) {
			setMsgCartao("Número do cartão inválido");
			setProblemaCartao(true);
		}
	}
	
	/**
	 * Responsabilitando o numero de CPF correto.
	 */
	public void validarNumeroCPF() {
		setMsgCPF("");
		setProblemaDados(false);
		if ( !(Util.isCPF( getCliente().getNrCpf() )) ) {
			setMsgCPF("Número do CPF inválido");
			setProblemaDados(true);
		}
	}
	
	public String getBuscarCNPJ() {
		return Constantes.CNPJ_KITCHIQUI;
	}
	
	public String getBuscarEndereco() {
		return Constantes.ENDERECO_KITCHIQUI;
	}
	
	public String getBuscarSiteKitChiqui() {
		return Constantes.SITE_KITCHIQUI;
	}

	public MalaDireta getMalaDireta() {
		if (this.malaDireta == null) {
			this.malaDireta = new MalaDireta();
		}
		return malaDireta;
	}

	public void setMalaDireta(MalaDireta malaDireta) {
		this.malaDireta = malaDireta;
	}

	public boolean isUsuarioLogado() {
		return !Util.isEmpty(Util.captarClienteSessao());
	}

	public void setUsuarioLogado(boolean usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Cliente getClienteGestao() {
		if ( Util.isEmpty( this.clienteGestao ) ) 
			   this.clienteGestao = new Cliente();
		return clienteGestao;
	}

	public void setClienteGestao(Cliente clienteGestao) {
		this.clienteGestao = clienteGestao;
	}

	public String getTmpStatusEnvio() {
		return tmpStatusEnvio;
	}

	public void setTmpStatusEnvio(String tmpStatusEnvio) {
		this.tmpStatusEnvio = tmpStatusEnvio;
	}

	public String getTmpStatusPedido() {
		return tmpStatusPedido;
	}

	public void setTmpStatusPedido(String tmpStatusPedido) {
		this.tmpStatusPedido = tmpStatusPedido;
	}

	public boolean isClientePesquisado() {
		return clientePesquisado;
	}

	public void setClientePesquisado(boolean clientePesquisado) {
		this.clientePesquisado = clientePesquisado;
	}

	/**
	 * PARA TRABALHAR COM LATITUDE E LONGITUDE FAÇA-SE USO DO METODO ABAIXO
	   public String getLatLong(String CEP) throws Exception {

	        // ***************************************************
	        try {
	            if (CEP.contains("-")) {
	                Document doc = Jsoup
	                        .connect(
	                                "http://maps.googleapis.com/maps/api/geocode/xml?address="
	                                        + CEP + "&language=pt-BR&sensor=true")
	                        .timeout(120000).get();
	                Elements lat = doc.select("geometry").select("location")
	                        .select("lat");
	                Elements lng = doc.select("geometry").select("location")
	                        .select("lng");
	                for (Element Latitude : lat) {
	                    for (Element Longitude : lng) {
	                        return Latitude.text() + "," + Longitude.text();
	                    }
	                }
	            } else {

	                StringBuilder cepHif = new StringBuilder(CEP);  
	                cepHif.insert(CEP.length() - 3, '-');

	                Document doc = Jsoup
	                        .connect(
	                                "http://maps.googleapis.com/maps/api/geocode/xml?address="
	                                        + cepHif + "&language=pt-BR&sensor=true")
	                        .timeout(120000).get();
	                Elements lat = doc.select("geometry").select("location")
	                        .select("lat");
	                Elements lng = doc.select("geometry").select("location")
	                        .select("lng");
	                for (Element Latitude : lat) {
	                    for (Element Longitude : lng) {
	                        return Latitude.text() + "," + Longitude.text();
	                    }
	                }
	            }

	        } catch (SocketTimeoutException e) {

	        } catch (HttpStatusException w) {

	        }
	        return CEP;
	    }
	    */
}
