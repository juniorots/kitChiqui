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

import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.Produto;
import lombok.Cleanup;

public class ProdutoDAOTest {
	
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
    
//  @Test
  public void mainTest() {

      @Cleanup
      final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

      @Cleanup
      final EntityManager entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Produto produto = new Produto();    
      ProdutoDAO dao = new ProdutoDAO(entityManager);
      
//      usuario.setEmail("juniorots@gmail.com");
//      usuario.setSenha("12345");
//      usuario.setReceberEmail("S");
      
      Produto insProduto = dao.insert(produto);
      
      entityManager.getTransaction().commit();

//      List<Produto> usuarios = dao.selectAll();
//      assertNotNull(usInserido.getId());
//      assertEquals(1, usuario.size());
  }
}
