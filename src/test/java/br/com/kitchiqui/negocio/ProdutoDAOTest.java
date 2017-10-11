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

//  @Test
  public void example() {
  }
    
  @Test
  public void mainTest() {
	  
	  @Cleanup
      final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

      @Cleanup
      final EntityManager entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();
//
      Produto prod = new Produto();    
      ProdutoDAO dao = new ProdutoDAO(entityManager);
//      
      prod.setSrcImagem("img/home/banner-slider/shoe1.png");
      prod.setTitulo("TÊNIS AMARELO");
      prod.setSubTitulo("Promoção do mês");
      prod.setDescritivo("Sensação do verão");
      prod.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      dao.insert(prod);      
      
      Produto prod2 = new Produto();   
      
      prod2.setSrcImagem("img/home/banner-slider/shoe2.png");
      prod2.setTitulo("TÊNIS BRANCO");
      prod2.setSubTitulo("Suavidade no estilo");
      prod2.setDescritivo("Conquista cada passo");
      prod2.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      dao.insert(prod2);      
//      
      Produto prod3 = new Produto();   
      
      prod3.setSrcImagem("img/home/banner-slider/shoe3.png");
      prod3.setTitulo("TÊNIS PRETO");
      prod3.setSubTitulo("Estilo Blade");
      prod3.setDescritivo("Dominando um estilo");
      prod3.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      dao.insert(prod3);
      
      Produto prod4 = new Produto();   
      
      prod4.setSrcImagem("img/home/featured-product/product-01.jpg");
      prod4.setTitulo("Camisa verde");
      prod4.setPreco(new Double(75));
      prod4.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod4);
      
      Produto prod5 = new Produto();   
      
      prod5.setSrcImagem("img/home/featured-product/product-02.jpg");
      prod5.setTitulo("Óculos de sol");
      prod5.setPreco(new Double(300));
      prod5.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod5);
      
      Produto prod6 = new Produto();   
      
      prod6.setSrcImagem("img/home/featured-product/product-03.jpg");
      prod6.setTitulo("Vestido florido");
      prod6.setPreco(new Double(750));
      prod6.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod6);
      
      Produto prod7 = new Produto();   
      
      prod7.setSrcImagem("img/home/featured-product/product-04.jpg");
      prod7.setTitulo("Bolsa fit");
      prod7.setPreco(new Double(350));
      prod7.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod7);
      
      Produto prod8 = new Produto();   
      
      prod8.setSrcImagem("img/home/featured-product/product-05.jpg");
      prod8.setTitulo("Boné Style");
      prod8.setPreco(new Double(90));
      prod8.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod8);
      
      Produto prod9 = new Produto();   
      
      prod9.setSrcImagem("img/home/featured-product/product-06.jpg");
      prod9.setTitulo("Tênis vermelho");
      prod9.setPreco(new Double(500));
      prod9.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod9);
      
      Produto prod10 = new Produto();   
      
      prod10.setSrcImagem("img/home/featured-product/product-07.jpg");
      prod10.setTitulo("Chápeu blues");
      prod10.setPreco(new Double(105));
      prod10.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod10);
      
      Produto prod11 = new Produto();   
      
      prod11.setSrcImagem("img/home/featured-product/product-09.jpg");
      prod11.setTitulo("Vestido África");
      prod11.setPreco(new Double(1500));
      prod11.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      dao.insert(prod11);
      
      entityManager.getTransaction().commit();
  }
  
}
