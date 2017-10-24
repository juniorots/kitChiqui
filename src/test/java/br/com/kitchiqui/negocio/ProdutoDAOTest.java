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

import br.com.kitchiqui.base.ImagemGrandeProdutoDAO;
import br.com.kitchiqui.base.ImagemPequenoProdutoDAO;
import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.EnumTipoProduto;
import br.com.kitchiqui.modelo.ImagemGrandeProduto;
import br.com.kitchiqui.modelo.ImagemPequenoProduto;
import br.com.kitchiqui.modelo.Produto;

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
//
      Produto prod = new Produto();    
      ProdutoDAO dao = new ProdutoDAO(entityManager);
      ImagemGrandeProdutoDAO imgDAO = new ImagemGrandeProdutoDAO(entityManager);
      ImagemPequenoProdutoDAO imgPeqDAO = new ImagemPequenoProdutoDAO(entityManager);
//      
      prod.setSrcImagem("img/home/banner-slider/shoe1.png");
      prod.setTitulo("TÊNIS AMARELO");
      prod.setSubTitulo("Promoção do mês");
      prod.setDescritivo("Sensação do verão");
      prod.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG1 = new ImagemGrandeProduto();
      imgG1.setSrcImagem("img/products/signle-product/product-slide-big-01.jpg");
      imgG1.setProduto(prod);
      
      ImagemGrandeProduto imgG2 = new ImagemGrandeProduto();
      imgG2.setSrcImagem("img/products/signle-product/product-slide-big-02.jpg");
      imgG2.setProduto(prod);
      
      ImagemGrandeProduto imgG3 = new ImagemGrandeProduto();
      imgG3.setSrcImagem("img/products/signle-product/product-slide-big-03.jpg");
      imgG3.setProduto(prod);
      
      ImagemGrandeProduto imgG4 = new ImagemGrandeProduto();
      imgG4.setSrcImagem("img/products/signle-product/product-slide-big-04.jpg");
      imgG4.setProduto(prod);
      
      ImagemPequenoProduto imgP1 = new ImagemPequenoProduto();
      imgP1.setSrcImagem("img/products/signle-product/product-slide-small-01.jpg");
      imgP1.setProduto(prod);
      
      ImagemPequenoProduto imgP2 = new ImagemPequenoProduto();
      imgP2.setSrcImagem("img/products/signle-product/product-slide-small-02.jpg");
      imgP2.setProduto(prod);
      
      ImagemPequenoProduto imgP3 = new ImagemPequenoProduto();
      imgP3.setSrcImagem("img/products/signle-product/product-slide-small-03.jpg");
      imgP3.setProduto(prod);
      
      ImagemPequenoProduto imgP4 = new ImagemPequenoProduto();
      imgP4.setSrcImagem("img/products/signle-product/product-slide-small-04.jpg");
      imgP4.setProduto(prod);
      
      prod.setTituloDescritivo("Tênis AMARELO momento de evoluir a cada passo");
      prod.setPreco(450D);
      prod.setAnotacaoPrincipalDescritivo("A importância do esporte no mundo levou à criação "
      		+ "das ciências do esporte, onde a Psicologia Aplicada tem evoluído. "
      		+ "Na estruturação dos programas de atendimento psicológico dos desportistas, apresentada pelo autor, além do psicólogo, "
      		+ "diversos profissionais que trabalham nas comissões técnicas, têm opinado. "
      		+ "Entretanto, o cliente (atleta) que consome o produto (Psicologia do Esporte) "
      		+ "nunca opinou sobre este, na América do Sul.");
      
      prod.setAnotacaoDetalhe("<p>A análise dos resultados proporcionou as seguintes conclusões: </p> "
				              + "          <ul class=\"list-unstyled\"> "
				              + "            <li>Cor amarelo;</li> "
				              + "            <li>Tamanho versátil;</li> "
				              + "            <li>Serve para todas as idades;</li> "
				              + "            <li>Lavavél em máquina;</li> "
				              + "          </ul>");
      
      prod.setAnotacaoTecnica("O autor sugere que sejam divulgados estes resultados aos psicólogos e aos "
      		+ "membros da comissão técnica dos clubes para o aperfeiçoamento dos futuros programas de Psicologia do Esporte.");
      
      prod.setAnotacaoComposicao("Composicao de exemplo do tênis, para efeito de preenchimento de campo e teste de sua limitacao "
      		+ "sua existência se justifica em ambiente de testes e homologação para a criação do projeto, assim esse texto deverá "
    		+ "ser desconsiderado em sua totalidade para realidade de execução real.");
      
      dao.insert(prod); 
      imgDAO.insert(imgG1);
      imgDAO.insert(imgG2);
      imgDAO.insert(imgG3);
      imgDAO.insert(imgG4);
      imgPeqDAO.insert(imgP1);
      imgPeqDAO.insert(imgP2);
      imgPeqDAO.insert(imgP3);
      imgPeqDAO.insert(imgP4);
      
      Produto prod2 = new Produto();   
      
      prod2.setSrcImagem("img/home/banner-slider/shoe2.png");
      prod2.setTitulo("TÊNIS BRANCO");
      prod2.setSubTitulo("Suavidade no estilo");
      prod2.setDescritivo("Conquista cada passo");
      prod2.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG5 = new ImagemGrandeProduto();
      imgG5.setSrcImagem("img/products/signle-product/product-slide-big-B01.jpg");
      imgG5.setProduto(prod2);
      
      ImagemGrandeProduto imgG6 = new ImagemGrandeProduto();
      imgG6.setSrcImagem("img/products/signle-product/product-slide-big-B02.jpg");
      imgG6.setProduto(prod2);
      
      ImagemGrandeProduto imgG7 = new ImagemGrandeProduto();
      imgG7.setSrcImagem("img/products/signle-product/product-slide-big-B03.jpg");
      imgG7.setProduto(prod2);
      
      ImagemGrandeProduto imgG8 = new ImagemGrandeProduto();
      imgG8.setSrcImagem("img/products/signle-product/product-slide-big-B04.jpg");
      imgG8.setProduto(prod2);
      
      ImagemPequenoProduto imgP5 = new ImagemPequenoProduto();
      imgP5.setSrcImagem("img/products/signle-product/product-slide-small-B01.jpg");
      imgP5.setProduto(prod2);
      
      ImagemPequenoProduto imgP6 = new ImagemPequenoProduto();
      imgP6.setSrcImagem("img/products/signle-product/product-slide-small-B02.jpg");
      imgP6.setProduto(prod2);
      
      ImagemPequenoProduto imgP7 = new ImagemPequenoProduto();
      imgP7.setSrcImagem("img/products/signle-product/product-slide-small-B03.jpg");
      imgP7.setProduto(prod2);
      
      ImagemPequenoProduto imgP8 = new ImagemPequenoProduto();
      imgP8.setSrcImagem("img/products/signle-product/product-slide-small-B04.jpg");
      imgP8.setProduto(prod2);
      
      prod2.setTituloDescritivo("Tênis BRANCO o seu estilo de pensar");
      prod2.setPreco(650D);
      prod2.setAnotacaoPrincipalDescritivo("A importância do esporte no mundo levou à criação "
      		+ "das ciências do esporte, onde a Psicologia Aplicada tem evoluído. "
      		+ "Na estruturação dos programas de atendimento psicológico dos desportistas, apresentada pelo autor, além do psicólogo, "
      		+ "diversos profissionais que trabalham nas comissões técnicas, têm opinado. "
      		+ "Entretanto, o cliente (atleta) que consome o produto (Psicologia do Esporte) "
      		+ "nunca opinou sobre este, na América do Sul.");
      
      prod2.setAnotacaoDetalhe("<p>A análise dos resultados proporcionou as seguintes conclusões: </p> "
				              + "          <ul class=\"list-unstyled\"> "
				              + "            <li>Cor branco;</li> "
				              + "            <li>Tamanho versátil;</li> "
				              + "            <li>Útil para todas as idades;</li> "
				              + "            <li>Lavavél em máquina;</li> "
				              + "          </ul>");
      
      prod2.setAnotacaoTecnica("O autor sugere que sejam divulgados estes resultados aos psicólogos e aos "
      		+ "membros da comissão técnica dos clubes para o aperfeiçoamento dos futuros programas de Psicologia do Esporte.");
      
      prod2.setAnotacaoComposicao("Composicao de exemplo do tênis, para efeito de preenchimento de campo e teste de sua limitacao "
      		+ "sua existência se justifica em ambiente de testes e homologação para a criação do projeto, assim esse texto deverá "
    		+ "ser desconsiderado em sua totalidade para realidade de execução real.");
      
      dao.insert(prod2);      
      imgDAO.insert(imgG5);
      imgDAO.insert(imgG6);
      imgDAO.insert(imgG7);
      imgDAO.insert(imgG8);
      imgPeqDAO.insert(imgP5);
      imgPeqDAO.insert(imgP6);
      imgPeqDAO.insert(imgP7);
      imgPeqDAO.insert(imgP8);
//      
      Produto prod3 = new Produto();   
      
      prod3.setSrcImagem("img/home/banner-slider/shoe3.png");
      prod3.setTitulo("TÊNIS PRETO");
      prod3.setSubTitulo("Estilo Blade");
      prod3.setDescritivo("Dominando um estilo");
      prod3.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      
   // Tratando secao de detalhe
      ImagemGrandeProduto imgG9 = new ImagemGrandeProduto();
      imgG9.setSrcImagem("img/products/signle-product/product-slide-big-P01.jpg");
      imgG9.setProduto(prod3);
      
      ImagemGrandeProduto imgG10 = new ImagemGrandeProduto();
      imgG10.setSrcImagem("img/products/signle-product/product-slide-big-P02.jpg");
      imgG10.setProduto(prod3);
      
      ImagemGrandeProduto imgG11 = new ImagemGrandeProduto();
      imgG11.setSrcImagem("img/products/signle-product/product-slide-big-P03.jpg");
      imgG11.setProduto(prod3);
      
      ImagemGrandeProduto imgG12 = new ImagemGrandeProduto();
      imgG12.setSrcImagem("img/products/signle-product/product-slide-big-P04.jpg");
      imgG12.setProduto(prod3);
      
      ImagemPequenoProduto imgP9 = new ImagemPequenoProduto();
      imgP9.setSrcImagem("img/products/signle-product/product-slide-small-P01.jpg");
      imgP9.setProduto(prod3);
      
      ImagemPequenoProduto imgP10 = new ImagemPequenoProduto();
      imgP10.setSrcImagem("img/products/signle-product/product-slide-small-P02.jpg");
      imgP10.setProduto(prod3);
      
      ImagemPequenoProduto imgP11 = new ImagemPequenoProduto();
      imgP11.setSrcImagem("img/products/signle-product/product-slide-small-P03.jpg");
      imgP11.setProduto(prod3);
      
      ImagemPequenoProduto imgP12 = new ImagemPequenoProduto();
      imgP12.setSrcImagem("img/products/signle-product/product-slide-small-P04.jpg");
      imgP12.setProduto(prod3);
      
      prod3.setTituloDescritivo("Tênis PRETO para qualquer solo!");
      prod3.setPreco(500D);
      prod3.setAnotacaoPrincipalDescritivo("A importância do esporte no mundo levou à criação "
      		+ "das ciências do esporte, onde a Psicologia Aplicada tem evoluído. "
      		+ "Na estruturação dos programas de atendimento psicológico dos desportistas, apresentada pelo autor, além do psicólogo, "
      		+ "diversos profissionais que trabalham nas comissões técnicas, têm opinado. "
      		+ "Entretanto, o cliente (atleta) que consome o produto (Psicologia do Esporte) "
      		+ "nunca opinou sobre este, na América do Sul.");
      
      prod3.setAnotacaoDetalhe("<p>A análise dos resultados proporcionou as seguintes conclusões: </p> "
				              + "          <ul class=\"list-unstyled\"> "
				              + "            <li>Cor Preto;</li> "
				              + "            <li>Tamanho versátil;</li> "
				              + "            <li>Útil para todas as idades;</li> "
				              + "            <li>Lavavél em máquina;</li> "
				              + "          </ul>");
      
      prod3.setAnotacaoTecnica("O autor sugere que sejam divulgados estes resultados aos psicólogos e aos "
      		+ "membros da comissão técnica dos clubes para o aperfeiçoamento dos futuros programas de Psicologia do Esporte.");
      
      prod3.setAnotacaoComposicao("Composicao de exemplo do tênis, para efeito de preenchimento de campo e teste de sua limitacao "
      		+ "sua existência se justifica em ambiente de testes e homologação para a criação do projeto, assim esse texto deverá "
    		+ "ser desconsiderado em sua totalidade para realidade de execução real.");
      
      dao.insert(prod3);
      imgDAO.insert(imgG9);
      imgDAO.insert(imgG10);
      imgDAO.insert(imgG11);
      imgDAO.insert(imgG12);
      imgPeqDAO.insert(imgP9);
      imgPeqDAO.insert(imgP10);
      imgPeqDAO.insert(imgP11);
      imgPeqDAO.insert(imgP12);
      
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
