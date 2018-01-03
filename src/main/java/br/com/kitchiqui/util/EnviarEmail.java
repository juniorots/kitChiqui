/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.kitchiqui.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.kitchiqui.modelo.EnumTipoEmail;

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
            email.addTo(Constantes.ADMINISTRADOR_1);
            email.addTo(Constantes.ADMINISTRADOR_2);
            email.setFrom(Constantes.EMAIL_REMETENTE_GMAIL, "KitChiqui - Administrador");
            
            for (String tmp : emails) {
                email.addBcc(tmp);
            }
            
            email.setSubject(assunto);
            
            if (tipoEmail.equals(EnumTipoEmail.RECUPERACAO_SENHA.getTipo())) {
                conteudo += "<br /><div style='background-color: #47BAC1; height: 5px; width: 80%;'></div>";
            }
            
            // Especificando rodape do e-mail de compra do produto
            if (tipoEmail.equals(EnumTipoEmail.COMPRA_PRODUTO.getTipo())) {
            	conteudo += "<br /><div style='background-color: #D3D3D3; margin-bottom: 20px; width: 80%; text-align: justify'>"
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
            
System.out.println(conteudo);
            
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
     * Tratando a situacao de enviar o email
     * para recuperacao de senha perdida.
     */
    public static void recuperarSenha(ArrayList<String> emails, String adicionalConteudo) {
        
        String assunto = "[kitchiqui] - Recuperação de Senha.";
        String conteudo = "<html><head><title>Recuperação de senha - KitChiqui.</title></head>"
                + "<body><br /><br />Olá! Recebemos uma solicitação de alteração de senha.<br /><br />"
                + "Assim acreditamos que sendo uma petição realizada por você, geramos uma nova senha! <br />"
                + "No entanto, caso essa solicitação não tenha sido gerada por favor,<br />" 
                + "solicitamos o quanto antes que altere-a, prezando pela segurança dos seus dados. <br /><br /><br />"
                + "Tome nota da sua nova senha: <h1>"
                + "<strong><span style='background-color: #F0FFF0'>" +adicionalConteudo+ "</span></strongs></h1><br />"
                + "<b>[ - POR FAVOR, NÃO RESPONDA ESSE E-MAIL. - ]</b><br />"
                + "</body></html>";
        
        tratarEnvio(emails, assunto, conteudo, EnumTipoEmail.RECUPERACAO_SENHA.getTipo());
    }
}

    