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
import br.com.kitchiqui.modelo.EnumClasseProduto;
import br.com.kitchiqui.modelo.EnumEspecie;
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

      Produto prod = new Produto();    
      ProdutoDAO dao = new ProdutoDAO(entityManager);
      ImagemGrandeProdutoDAO imgDAO = new ImagemGrandeProdutoDAO(entityManager);
      ImagemPequenoProdutoDAO imgPeqDAO = new ImagemPequenoProdutoDAO(entityManager);
      
      prod.setSrcImagem("img/home/banner-slider/shoe1.png");
      prod.setSrcImagemCarrinho("img/products/cart-image1.jpg");
      prod.setTitulo("KIT ORTODÔNTICO");
      prod.setPreco(450D);
      prod.setSubTitulo("Promoção do mês");
      prod.setDescritivo("Sucesso a cada sorriso");
      prod.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      prod.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod.setDisponivel(true);
      
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
      
      prod.setTituloDescritivo("Kit Ortodôntico carinho especial com sua estética");
      prod.setAnotacaoPrincipalDescritivo("A importância da saúde bucal é fundamental "
      		+ "com a boa higiene do aparelho ortodôntico, "
      		+ "nada mais prático que um Kit especialmente montado para você. Oferecemos essa exclusividade "
      		+ "com o foco em dar maior praticidade no seu dia-a-dia. Selecionamos os itens fundamentais ao "
      		+ "seu pleno tratamento ortodôntico.");
      
      prod.setAnotacaoDetalhe("<p>O que vimos de importante a você:</p>"
    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ortodôntica;</li>"
	              + "<li>Passa fio;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Enxaguante bucal;</li>"
              + "</ul>");
      
      prod.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
              + "<li>Escova ortodôntica: Pegar medida Curaprox;</li>"
              + "<li>Passa fio: Pegar medidas passa fio;</li>"
              + "<li>Fio dental: Tamanho em metros;</li>"
              + "<li>Creme dental: Especificar o tamanho;</li>"
              + "<li>Escova interdental: Característica;</li>"
              + "<li>Enxaguante bucal: Especificar tamanho em ML;</li>"
          + "</ul>");
      
      prod.setAnotacaoComposicao("A higienização bucal é um processo composto de várias etapas, principalmente nesse momento específico "
      		+ "de tratamento ortodôntico, nos quais citamos:"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Remoção do biofilme dental com auxílio do fio;</li>"
              + "<li>Passa fio, fundamental nos pequenos espaços dispostos pelos brackets;</li>"
              + "<li>Creme, mais indicado para a situação;</li>"
              + "<li>Escova anatomicamente apropriada a melhor higienização;</li>"
              + "<li>Escova interdental, focando na finalização perfeita em sua função;</li>"
              + "<li>Para remover bactérias e resíduos nada mais prático que o nosso enxaguante bucal;</li>"
          + "</ul>");
      
      
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
      prod2.setSrcImagemCarrinho("img/products/cart-image2.jpg");
      prod2.setTitulo("KIT Pós-cirurgico");
      prod2.setPreco(650D);
      prod2.setSubTitulo("Delicadeza com carinho");
      prod2.setDescritivo("Para cuidar de você");
      prod2.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      prod2.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod2.setDisponivel(true);
      
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
      
      prod2.setTituloDescritivo("Kit Pós-cirurgico - A atenção que você precisa.");
      prod2.setAnotacaoPrincipalDescritivo("Sabendo do cuidado que você precisa nesse momento tão sensível à sua "
      		+ "saúde, pensamos exatamente em tudo que irá lhe servir com praticidade, economicidade e higiene. "
      		+ "Não perca tempo e adquira já o seu kit focando na melhor recuperação e reabilitação funcional. "
      		+ "Pensamos carinhosamente em você.");
      
      prod2.setAnotacaoDetalhe("<p>Do que dispomos:</p>"
    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova extra-macia;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Enxaguante bucal solução com clorexidina;</li>"
              + "</ul>");
      
      prod2.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
              + "<li>Escova extra-macia: Pegar medida Curaprox;</li>"
              + "<li>Fio dental: Tamanho em metros;</li>"
              + "<li>Creme dental: Especificar o tamanho;</li>"
              + "<li>Enxaguante bucal: Especificar tamanho em ML;</li>"
          + "</ul>");
      
      prod2.setAnotacaoComposicao("A recuperação pós-cirurgica é fundamental quando associada a boa higienização, do que compomos descreve-se:"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Escova extra-macia, possui a função de não agredir a região tratada;</li>"
              + "<li>Fio dental, remover biofilme dental com eficiência;</li>"
              + "<li>Creme dental, evitando a ploriferação de placa bacteriana;</li>"
              + "<li>Enxaguante bucal, solução com clorexidina extremamente útil na remoção de bactérias inibindo sua proliferação;</li>"
          + "</ul>");
      
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
      prod3.setSrcImagemCarrinho("img/products/cart-image3.jpg");
      prod3.setTitulo("Kit Periodontal");
      prod3.setPreco(500D);
      prod3.setSubTitulo("Tratamento e Controle");
      prod3.setDescritivo("Sua gengiva mais saudável");
      prod3.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      prod3.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod3.setDisponivel(true);
      
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
      
      prod3.setTituloDescritivo("Kit Periodontal - Pensamos na sua gengiva");
      prod3.setAnotacaoPrincipalDescritivo("Sabemos que nos ater somente aos dentes não é o suficiente. "
    		+ "É comum a existência de danos irreversíveis à estrutura óssea, ocasionando percas dentárias, "
      		+ "com essa necessidade montamos o Kit essencial com total carinho à sua gengiva. "
      		+ "");
      
      prod3.setAnotacaoDetalhe("<p>Veja nossa atenção a você:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova periodontal;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Fita dental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Enxaguante bucal solução com clorexidina;</li>"
	          + "</ul>");
	  
	  prod3.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
	          + "<li>Escova periodontal: Definir característica técnica;</li>"
	          + "<li>Escova interdental: Definir característica técnica;</li>"
	          + "<li>Fita dental: Tamanho em metros;</li>"
	          + "<li>Creme dental: Especificar o tamanho;</li>"
	          + "<li>Enxaguante bucal: Especificar tamanho em ML;</li>"
	      + "</ul>");
	  
	  prod3.setAnotacaoComposicao("Dos cuidados que pensamos à sua gengiva:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Escova periodontal, cerdas macias limpam melhor a gengiva sem causar danos;</li>"
	          + "<li>Escova interdental, para os casos onde houve perda óssea, facilitando assim a higienização;</li>"
	          + "<li>Fita dental, remover biofilme dental considerando os espaços em excesso;</li>"
	          + "<li>Creme dental, evitando a ploriferação de placa bacteriana;</li>"
	          + "<li>Enxaguante bucal, solução com clorexidina extremamente útil na remoção de bactérias inibindo sua proliferação;</li>"
	      + "</ul>");
      
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
      prod4.setSrcImagemCarrinho("img/products/cart-image1.jpg");
      prod4.setTitulo("Kit Standart - Essencial");
      prod4.setPreco(50D);
      prod4.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod4.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod4.setClasse(EnumClasseProduto.KIT_STANDART.getClasse());
      prod4.setDisponivel(true);
      
   // Tratando secao de detalhe
      ImagemGrandeProduto imgG13 = new ImagemGrandeProduto();
      imgG13.setSrcImagem("img/products/signle-product/product-slide-big-P01.jpg");
      imgG13.setProduto(prod4);
      
      ImagemGrandeProduto imgG14 = new ImagemGrandeProduto();
      imgG14.setSrcImagem("img/products/signle-product/product-slide-big-P02.jpg");
      imgG14.setProduto(prod4);
      
      ImagemGrandeProduto imgG15 = new ImagemGrandeProduto();
      imgG15.setSrcImagem("img/products/signle-product/product-slide-big-P03.jpg");
      imgG15.setProduto(prod4);
      
      ImagemGrandeProduto imgG16 = new ImagemGrandeProduto();
      imgG16.setSrcImagem("img/products/signle-product/product-slide-big-P04.jpg");
      imgG16.setProduto(prod4);
      
      ImagemPequenoProduto imgP13 = new ImagemPequenoProduto();
      imgP13.setSrcImagem("img/products/signle-product/product-slide-small-P01.jpg");
      imgP13.setProduto(prod4);
      
      ImagemPequenoProduto imgP14 = new ImagemPequenoProduto();
      imgP14.setSrcImagem("img/products/signle-product/product-slide-small-P02.jpg");
      imgP14.setProduto(prod4);
      
      ImagemPequenoProduto imgP15 = new ImagemPequenoProduto();
      imgP15.setSrcImagem("img/products/signle-product/product-slide-small-P03.jpg");
      imgP15.setProduto(prod4);
      
      ImagemPequenoProduto imgP16 = new ImagemPequenoProduto();
      imgP16.setSrcImagem("img/products/signle-product/product-slide-small-P04.jpg");
      imgP16.setProduto(prod4);
      
      prod4.setTituloDescritivo("Kit Standart - Fundamental a qualquer momento");
      prod4.setAnotacaoPrincipalDescritivo("Todos possui a necessidade de higienização diária, com Kit Standart montamos o que você precisa. "
    		+ "Com elementos fundamentais a sua saúde bucal, pensamos na melhor composição a sua felicidade, "
      		+ "faça uso dessa facilidade e praticidade nos momentos mais importantes a você. "
      		+ "");
      
      prod4.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova macia;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Creme dental;</li>"
	          + "</ul>");
	  
	  prod4.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
	          + "<li>Escova macia: Definir característica técnica;</li>"
	          + "<li>Fio dental: Tamanho em metros;</li>"
	          + "<li>Creme dental: Especificar o tamanho;</li>"
	      + "</ul>");
	  
	  prod4.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Escova macia, cerdas apropriadas para não agredir o esmalte dentário;</li>"
	          + "<li>Fio dental, remover biofilme dental;</li>"
	          + "<li>Creme dental, auxilia na remoção de placas;</li>"
	      + "</ul>");
      
      dao.insert(prod4);
      imgDAO.insert(imgG13);
      imgDAO.insert(imgG14);
      imgDAO.insert(imgG15);
      imgDAO.insert(imgG16);
      imgPeqDAO.insert(imgP13);
      imgPeqDAO.insert(imgP14);
      imgPeqDAO.insert(imgP15);
      imgPeqDAO.insert(imgP16);
      
      Produto prod5 = new Produto();   
      
      prod5.setSrcImagem("img/home/featured-product/product-02.jpg");
      prod5.setSrcImagemCarrinho("img/products/cart-image3.jpg");
      prod5.setTitulo("Kit Infantil - Escolar");
      prod5.setPreco(120D);
      prod5.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod5.setEspecie(EnumEspecie.PRODUTO_INFANTIL.getEspecie());
      prod4.setClasse(EnumClasseProduto.KIT_ESCOLAR.getClasse());
      prod5.setDisponivel(true);
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG17 = new ImagemGrandeProduto();
      imgG17.setSrcImagem("img/products/signle-product/product-slide-big-P01.jpg");
      imgG17.setProduto(prod5);
      
      ImagemGrandeProduto imgG18 = new ImagemGrandeProduto();
      imgG18.setSrcImagem("img/products/signle-product/product-slide-big-P02.jpg");
      imgG18.setProduto(prod5);
      
      ImagemGrandeProduto imgG19 = new ImagemGrandeProduto();
      imgG19.setSrcImagem("img/products/signle-product/product-slide-big-P03.jpg");
      imgG19.setProduto(prod5);
      
      ImagemGrandeProduto imgG20 = new ImagemGrandeProduto();
      imgG20.setSrcImagem("img/products/signle-product/product-slide-big-P04.jpg");
      imgG20.setProduto(prod5);
      
      ImagemPequenoProduto imgP17 = new ImagemPequenoProduto();
      imgP17.setSrcImagem("img/products/signle-product/product-slide-small-P01.jpg");
      imgP17.setProduto(prod5);
      
      ImagemPequenoProduto imgP18 = new ImagemPequenoProduto();
      imgP18.setSrcImagem("img/products/signle-product/product-slide-small-P02.jpg");
      imgP18.setProduto(prod5);
      
      ImagemPequenoProduto imgP19 = new ImagemPequenoProduto();
      imgP19.setSrcImagem("img/products/signle-product/product-slide-small-P03.jpg");
      imgP19.setProduto(prod5);
      
      ImagemPequenoProduto imgP20 = new ImagemPequenoProduto();
      imgP20.setSrcImagem("img/products/signle-product/product-slide-small-P04.jpg");
      imgP20.setProduto(prod5);
      
      prod5.setTituloDescritivo("Kit Infantil - Sucesso nas escolas");
      prod5.setAnotacaoPrincipalDescritivo("Educar as crianças a fazer a higienização bucal até mesmo fora de casa é um desafio. "
      		+ "Com nosso produto essa missão torna-se elementar, contendo todo o carinho para esse período em que as "
      		+ "crianças estão longe da rotina de casa. Dispomos todos os itens que facilitam esse momento. "
      		+ "Trazendo também independência a criança quanto a cuidar da saúde bucal"
      		+ "");
      
      prod5.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova macia;</li>"
	              + "<li>Pasta de dente infantil;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Toalinha;</li>"
	          + "</ul>");
	  
	  prod5.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
	          + "<li>Escova macia: Definir característica técnica;</li>"
	          + "<li>Fio dental: Tamanho em metros;</li>"
	          + "<li>Creme dental: Especificar o tamanho;</li>"
	      + "</ul>");
	  
	  prod5.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Estojo personalizado, formato anatômico de fácil utilização, com cores alegres;</li>"
	          + "<li>Escova macia com formato adequado para a boquinha das crianças;</li>"
	          + "<li>Pasta de dente, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental, evitando a formação de placa bacteriana;</li>"
	          + "<li>Toalha, com foco em facilitar a higienizaçao;</li>"
	      + "</ul>");
      
      
      dao.insert(prod5);
      imgDAO.insert(imgG17);
      imgDAO.insert(imgG18);
      imgDAO.insert(imgG19);
      imgDAO.insert(imgG20);
      imgPeqDAO.insert(imgP17);
      imgPeqDAO.insert(imgP18);
      imgPeqDAO.insert(imgP19);
      imgPeqDAO.insert(imgP20);
      
      Produto prod6 = new Produto();   
      
      prod6.setSrcImagem("img/home/featured-product/product-03.jpg");
      prod6.setSrcImagemCarrinho("img/products/cart-image1.jpg");
      prod6.setTitulo("Kit Ortodôntico - Feminino");
      prod6.setPreco(180D);
      prod6.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod6.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
      prod6.setClasse(EnumClasseProduto.KIT_ORTODONTICO.getClasse());
      prod6.setDisponivel(true);
      
   // Tratando secao de detalhe
      ImagemGrandeProduto imgG21 = new ImagemGrandeProduto();
      imgG21.setSrcImagem("img/products/signle-product/product-slide-big-P01.jpg");
      imgG21.setProduto(prod6);
      
      ImagemGrandeProduto imgG22 = new ImagemGrandeProduto();
      imgG22.setSrcImagem("img/products/signle-product/product-slide-big-P02.jpg");
      imgG22.setProduto(prod6);
      
      ImagemGrandeProduto imgG23 = new ImagemGrandeProduto();
      imgG23.setSrcImagem("img/products/signle-product/product-slide-big-P03.jpg");
      imgG23.setProduto(prod6);
      
      ImagemGrandeProduto imgG24 = new ImagemGrandeProduto();
      imgG24.setSrcImagem("img/products/signle-product/product-slide-big-P04.jpg");
      imgG24.setProduto(prod6);
      
      ImagemPequenoProduto imgP21 = new ImagemPequenoProduto();
      imgP21.setSrcImagem("img/products/signle-product/product-slide-small-P01.jpg");
      imgP21.setProduto(prod6);
      
      ImagemPequenoProduto imgP22 = new ImagemPequenoProduto();
      imgP22.setSrcImagem("img/products/signle-product/product-slide-small-P02.jpg");
      imgP22.setProduto(prod6);
      
      ImagemPequenoProduto imgP23 = new ImagemPequenoProduto();
      imgP23.setSrcImagem("img/products/signle-product/product-slide-small-P03.jpg");
      imgP23.setProduto(prod6);
      
      ImagemPequenoProduto imgP24 = new ImagemPequenoProduto();
      imgP24.setSrcImagem("img/products/signle-product/product-slide-small-P04.jpg");
      imgP24.setProduto(prod6);
      
      prod6.setTituloDescritivo("Kit Ortodôntico - A delicadeza da mulher");
      prod6.setAnotacaoPrincipalDescritivo("O tratamento com aparelhos ortodôntico tende a criar alguns obstáculos para a higiene bucal.  "
      		+ "O Kit Ortodôntico Feminino possui todos os itens necessários para uma boa higiene bucal considerando a essência da mulher."
      		+ "");
      
      prod6.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ortodôntica;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Passa fio;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Enxaguante bucal;</li>"
	          + "</ul>");
	  
	  prod6.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
	          + "<li>Escova macia: Definir característica técnica;</li>"
	          + "<li>Fio dental: Tamanho em metros;</li>"
	          + "<li>Creme dental: Especificar o tamanho;</li>"
	      + "</ul>");
	  
	  prod6.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Escova ortodôntica,  possui um formato adequado para o formato do aparelho, assim higienizando melhor todo o conjunto;</li>"
	          + "<li>Escova interdental, devido ao seu formato cônico e cilíndrico se encaixa entre os dentes, finalizando a higienização;</li>"
	          + "<li>Passa fio, essencial para facilitar o uso do fio dental;</li>"
	          + "<li>Pasta de dente, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental localizado entre os dentes;</li>"
	          + "<li>Enxaguante bucal, remove bactérias;</li>"
	      + "</ul>");
      
      dao.insert(prod6);
      imgDAO.insert(imgG21);
      imgDAO.insert(imgG22);
      imgDAO.insert(imgG23);
      imgDAO.insert(imgG24);
      imgPeqDAO.insert(imgP21);
      imgPeqDAO.insert(imgP22);
      imgPeqDAO.insert(imgP23);
      imgPeqDAO.insert(imgP24);
      
      Produto prod7 = new Produto();   
      
      prod7.setSrcImagem("img/home/featured-product/product-04.jpg");
      prod7.setSrcImagemCarrinho("img/products/cart-image2.jpg");
      prod7.setTitulo("Kit Periodontal - Feminino");
      prod7.setPreco(180D);
      prod7.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod7.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
      prod7.setDisponivel(true);
      prod7.setClasse(EnumClasseProduto.KIT_PERIODONTAL.getClasse());
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG25 = new ImagemGrandeProduto();
      imgG25.setSrcImagem("img/products/signle-product/product-slide-big-P01.jpg");
      imgG25.setProduto(prod7);
      
      ImagemGrandeProduto imgG26 = new ImagemGrandeProduto();
      imgG26.setSrcImagem("img/products/signle-product/product-slide-big-P02.jpg");
      imgG26.setProduto(prod7);
      
      ImagemGrandeProduto imgG27 = new ImagemGrandeProduto();
      imgG27.setSrcImagem("img/products/signle-product/product-slide-big-P03.jpg");
      imgG27.setProduto(prod7);
      
      ImagemGrandeProduto imgG28 = new ImagemGrandeProduto();
      imgG28.setSrcImagem("img/products/signle-product/product-slide-big-P04.jpg");
      imgG28.setProduto(prod7);
      
      ImagemPequenoProduto imgP25 = new ImagemPequenoProduto();
      imgP25.setSrcImagem("img/products/signle-product/product-slide-small-P01.jpg");
      imgP25.setProduto(prod7);
      
      ImagemPequenoProduto imgP26 = new ImagemPequenoProduto();
      imgP26.setSrcImagem("img/products/signle-product/product-slide-small-P02.jpg");
      imgP26.setProduto(prod7);
      
      ImagemPequenoProduto imgP27 = new ImagemPequenoProduto();
      imgP27.setSrcImagem("img/products/signle-product/product-slide-small-P03.jpg");
      imgP27.setProduto(prod7);
      
      ImagemPequenoProduto imgP28 = new ImagemPequenoProduto();
      imgP28.setSrcImagem("img/products/signle-product/product-slide-small-P04.jpg");
      imgP28.setProduto(prod7);
      
      prod7.setTituloDescritivo("Kit Periodontal - Força feminina");
      prod7.setAnotacaoPrincipalDescritivo("A doença periodontal acomete gengiva e periodonto (estrutura de suporte aos dentes) "
      		+ "o principal fator de sucesso e controle do tratamento é a perfeita higienizaçao, "
      		+ "para isso existem itens que facilitam a higiene. Para sua comodidade montamos o "
      		+ "kit perfeito para o seu tratamento não deixando de pensar nos detalhes femininos, é claro!"
      		+ "");
      
      prod7.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova macia;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Fita dental;</li>"
	              + "<li>Enxaguante bucal com clorexidina;</li>"
	          + "</ul>");
	  
	  prod7.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
	          + "<li>Escova macia: Definir característica técnica;</li>"
	          + "<li>Fio dental: Tamanho em metros;</li>"
	          + "<li>Creme dental: Especificar o tamanho;</li>"
	      + "</ul>");
	  
	  prod7.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Lindo estojo personalizado, que facilita a locomoção dos itens, mantendo-os devidamente organizado e livre de exposições externas;</li>"
	          + "<li>Escova macia e anatômica com cerdas macias que não agridem a gengiva, evitando traumas;</li>"
	          + "<li>Pasta de dente, com o princípio de remoção do biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental, evitando assim a formação de placa bacteriana;</li>"
	          + "<li>Enxaguante bucal remove bactérias causadoras da doença periodontal em ate 95%;</li>"
	      + "</ul>");
      
      dao.insert(prod7);
      imgDAO.insert(imgG25);
      imgDAO.insert(imgG26);
      imgDAO.insert(imgG27);
      imgDAO.insert(imgG28);
      imgPeqDAO.insert(imgP25);
      imgPeqDAO.insert(imgP26);
      imgPeqDAO.insert(imgP27);
      imgPeqDAO.insert(imgP28);

      
      Produto prod8 = new Produto();   
      
      prod8.setSrcImagem("img/home/featured-product/product-05.jpg");
      prod8.setSrcImagemCarrinho("img/products/cart-image3.jpg");
      prod8.setTitulo("Kit Pós-Cirúrgico - Feminino");
      prod8.setPreco(250D);
      prod8.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod8.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
      prod8.setDisponivel(true);
      prod8.setClasse(EnumClasseProduto.KIT_POSCIRURGICO.getClasse());
      
   // Tratando secao de detalhe
      ImagemGrandeProduto imgG29 = new ImagemGrandeProduto();
      imgG29.setSrcImagem("img/products/signle-product/product-slide-big-P01.jpg");
      imgG29.setProduto(prod8);
      
      ImagemGrandeProduto imgG30 = new ImagemGrandeProduto();
      imgG30.setSrcImagem("img/products/signle-product/product-slide-big-P02.jpg");
      imgG30.setProduto(prod8);
      
      ImagemGrandeProduto imgG31 = new ImagemGrandeProduto();
      imgG31.setSrcImagem("img/products/signle-product/product-slide-big-P03.jpg");
      imgG31.setProduto(prod8);
      
      ImagemGrandeProduto imgG32 = new ImagemGrandeProduto();
      imgG32.setSrcImagem("img/products/signle-product/product-slide-big-P04.jpg");
      imgG32.setProduto(prod8);
      
      ImagemPequenoProduto imgP29 = new ImagemPequenoProduto();
      imgP29.setSrcImagem("img/products/signle-product/product-slide-small-P01.jpg");
      imgP29.setProduto(prod8);
      
      ImagemPequenoProduto imgP30 = new ImagemPequenoProduto();
      imgP30.setSrcImagem("img/products/signle-product/product-slide-small-P02.jpg");
      imgP30.setProduto(prod8);
      
      ImagemPequenoProduto imgP31 = new ImagemPequenoProduto();
      imgP31.setSrcImagem("img/products/signle-product/product-slide-small-P03.jpg");
      imgP31.setProduto(prod8);
      
      ImagemPequenoProduto imgP32 = new ImagemPequenoProduto();
      imgP32.setSrcImagem("img/products/signle-product/product-slide-small-P04.jpg");
      imgP32.setProduto(prod8);
      
      prod8.setTituloDescritivo("Kit Pós-Cirúrgico - Toque feminino");
      prod8.setAnotacaoPrincipalDescritivo("Os cuidados no pós-cirúrgico são essenciais para o sucesso de um tratamento. "
      		+ "Entre eles destacamos a higienização bucal. "
      		+ "Pensando nisso montamos um kit que se adéqua a esse momento delicado da sua saúde. Lembrando do lado feminino."
      		+ "");
      
      prod8.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova macia;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Enxaguante bucal com clorexidina;</li>"
	          + "</ul>");
	  
	  prod8.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: XX cm (Largura), XX cm (Altura), XX cm (Comprimento);</li>"
	          + "<li>Escova macia: Definir característica técnica;</li>"
	          + "<li>Fio dental: Tamanho em metros;</li>"
	          + "<li>Creme dental: Especificar o tamanho;</li>"
	      + "</ul>");
	  
	  prod8.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Lindo estojo personalizado, com detalhes que facilitam o uso, tornando fácil transportar o kit no dia a dia;</li>"
	          + "<li>Escova com cerdas macias que não agride a área operada;</li>"
	          + "<li>Creme dental, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental localizado entre os dentes;</li>"
	          + "<li>Enxaguante bucal, diminuindo a proliferação bacteriana na área operada evitando infecções;</li>"
	      + "</ul>");
      
      dao.insert(prod8);
      imgDAO.insert(imgG29);
      imgDAO.insert(imgG30);
      imgDAO.insert(imgG31);
      imgDAO.insert(imgG32);
      imgPeqDAO.insert(imgP29);
      imgPeqDAO.insert(imgP30);
      imgPeqDAO.insert(imgP31);
      imgPeqDAO.insert(imgP32);
//      
//      Produto prod9 = new Produto();   
//      
//      prod9.setSrcImagem("img/home/featured-product/product-06.jpg");
//      prod9.setSrcImagemCarrinho("img/products/cart-image1.jpg");
//      prod9.setTitulo("Tênis vermelho - KIT MONTADO");
//      prod9.setPreco(new Double(500));
//      prod9.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
//      prod9.setEspecie(EnumEspecie.PRODUTO_INFANTIL.getEspecie());
//      prod9.setDisponivel(true);
//      prod9.setClasse(EnumClasseProduto.KIT_MONTADO.getClasse());
//      dao.insert(prod9);
//      
//      Produto prod10 = new Produto();   
//      
//      prod10.setSrcImagem("img/home/featured-product/product-07.jpg");
//      prod10.setSrcImagemCarrinho("img/products/cart-image2.jpg");
//      prod10.setTitulo("Chápeu blues - KIT MONTADO");
//      prod10.setPreco(new Double(105));
//      prod10.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
//      prod10.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
//      prod10.setDisponivel(true);
//      prod10.setClasse(EnumClasseProduto.KIT_MONTADO.getClasse());
//      dao.insert(prod10);
//      
//      Produto prod11 = new Produto();   
//      
//      prod11.setSrcImagem("img/home/featured-product/product-09.jpg");
//      prod11.setSrcImagemCarrinho("img/products/cart-image3.jpg");
//      prod11.setTitulo("Vestido África - ESTOJO");
//      prod11.setPreco(new Double(1500));
//      prod11.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
//      prod11.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
//      prod11.setDisponivel(true);
//      prod11.setClasse(EnumClasseProduto.ESTOJO.getClasse());
//      dao.insert(prod11);
      
      entityManager.getTransaction().commit();
  }
  
}
