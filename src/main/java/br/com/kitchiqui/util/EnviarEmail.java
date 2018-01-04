/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.kitchiqui.util;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.EnumStatusCompra;
import br.com.kitchiqui.modelo.EnumStatusEnvio;
import br.com.kitchiqui.modelo.EnumTipoEmail;
import br.com.kitchiqui.modelo.Produto;

/**
 *
 * @author Jose Alves
 */
public class EnviarEmail {
    
    /**
     * Enviar o email para a lista de usarios especificados
     * @param emails
     * @param assunto
     * @param conteudo 
     */
    public static void tratarEnvio(ArrayList<String> emails, String assunto, String conteudo, Integer tipoEmail) {
        HtmlEmail email = new HtmlEmail();
        
        try {
            email.setHostName(Constantes.HOST_NAME_GMAIL);
            email.addBcc(Constantes.ADMINISTRADOR_1);
            email.addBcc(Constantes.ADMINISTRADOR_2);
            email.setFrom(Constantes.EMAIL_REMETENTE_GMAIL, "KitChiqui - Administrador");
            
            for (String tmp : emails) {
                email.addTo(tmp);
            }
            
            email.setSubject(assunto);
            
            if (tipoEmail.equals(EnumTipoEmail.RECUPERACAO_SENHA.getTipo())) {
                conteudo += "<br /><div style='background-color: #47BAC1; height: 5px; width: 80%;'></div>";
            }
            
            // Especificando rodape do e-mail de compra do produto
            if (tipoEmail.equals(EnumTipoEmail.COMPRA_PRODUTO.getTipo())) {
            	conteudo += "<br /><br /><div style='background-color: #D3D3D3; margin-bottom: 20px; width: 80%; text-align: justify'>"
            					+ "<small>"
            						+ "	<strong><span style='font-size: 12px;'>Observações importantes:</span></strong>"
            						+ "<br /><br />"
            						+ "<strong>Sobre a compra</strong>"
            						+ "<br /><br />"
            						+ "- Para sua segurança, as informações contidas em seu cadastro são passíveis de confirmação, "
            						+ "que poderá ser feita através de contato telefônico. Na necessidade de confirmação dos "
            						+ "dados informados o prazo para entrega do seu pedido pode sofrer alguma alteração."
            						+ "<br />"
            						+ "- Caso exista algum produto em seu pedido que não possa ser entregue por questões de "
            						+ "indisponibilidade momentânea, decorrente de uma demanda superior àquela "
            						+ "estimada no momento da compra, a "+ Constantes.SITE_KITCHIQUI +" devolverá a quantia paga "
       								+ "pelo produto, assim como o valor proporcional do frete (quando esse efetuado)."
   									+ "<br /><br />"
   									+ "<strong>Alteração de pedido</strong>"
   									+ "<br /><br />"
   									+ "- Após o fechamento da compra não será possível alterar o endereço do destinatário, "
   									+ "incluir ou substituir produtos do pedido, ou os serviços de entrega e a "
   									+ "modalidade de pagamento."
   									+ "<br />"
   									+ "- O envio de produtos para pedidos com mais de um item/quantidade poderá ser "
   									+ "parcial dependendo da disponibilidade em nossos estoques e de "
   									+ "fornecedores, sem alterar o valor total do frete."
   									+ "<br /><br />"
   									+ "<strong>Sobre a entrega</strong>"
   									+ "<br /><br />"
   									+ "- Para contagem do prazo de entrega consideramos como dias úteis de segunda a sexta-feira, "
   									+ "das 8h às 18h, exceto feriados."
   									+ "<br />"
   									+ "- Sua encomenda será entregue apenas a uma pessoa habilitada, "
   									+ "pois nossos portadores não poderão deixar o pedido sem a assinatura do protocolo."
   									+ "<br />"
   									+ "- Para pedidos com mais de um produto e com diferentes prazos de entrega, "
   									+ "prevalecerá o prazo mais longo."
   									+ "<br />"
   									+ "- Faremos 3 tentativas para entrega do seu pedido. No caso de não conseguirmos, "
   									+ "o pedido volta para a KitChiqui e será providenciada a devolução do valor pago pelo produto."
   									+ "<br />"
   									+ "- Na impossibilidade da entrega do pedido no endereço indicado pelo cliente, por força "
   									+ "maior e/ou caso furto, a KitChiqui fará contato telefônico para agendar nova entrega."
   									+ "<br />"
   									+ "- Em virtude do Protocolo ICMS nº 21, de 1º de abril de 2011, "
   									+ "assinado pelos Estados AC, AL, AP, BA, CE, ES, GO, MA, MT, MS, PA, PB, PE, PI, "
   									+ "RN, RR, RO e SE e o DF, os produtos adquiridos via comércio eletrônico "
   									+ "destinados aos mencionados Estados poderão demorar alguns dias "
   									+ "além do previsto, em razão de problemas de cobrança de ICMS nas "
   									+ "fronteiras desses Estados. Para minimizar esse "
   									+ "problema já tomamos as medidas judiciais cabíveis para afastar a "
   									+ "incidência desse Protocolo e esperamos resolver a questão com brevidade."
   									+ "<br /><br />"
   									+ "Atenciosamente,"
   									+ "<br />"
   									+ "<strong><span style='font-size: 12px'>KitChiqui Serviços Ltda.</span></strong>"
            					+ "</small>"
            				+ "</div>";
            }
            email.setHtmlMsg(conteudo);
            
            // Tratando mensagem alternativa
            email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML... :-(");

            email.setSmtpPort(Constantes.PORTA_SMTP_GMAIL);
            email.setAuthenticator(new DefaultAuthenticator(Constantes.EMAIL_REMETENTE_GMAIL, Constantes.SENHA_REMETENTE_GMAIL));
            email.setSSLOnConnect(true);
            
            // Enviando email
            email.send();
            
        } catch (EmailException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
    }
    
    /**
     * Util para o caso do cliente efetuar uma compra no site ou notifica-lo do status do pedido
     * @param cliente
     */
    public static void enviarEmailComercial(Cliente cliente, Integer statusEnvio) {
    	ArrayList<String> email = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        StringBuilder assunto =  new StringBuilder();

		email.add(cliente.getEmail());
        
        String produtos = "";
        for (Produto p : cliente.getListaCarrinho()) {
       	if (p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.PROCESSANDO.getTipo()))  
				produtos += "<tr>"
						+ "<td>"+p.getTitulo()+"</td>"
						+ "<td>"+p.getQuantidade()+"</td>"
						+ "<td>"+p.getPrecoFormatado()+"</td>"
						+ "<td>"+Util.formatarValorMoeda(p.getPreco() * p.getQuantidade())+"</td>"
						+ "</tr>";
        }
        
        String tipoPagamento = "";
        switch (cliente.getPagamento().getTipoPagamento()) {
        	case  1:
        		tipoPagamento = "Pay Pal";
        		break;
        	case 2:
        		tipoPagamento = "Cartão de crédito";
        		break;
        	default:
        		tipoPagamento = "Cartão de débito";
        }
        
        tmp.delete(0, tmp.length());

        tmp.append("<strong><span style='font-size: 25px; font-family: monospace'>KIT</span></strong> ");
        tmp.append("<strong><span style='font-size: 25px; color: #47BAC1; font-family: monospace'>CHIQUI</span></strong>");
        tmp.append("<br /><div style='background-color: #47BAC1; height: 5px; width: 80%;'></div>");
        
        tmp.append("<div style='margin-top: 20px;'>"
        		+ "<strong><span style='font-size: 16px'>Pedido Recebido</span></strong>"
        		+ "<br /><br />"
        		+ "Olá <strong>"+cliente.getNomeCompleto()+"</strong>,"
        		+ "<br />"
        		+ "Obrigado por comprar na <strong>KitChiqui</strong>!"
        		+ "<br />"
        		+ "O seu pedido no valor de " + resumoTotal(cliente) + ", "
  				+ "realizado em " + Util.formataData(Calendar.getInstance().getTime())
  				+ " foi recebido com sucesso!"
  				+ " <br /><br />");
        
        if (statusEnvio.equals(EnumStatusEnvio.AGUARDANDO_PAGAMENTO.getTipo())) {
	       	tmp.append("<strong><span style='color: #191970; font-size: 18px;'>Estamos aguardando a confirmação do pagamento.</span></strong>");
	       	assunto.append("Pedido Recebido - KitChiqui.com.br (Comercial)");
        }
        
        if (statusEnvio.equals(EnumStatusEnvio.PREPARANDO_ENVIO.getTipo())) { 
	       	tmp.append("<strong><span style='color: #191970; font-size: 18px;'>Seu pedido foi aprovado, estamos preparando o envio do(s) produto(s).</span></strong>");
	       	assunto.append("Pedido Aprovado - KitChiqui.com.br (Comercial)");
        }
        
        if (statusEnvio.equals(EnumStatusEnvio.CONFIRMANDO_ENTREGA.getTipo())) {
	       	tmp.append("<strong><span style='color: #191970; font-size: 18px;'>Fomos notificados que seu produto foi entregue! :-)</span></strong>");
	       	tmp.append("<br />Caso haja algum problema com sua entrega, favor entrar em contato informando o ocorrido pelo canal: <strong>kitchiqui@gmail.com</strong>");
	       	tmp.append("<br />A omissão por informações durante <strong>7 (sete) dias</strong>, nos faz acreditar que o produto foi entrege corretamente.");
	       	assunto.append("Notificação de Entrega - KitChiqui.com.br (Comercial)");
        }

        tmp.append("<br /><br />"
  				+ "</div>"
  				
  				// Produtos...
  				+ "<div style='margin-top: 20px;'>"
  				+ "<strong><span style='font-size: 12px'>Dados do pedido:</span></strong>"
  				+ "<table style='width: 80%'>"
  					+ "<thead>"
  					+ "<tr style='background-color: #47BAC1; text-align: left; font-weight: bold; color: black;'>"
  						+"<th>Produto</th>"
  						+"<th>Quantidade</th>"
  						+"<th>Preço Unitário</th>"
  						+"<th>Valor Total</th>"
  					+ "</tr>"
  					+ "</thead>"
  					+ "<tbody>"
  					+ produtos
  					+ "<tr style='font-weight: bold; text-align: right'>"
  					+ "<td colspan='4'>Frete: "+ cliente.getEndereco().getPrecoModoEnvioFormatado() +"</td>"
  					+ "</tr>"
  					+ "<tr style='font-weight: bold; text-align: right; font-size: 14px;'>"
  					+ "<td colspan='4'>Valor total: "+ resumoTotal(cliente) +"</td>"
  					+ "</tr>"
  					+ "</tbody>"
  				+ "</table>"
        		+ "</div>"
  				
        		// Meio de pagamento...
				+ "<div style='margin-top: 20px;'>"
				+ "<strong><span style='font-size: 12px'>Dados do pagamento:</span></strong>"
				+ "<table style='width: 80%'>"
					+ "<thead>"
					+ "<tr style='background-color: #47BAC1; text-align: left; font-weight: bold; color: black;'>"
						+"<th>Forma de Pagamento</th>"
						+"<th>Valor</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ "<tr>"
						+ "<td>"+ tipoPagamento +"</td>"
						+ "<td>"+ resumoTotal(cliente) +"</td>"
					+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ "</div>"
				 
				//Meio de entrega...
				+ "<div style='margin-top: 20px;'>"
				+ "<strong><span style='font-size: 12px'>Dados da entrega:</span></strong>"
				+ "<table style='width: 80%'>"
					+ "<thead>"
					+ "<tr style='background-color: #47BAC1; text-align: left; font-weight: bold; color: black;'>"
						+"<th>Destinatário</th>"
						+"<th>Endereço</th>"
						+"<th>Bairro</th>"
						+"<th>Cidade</th>"
						+"<th>CEP</th>"
					+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
					+ "<tr>"
						+ "<td>"+ cliente.getNomeCompleto() +"</td>"
						+ "<td>"+ cliente.getEndereco().getNomeRua() +"</td>"
						+ "<td>"+ cliente.getEndereco().getBairro() +"</td>"
						+ "<td>"+ cliente.getEndereco().getNomeCidade() + " - " + cliente.getEndereco().getEstado() +"</td>"
						+ "<td>"+ cliente.getEndereco().getCep() +"</td>"
					+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ "</div>"
       	);
        
        EnviarEmail.tratarEnvio(email, assunto.toString(), tmp.toString(), EnumTipoEmail.COMPRA_PRODUTO.getTipo());
    }
    
    /**
     * Tratando a situacao de enviar o email
     * para recuperacao de senha perdida.
     */
    public static void recuperarSenha(ArrayList<String> emails, String adicionalConteudo) {
        
        String assunto = "Recuperação de senha - KitChiqui.com.br (Segurança)";
        StringBuilder tmp = new StringBuilder();
        tmp.delete(0, tmp.length());
        
        tmp.append("<strong><span style='font-size: 25px; font-family: monospace'>KIT</span></strong> ");
        tmp.append("<strong><span style='font-size: 25px; color: #47BAC1; font-family: monospace'>CHIQUI</span></strong>");
        tmp.append("<br /><div style='background-color: #47BAC1; height: 5px; width: 80%;'></div>");
        
        tmp.append("<br />Olá! Recebemos uma solicitação de alteração de senha.");
        tmp.append("<br /><br />Nova senha: ");
        tmp.append("<strong><span style='color: #191970; font-size: 18px;'>" + adicionalConteudo + "</span></strong>");
        tmp.append("<br /><br /><div style='background-color: #D3D3D3; margin-bottom: 10px; width: 80%; text-align: justify'>");
        tmp.append("<small>"
        		+ "<br />&nbsp;Caso essa solicitação não tenha sido gerada por você, por favor, solicitamos o quanto antes que altere-a, prezando "
        		+ "pela segurança dos seus dados."
        		+ "<br /><br />&nbsp;<strong> - ESTE É UM E-MAIL AUTOMÁTICO, POR ISSO NÃO É NECESSÁRIO RESPONDÊ-LO - </strong>"
        		+ "<br /><br />Atenciosamente,"
        		+ "<br /> <strong>KitChiqui Serviços Ltda.</strong>");
        tmp.append("</small></div><br />");
        
//        String conteudo = "<html><head><title>Recuperação de senha - KitChiqui.</title></head>"
//                + "<body><br /><br />Olá! Recebemos uma solicitação de alteração de senha.<br /><br />"
//                + "Assim acreditamos que sendo uma petição realizada por você, geramos uma nova senha! <br />"
//                + "No entanto, caso essa solicitação não tenha sido gerada por favor,<br />" 
//                + "solicitamos o quanto antes que altere-a, prezando pela segurança dos seus dados. <br /><br /><br />"
//                + "Tome nota da sua nova senha: <h1>"
//                + "<strong><span style='background-color: #F0FFF0'>" +adicionalConteudo+ "</span></strongs></h1><br />"
//                + "<b>[ - POR FAVOR, NÃO RESPONDA ESSE E-MAIL. - ]</b><br />"
//                + "</body></html>";
        
        tratarEnvio(emails, assunto, tmp.toString(), EnumTipoEmail.RECUPERACAO_SENHA.getTipo());
    }
    
    /**
     * Util para o caso de envio do e-mail comercial ao cliente
     * @param cliente
     * @return
     */
    public static String resumoTotal(Cliente cliente) {
    	Double tmp = 0.0;
		for (Produto p : cliente.getListaCarrinho()) {
			if (!p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.PROCESSANDO.getTipo()))
    			continue;
			
    		tmp += p.getPreco() * p.getQuantidade();
    	}
		tmp += cliente.getEndereco().getPrecoModoEnvio();
    	return "R$ " + Util.formatarValorMoeda(tmp);
    }
}
