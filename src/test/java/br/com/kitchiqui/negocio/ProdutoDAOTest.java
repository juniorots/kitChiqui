/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.EnumTipoProduto;
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
      
      produto.setSrcImagem("img/home/banner-slider/shoe1.png");
      produto.setTitulo("TÊNIS AMARELO");
      produto.setSubTitulo("Promoção do mês");
      produto.setDescritivo("Sensação do verão");
      produto.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      
      
      dao.insert(produto);      
      entityManager.getTransaction().commit();

//      Produto insProduto = dao.insert(produto);
//      List<Produto> usuarios = dao.selectAll();
//      assertNotNull(usInserido.getId());
//      assertEquals(1, usuario.size());
  }
}
