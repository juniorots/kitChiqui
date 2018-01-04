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
import br.com.kitchiqui.modelo.EnumStatusEnvio;
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
         
         ArrayList<String> emails = new ArrayList<>();
         
         emails.add("juniormsd@gmail.com");
//         EnviarEmail.enviarEmailComercial(retorno.get(0), EnumStatusEnvio.CONFIRMANDO_ENTREGA.getTipo());
         EnviarEmail.recuperarSenha(emails, "SENHA_FAKE");
    }
}
