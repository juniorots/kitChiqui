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
import br.com.kitchiqui.modelo.Parceiro;
import br.com.kitchiqui.util.Util;
import lombok.Cleanup;

public class ParceiroDAOTest extends BaseController {
	
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
    
    /**
     * Util na verificacao de Parceiro ja existente na base de dados
     * @return
     */
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
    
    /**
     * Atualizando Parceiro
     */
    public void atualizarParceiro(Parceiro parc) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ParceiroDAO dao = new ParceiroDAO(entityManager);
        dao.update(parc);
        entityManager.getTransaction().commit();
	}
    
    /**
     * Inserindo Parceiro na base
     */
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
    	@Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ParceiroDAO parDAO = new ParceiroDAO(entityManager);
        
        Parceiro p1 = new Parceiro();
        p1.setSrcImagem("img/home/partners/partner-01.png");
        
        Parceiro tmp = contidoBase(p1);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p1);
        } else {
        	p1.setId(tmp.getId());
        	atualizarParceiro(p1);
        }
//        parDAO.insert(p1);
        
        Parceiro p2 = new Parceiro();
        p2.setSrcImagem("img/home/partners/partner-02.png");
        
        tmp = contidoBase(p2);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p2);
        } else {
        	p2.setId(tmp.getId());
        	atualizarParceiro(p2);
        }
//        parDAO.insert(p2);
        
        Parceiro p3 = new Parceiro();
        p3.setSrcImagem("img/home/partners/partner-03.png");
        
        tmp = contidoBase(p3);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p3);
        } else {
        	p3.setId(tmp.getId());
        	atualizarParceiro(p3);
        }
//        parDAO.insert(p3);
        
        Parceiro p4 = new Parceiro();
        p4.setSrcImagem("img/home/partners/partner-04.png");
        
        tmp = contidoBase(p4);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p4);
        } else {
        	p4.setId(tmp.getId());
        	atualizarParceiro(p4);
        }
//        parDAO.insert(p4);
        
        Parceiro p5 = new Parceiro();
        p5.setSrcImagem("img/home/partners/partner-05.png");
        
        tmp = contidoBase(p5);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p5);
        } else {
        	p5.setId(tmp.getId());
        	atualizarParceiro(p5);
        }
//        parDAO.insert(p5);
        
        Parceiro p6 = new Parceiro();
        p6.setSrcImagem("img/home/partners/partner-06.png");
        
        tmp = contidoBase(p6);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p6);
        } else {
        	p6.setId(tmp.getId());
        	atualizarParceiro(p6);
        }
//        parDAO.insert(p6);
        
        Parceiro p7 = new Parceiro();
        p7.setSrcImagem("img/home/partners/partner-07.png");
        
        tmp = contidoBase(p7);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p7);
        } else {
        	p7.setId(tmp.getId());
        	atualizarParceiro(p7);
        }
//        parDAO.insert(p7);
        
        Parceiro p8 = new Parceiro();
        p8.setSrcImagem("img/home/partners/partner-08.png");
        
        tmp = contidoBase(p8);
        if (Util.isEmpty(tmp)) {
        	inserirParceiro(p8);
        } else {
        	p8.setId(tmp.getId());
        	atualizarParceiro(p8);
        }
        
//        parDAO.insert(p8);
//        entityManager.getTransaction().commit();
    }
}
