/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Cleanup;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kitchiqui.base.ParceiroDAO;
import br.com.kitchiqui.modelo.Parceiro;

public class ParceiroDAOTest {
	
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

    @Test
    public void example() {
    }
    
//    @Test
    public void mainTest(){
    	@Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        ParceiroDAO parDAO = new ParceiroDAO(entityManager);
        
        Parceiro p1 = new Parceiro();
        p1.setSrcImagem("img/home/partners/partner-01.png");
        parDAO.insert(p1);
        
        Parceiro p2 = new Parceiro();
        p2.setSrcImagem("img/home/partners/partner-02.png");
        parDAO.insert(p2);
        
        Parceiro p3 = new Parceiro();
        p3.setSrcImagem("img/home/partners/partner-03.png");
        parDAO.insert(p3);
        
        Parceiro p4 = new Parceiro();
        p4.setSrcImagem("img/home/partners/partner-04.png");
        parDAO.insert(p4);
        
        Parceiro p5 = new Parceiro();
        p5.setSrcImagem("img/home/partners/partner-05.png");
        parDAO.insert(p5);
        
        Parceiro p6 = new Parceiro();
        p6.setSrcImagem("img/home/partners/partner-06.png");
        parDAO.insert(p6);
        
        Parceiro p7 = new Parceiro();
        p7.setSrcImagem("img/home/partners/partner-07.png");
        parDAO.insert(p7);
        
        Parceiro p8 = new Parceiro();
        p8.setSrcImagem("img/home/partners/partner-08.png");
        parDAO.insert(p8);
        
        entityManager.getTransaction().commit();
    }
}
