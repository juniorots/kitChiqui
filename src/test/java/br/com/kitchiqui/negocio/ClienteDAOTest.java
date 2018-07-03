/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kitchiqui.base.ParceiroDAO;
import br.com.kitchiqui.controller.BaseController;
import br.com.kitchiqui.controller.ClienteMB;
import br.com.kitchiqui.modelo.Parceiro;
import br.com.kitchiqui.util.Util;
import lombok.Cleanup;

public class ClienteDAOTest extends BaseController {
	
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
    public void example() {
    }
    
    public Parceiro contidoBase(Parceiro parc) {
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
                
        ParceiroDAO dao = new ParceiroDAO(entityManager);
        
        if (!Util.isEmpty(dao.findByStringField("srcImagem", parc.getSrcImagem(), true, 0, 1))) {
        	return dao.findByStringField("srcImagem", parc.getSrcImagem(), true, 0, 1).get(0);
        } else 
        	return null;
    }
    
    public void atualizarParceiro(Parceiro parc) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ParceiroDAO dao = new ParceiroDAO(entityManager);
        dao.update(parc);
        entityManager.getTransaction().commit();
	}
    
    public void inserirParceiro(Parceiro parc) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ParceiroDAO dao = new ParceiroDAO(entityManager);
        dao.insert(parc);
        entityManager.getTransaction().commit();
	}
    
    @Test
    public void mainTest(){
    	ClienteMB clienteMB = new ClienteMB();
//    	clienteMB.tratarBackUp();
    }
}
