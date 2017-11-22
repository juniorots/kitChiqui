/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.kitchiqui.modelo.Blog;
import br.com.kitchiqui.modelo.Cliente;
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
	
	private Cliente cliente= null;
	private Produto produto = null;
	private Blog blog = null;
	private String tmpPrimeiro;
	private String tmpSegundo;
	
	private String tmpRua;
	private String tmpBairro;
	private String tmpEstado;
	private String tmpCidade;
	
	private String msgCartao;
	private boolean problemaCartao;
	
	private Map<String, String> estados = new HashMap();
	private Map<Integer, Integer> listaMes = new HashMap();
	private Map<Integer, Integer> listaAno = new HashMap();
	
	public void resetarConfig() {
		try {
			tmpPrimeiro = "";
			getProduto().setTitulo("");
		} catch (Exception e) {
			// TODO nothing...
		}
	}
	
	public void paginaPrincipal(){
		resetarConfig();
		Util.forward(PAGINA_PRINCIPAL);
	}
	
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
	   if ( Util.isEmpty( this.cliente ) ) {
		   this.cliente = new Cliente();
	   }
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
