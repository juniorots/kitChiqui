/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Cleanup;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kitchiqui.base.ClienteDAO;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.EnumStatusCompra;
import br.com.kitchiqui.modelo.EnumTipoEmail;
import br.com.kitchiqui.modelo.EnumTipoPagamento;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.EnviarEmail;
import br.com.kitchiqui.util.Util;

public class EmailTest {
	
	public EmailTest() {		
	}
	
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public String resumoTotal(Cliente cliente) {
    	Double tmp = 0.0;
		for (Produto p : cliente.getListaCarrinho()) {
			if (!p.getCompraProduto().getCodCompra().equals(EnumStatusCompra.PROCESSANDO.getTipo()))
    			continue;
			
    		tmp += p.getPreco() * p.getQuantidade();
    	}
		tmp += cliente.getEndereco().getPrecoModoEnvio();
    	return "R$ " + Util.formatarValorMoeda(tmp);
    }
    
//    @Test
    public void model() {
    }
    
    @Test
    public void testarEnvio() {
    	 @Cleanup
         final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

         @Cleanup
         final EntityManager entityManager = entityManagerFactory.createEntityManager();
         entityManager.getTransaction().begin();
         Cliente cliente = new Cliente();
         
         ClienteDAO dao = new ClienteDAO(entityManager);
         cliente.setEmail("juniorots@gmail.com");
         List<Cliente> retorno = dao.findByStringField("email", cliente.getEmail(), true, 0, 1);
         
         ArrayList<String> email = new ArrayList<>();
         StringBuilder tmp = new StringBuilder();

         String produtos = "";
         for (Produto p : cliente.getListaCarrinho()) {
        	if (p.getCompraProduto().getStatusCompra().equals(EnumStatusCompra.PROCESSANDO.getTipo()))  
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
         
         tmp.append("<img src='http://localhost:8080/kitchiqui-1/img/tituloEmailKitChiqui.png'/> <br />");
         
         tmp.append("<div style='margin-top: 20px;'>"
         		+ "<strong><span style='font-size: 16px'>Pedido Recebido</span></strong>"
         		+ "<br /><br />"
         		+ "Olá <strong>"+retorno.get(0).getNomeCompleto()+"</strong>"
         		+ "<br />"
         		+ "Obrigado por comprar na KitChiqui!"
         		+ "<br />"
         		+ "O seu pedido no valor de " + resumoTotal(cliente) + ", "
   				+ "realizado em " + Util.formataData(Calendar.getInstance().getTime())
   				+ " foi recebido com sucesso!"
   				+ "<br /><br /><br /><br />"
   				+ "</div>"
   				
   				// Produtos...
   				+ "<div style='margin-top: 20px;'>"
   				+ "<strong><span style='font-size: 12px'>Dados do pedido:</span></strong>"
   				+ "<table>"
   					+ "<thead>"
   					+ "<tr style='background-color: #47BAC1'; text-align: center; font-weight: bold; color: white;>"
   						+"<th>Produto</th>"
   						+"<th>Quantidade</th>"
   						+"<th>Preço Unitário</th>"
   						+"<th>Valor Total</th>"
   					+ "</tr>"
   					+ "</thead>"
   					+ "<tbody>"
   					+ produtos
   					+ "<tr rowspan='4' style='font-weight: bold; text-align: right'>"
   					+ "<td>Frete: "+ cliente.getEndereco().getPrecoModoEnvioFormatado() +"</td>"
   					+ "</tr>"
   					+ "<tr rowspan='4' style='font-weight: bold; text-align: right; font-size: 14px;'>"
   					+ "<td>Valor total: "+ resumoTotal(cliente) +"</td>"
   					+ "</tr>"
   					+ "</tbody>"
   				+ "</table>"
         		+ "</div>"
   				
         		// Meio de pagamento...
				+ "<div style='margin-top: 20px;'>"
				+ "<strong><span style='font-size: 12px'>Dados do pagamento:</span></strong>"
				+ "<table>"
					+ "<thead>"
					+ "<tr style='background-color: #47BAC1'; text-align: center; font-weight: bold; color: white;>"
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
				 
				//Meio de pagamento...
				+ "<div style='margin-top: 20px;'>"
				+ "<strong><span style='font-size: 12px'>Dados da entrega:</span></strong>"
				+ "<table>"
					+ "<thead>"
					+ "<tr style='background-color: #47BAC1'; text-align: center; font-weight: bold; color: white;>"
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
         
         EnviarEmail.tratarEnvio(email, "Pedido Recebido - KitChiqui.com.br (Comercial)", tmp.toString(), 
        		 EnumTipoEmail.COMPRA_PRODUTO.getTipo());
    }
}
