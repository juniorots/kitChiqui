/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */

package br.com.kitchiqui.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.Produto;

/**
 *
 * @author Jose Alves
 */
public class Util {
    
	public static String gerarCodigoRastreio() {
		String retorno = "00000000";
		int min = 9999999;
		int max = 99999999;
		try {
			int num = ThreadLocalRandom.current().nextInt(min, max+1);
			retorno = String.valueOf(num);
		} catch (IllegalArgumentException ie) {
			ie.printStackTrace();
		}
		return retorno;
	}
	
    /**
     * Capta a data no formato, especificado pelo Cliente
     * caso nao seja especificado um formato valido
     * ser utilizado o padrao: dd/MM/yyyy
     * 
     * @return 
     */
    public static String captarDataFormatada ( Date data, String padrao ) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat formato = null;
        if ( isEmpty (padrao) ) {
            formato  = new SimpleDateFormat("dd/MM/yyyy");        
        } else {
            formato  = new SimpleDateFormat( padrao );        
        }
        sb.append( formato.format(data) );
        return sb.toString();
    }
   
    /**
     * Verificando equivalencia entre produtos
     * @param p1
     * @param p2
     * @return
     */
    public static boolean equalsProduto(Produto p1, Produto p2) {
    	return p1.getId().equals(p2.getId());
    }
    
    /**
     * Util para o tratamento de conversao dos valores do range
     * @param produto
     * @param primeiroValor
     * @param segundoValor
     */
    public static void tratarRangePreco(Produto produto, String primeiroValor, String segundoValor){
    	String exp = "[0-9,]+";
    	Pattern p = Pattern.compile(exp);
    	
    	try {
    		if (primeiroValor.equals(""))
    			primeiroValor = null;
    		
    		if (segundoValor.equals(""))
    			segundoValor = null;

    		Matcher m = p.matcher(primeiroValor);
	    	if (m.find()) 
	    		produto.setPrimeiroFiltroPreco( Double.parseDouble( m.group().substring(0, m.group().indexOf(',') ) ) );
	
	    	m = p.matcher(segundoValor);
	    	if (m.find())
	    		produto.setSegundoFiltroPreco( Double.parseDouble( m.group().substring(0, m.group().indexOf(',') ) ) );
    	} catch (Exception e) {
    		// TODO nothing...
    	}
    }
    
    /*
     * Montando a mensagem estilo balao...
     */
    public static void montarMensagem(FacesMessage.Severity tipo, String mensagem) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(tipo, mensagem, "") );
            context.getExternalContext().getFlash().setKeepMessages(true);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
    * Montar mensagem no centro da tela
    */
    public static void montarMensagemModal(FacesMessage.Severity tipo, String titulo, String corpo) {
        FacesMessage mensagem = new FacesMessage(tipo, titulo, corpo);
        RequestContext.getCurrentInstance().showMessageInDialog(mensagem);
    }
    
    /*
     * Verificando existencia de valor vazio para montar o link
     */
    public static String linkTacoVazio(String arg) {
        if ( Util.isEmpty(arg) ) {
            arg = montarLink(arg, "5");
        } else {
            if ( "*".trim().equalsIgnoreCase( arg )) {
                arg = montarLink(arg, "4");
            }
        }
        return arg;
    }
    
    /**
     * Montando o link para especificacao de nota de rodape
     * @param descricao
     * @param codMensagem
     * @return 
     */
    public static String montarLink ( String descricao, String... codMensagem ) {
        StringBuilder retorno = new StringBuilder();
        String tmp = "";

        for (String cod : codMensagem) {
            tmp += cod;
            if(codMensagem.length > 1) 
                cod += ",";
        }
        if ( !Util.isEmpty(descricao) )
            retorno.append(descricao);
        else 
            retorno.append("[vazio]");
        retorno.append(" <nota>"+ tmp +"</nota>");
        return retorno.toString();
    }
    
    /**
     * Especificado para montar a nota de rodape...
     * @param codigoMensagem
     * @return 
     */
    public static String montarDescricaoLink( String codigoMensagem ) {
        StringBuilder retorno = new StringBuilder();
//        retorno.append(Constantes.notas.get(codigoMensagem.trim()));
        return retorno.toString();
    }
    
   /*
    * Verificando se o Cliente esta logado no sistema.
    */
    public static boolean isClienteLogado() {
        if ( Util.isEmpty( Util.captarClienteSessao() ) ) {
            FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "É porque não dá mesmo!", 
                    "Ops... Identifiquei que você não entrou no sistema!!<br /> "
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "Preciso que você faça isso, para liberar a consulta!!");
            RequestContext.getCurrentInstance().showMessageInDialog(mensagem);
            return false;
        } else {
            return true;
        }
    }
    
    /*
     * redirecionando...
     */
    public static void forward( String caminho ) {
        try {
        	FacesContext context = FacesContext.getCurrentInstance();
        	ViewHandler vh = context.getApplication().getViewHandler();
        	UIViewRoot vr = vh.createView(context, caminho);
        	context.setViewRoot(vr);
        	context.renderResponse();
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Trabalha na formatacao da data no formato yyyy-MM-dd
     * @param data
     * @return 
     */
    public static Date formatarDataBanco ( String pData ) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = new Date( formato.parse(pData).getTime() );
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return data;
    }
    
    public static String formataData ( Date data ) {
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    	return formato.format(data);
    }
    
    /*
     * Tratando formatacao para valor de moeda
     */
    public static String formatarValorMoeda(Double valor) {
    	try {
	    	NumberFormat nf = new DecimalFormat("###,##0.00");
			return nf.format(valor);
    	} catch (Exception e) {
    		return "0,00";
    	}
    }
    
    public static String cifrar(String texto) {
        StringBuilder sb = new StringBuilder();
        
        if ( isEmpty( texto )) {
            return "";
        }
        
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            byte message[] = algoritmo.digest(texto.getBytes("ISO-8859-1"));
            for (byte b : message) {
                sb.append( String.format("%02X",0xFF & b) );
            }
        } catch (NoSuchAlgorithmException ae) {
            ae.printStackTrace();
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        }
        return sb.toString();
    }
    
    /**
     * Dever-se-a ser utilizado para recuperacao de senha, quando o Cliente solicitar tal funcionalidade
     * @param texto
     * @return 
     */
    public static String cifrarRecuperacao (String texto) {
        StringBuilder sb = new StringBuilder();
        
        if ( isEmpty( texto )) {
            return "";
        }
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("MD5");
            byte message[] = algoritmo.digest(texto.getBytes("ISO-8859-1"));
            
            sb.append(message);
        }  catch (NoSuchAlgorithmException ae) {
            ae.printStackTrace();
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        }
        
        return sb.toString();
    }
    
    /**
     * Tratando da valicao de e-mail
     * @param email
     * @return 
     */
    public static boolean validarEmail( String email ) {
        String regex = "[a-z._-].+@[a-z.]+";
        return email.matches(regex);
    }
    
    public static boolean isCPF(String CPF) {
    	if (Util.isEmpty(CPF)) 
    		return false;
    	
    	CPF = CPF.replaceAll("[.]", "").replaceAll("[,]", "").replaceAll("[-]", "");
    	
	    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
	        CPF.equals("22222222222") || CPF.equals("33333333333") ||
	        CPF.equals("44444444444") || CPF.equals("55555555555") ||
	        CPF.equals("66666666666") || CPF.equals("77777777777") ||
	        CPF.equals("88888888888") || CPF.equals("99999999999") ||
	       (CPF.length() != 11))
	    	return(false);

	    char dig10, dig11;
	    int sm, i, r, num, peso;

	    try {
	    	sm = 0;
	    	peso = 10;
	    	for (i=0; i<9; i++) {              
    	        num = (int)(CPF.charAt(i) - 48); 
    	        sm = sm + (num * peso);
    	        peso = peso - 1;
    	      }

    	      r = 11 - (sm % 11);
    	      if ((r == 10) || (r == 11))
    	         dig10 = '0';
    	      else dig10 = (char)(r + 48); 

    	// Calculo do 2o. Digito Verificador
    	      sm = 0;
    	      peso = 11;
    	      for(i=0; i<10; i++) {
    	        num = (int)(CPF.charAt(i) - 48);
    	        sm = sm + (num * peso);
    	        peso = peso - 1;
    	      }

    	      r = 11 - (sm % 11);
    	      if ((r == 10) || (r == 11))
    	         dig11 = '0';
    	      else dig11 = (char)(r + 48);

    	// Verifica se os digitos calculados conferem com os digitos informados.
    	      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
    	         return(true);
    	      else return(false);
    	    } catch (InputMismatchException erro) {
    	        return(false);
    	    }
	  }

    /**
     * Verificando objeto nulo
     * @param obj
     * @return 
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String && ((String) obj).trim().equals("") ) {
            return true;
        } else if (obj instanceof Collection && ((Collection) obj).size() == 0)  {
            return true;
        } else {
            return false;
        }
    }
    
   /*
    * Gravando o Cliente na sessao
    */
    public static void gravarClienteSessao(Cliente cliente) {
        FacesContext fc =   FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
        sessao.setAttribute( "CLIENTE", cliente );
    }
    
   /*
    * Captando o usuário da sessao
    */
    public static Cliente captarClienteSessao() {
        Cliente cliente = null;
        ExternalContext external =  FacesContext.getCurrentInstance().getExternalContext();
        HttpSession sessao = (HttpSession) external.getSession(true);
        
        if ( !isEmpty(sessao.getAttribute("CLIENTE") ) )
        	cliente = (Cliente) sessao.getAttribute("CLIENTE"); 
        return cliente;
    }
    
    /*
     * Util para limpar dados da sessao
     */
    public static void limparClienteSessao() {
       FacesContext fc =   FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
        sessao.setAttribute("CLIENTE", null);
    }
}
