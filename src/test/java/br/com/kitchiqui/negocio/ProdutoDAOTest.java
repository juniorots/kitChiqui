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

import br.com.kitchiqui.base.ImagemGrandeProdutoDAO;
import br.com.kitchiqui.base.ImagemPequenoProdutoDAO;
import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.controller.BaseController;
import br.com.kitchiqui.modelo.EnumClasseProduto;
import br.com.kitchiqui.modelo.EnumEspecie;
import br.com.kitchiqui.modelo.EnumTipoProduto;
import br.com.kitchiqui.modelo.ImagemGrandeProduto;
import br.com.kitchiqui.modelo.ImagemPequenoProduto;
import br.com.kitchiqui.modelo.Produto;
import br.com.kitchiqui.util.Util;
import lombok.Cleanup;

public class ProdutoDAOTest extends BaseController {
	
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
     * Util na verificacao de produto ja existente na base de dados
     * @return
     */
    public Produto contidoBase(Produto produto) {
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
                
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        if (!Util.isEmpty(dao.findByStringField("srcImagem", produto.getSrcImagem(), true, 0, 1))) {
        	return dao.findByStringField("srcImagem", produto.getSrcImagem(), true, 0, 1).get(0);
        } else 
        	return null;
    }
    
    /**
     * Atualizando produto
     */
    public void atualizarProduto(Produto produto) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        if (!Util.isEmpty(produto.getStrPreco())) {
        	produto.setPreco(Double.parseDouble(produto.getStrPreco().replaceAll("[R$]", "").replaceAll("[,]", ".")));
        }
        dao.update(produto);
        entityManager.getTransaction().commit();
	}
    
    /**
     * Inserindo produto na base
     */
    public void inserirProduto(Produto produto) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ProdutoDAO dao = new ProdutoDAO(entityManager);
        if (!Util.isEmpty(produto.getStrPreco())) {
        	produto.setPreco(Double.parseDouble(produto.getStrPreco().replaceAll("[R$]", "").replaceAll("[,]", ".")));
        }
        dao.insert(produto);
        entityManager.getTransaction().commit();
	}
    
    /**
     * Tratando conjunto de imagens anexas
     * @param imgG
     */
    public void inserirImagemGrande(ImagemGrandeProduto imgG) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ImagemGrandeProdutoDAO dao = new ImagemGrandeProdutoDAO(entityManager);
        dao.insert(imgG);
        entityManager.getTransaction().commit();
	}
    
    /**
     * Tratando conjunto de imagens anexas
     * @param imgP
     */
    public void inserirImagemPequeno(ImagemPequenoProduto imgP) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        ImagemPequenoProdutoDAO dao = new ImagemPequenoProdutoDAO(entityManager);
        dao.insert(imgP);
        entityManager.getTransaction().commit();
	}
    
  @Test
  public void mainTest() {
	  
	  @Cleanup
      final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

      @Cleanup
      final EntityManager entityManager = entityManagerFactory.createEntityManager();
      entityManager.getTransaction().begin();

      Produto prod = new Produto();    
//      ProdutoDAO dao = new ProdutoDAO(entityManager);
//      ImagemGrandeProdutoDAO imgDAO = new ImagemGrandeProdutoDAO(entityManager);
//      ImagemPequenoProdutoDAO imgPeqDAO = new ImagemPequenoProdutoDAO(entityManager);
      
      prod.setSrcImagem("img/home/banner-slider/kitVitrineOrtoMasculino.png");
      prod.setSrcImagemCarrinho("img/products/kitVitrineOrtoMasculino64x74.png");
      prod.setTitulo("Kit Ortodontico - Masculino");
      prod.setPreco(65D);
      prod.setSubTitulo("Promoção do mês");
      prod.setDescritivo("Essencial na Organização");
      prod.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      prod.setClasse(EnumClasseProduto.KIT_ORTODONTICO.getClasse());
      prod.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod.setQtdEstoque(20);
      prod.setDisponivel(true);
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG1 = new ImagemGrandeProduto();
      imgG1.setSrcImagem("img/products/signle-product/masculinoGrande.png");
      imgG1.setProduto(prod);
      
//      ImagemGrandeProduto imgG2 = new ImagemGrandeProduto();
//      imgG2.setSrcImagem("img/products/signle-product/escovaOrtoGrande.png");
//      imgG2.setProduto(prod);
      
//      ImagemGrandeProduto imgG3 = new ImagemGrandeProduto();
//      imgG3.setSrcImagem("img/products/signle-product/passaFioGrande.png");
//      imgG3.setProduto(prod);
//      
//      ImagemGrandeProduto imgG4 = new ImagemGrandeProduto();
//      imgG4.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG4.setProduto(prod);
//      
//      ImagemGrandeProduto imgG5 = new ImagemGrandeProduto();
//      imgG5.setSrcImagem("img/products/signle-product/escovaInterGrande.png");
//      imgG5.setProduto(prod);
//      
//      ImagemGrandeProduto imgG6 = new ImagemGrandeProduto();
//      imgG6.setSrcImagem("img/products/signle-product/enxaguaGrande.png");
//      imgG6.setProduto(prod);
//      
//      ImagemGrandeProduto imgG7 = new ImagemGrandeProduto();
//      imgG7.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG7.setProduto(prod);
//      
//      ImagemGrandeProduto imgG53 = new ImagemGrandeProduto();
//      imgG53.setSrcImagem("img/products/signle-product/ceraGrande.png");
//      imgG53.setProduto(prod);
//      
      ImagemPequenoProduto imgP1 = new ImagemPequenoProduto();
      imgP1.setSrcImagem("img/products/signle-product/masculinoPequeno.png");
      imgP1.setProduto(prod);
//      
//      ImagemPequenoProduto imgP2 = new ImagemPequenoProduto();
//      imgP2.setSrcImagem("img/products/signle-product/escovaOrtoPequena.png");
//      imgP2.setProduto(prod);
//      
//      ImagemPequenoProduto imgP3 = new ImagemPequenoProduto();
//      imgP3.setSrcImagem("img/products/signle-product/passaFioPequeno.png");
//      imgP3.setProduto(prod);
//      
//      ImagemPequenoProduto imgP4 = new ImagemPequenoProduto();
//      imgP4.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP4.setProduto(prod);
//
//      ImagemPequenoProduto imgP5 = new ImagemPequenoProduto();
//      imgP5.setSrcImagem("img/products/signle-product/escovaInterPequena.png");
//      imgP5.setProduto(prod);
//      
//      ImagemPequenoProduto imgP6 = new ImagemPequenoProduto();
//      imgP6.setSrcImagem("img/products/signle-product/enxaguaPequeno.png");
//      imgP6.setProduto(prod);
//      
//      ImagemPequenoProduto imgP7 = new ImagemPequenoProduto();
//      imgP7.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP7.setProduto(prod);
//      
//      ImagemPequenoProduto imgP53 = new ImagemPequenoProduto();
//      imgP53.setSrcImagem("img/products/signle-product/ceraPequena.png");
//      imgP53.setProduto(prod);
            
      prod.setTituloDescritivo("Kit Estojo de Organização modelo masculino");
      prod.setAnotacaoPrincipalDescritivo("Pensando na sua comodidade "
      		+ "nada mais prático que um Kit especialmente montado para você. Oferecemos essa exclusividade "
      		+ "com o foco em dar maior praticidade no seu dia-a-dia. Selecionamos os itens fundamentais ao "
      		+ "seu pleno tratamento ortodôntico.");
      
      prod.setAnotacaoDetalhe("<p>O que vimos de importante a você:</p>"
    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova extra-macia;</li>"
	              + "<li>Passa fio;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Cera ortodôntica;</li>"
	              + "<li>Enxaguante bucal;</li>"
              + "</ul>");
      
      prod.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores:"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Kit: 25 cm (Largura), 23 cm (Altura), 10 cm (Comprimento);</li>"
              + "<li>Escova ultra macia: Tipo Médio com sensibilidade à sua gengiva;</li>"
              + "<li>Passa fio;</li>"
              + "<li>Fio dental: 50 metros;</li>"
              + "<li>Creme dental: 70g;</li>"
              + "<li>Escova interdental: 3 a 7mm;</li>"
//              + "<li>Cera ortodôntica vigente do mercado</li>"
              + "<li>Enxaguante bucal: 250mL;</li>"
          + "</ul>");
      
      prod.setAnotacaoComposicao("Organização é fundamental, principalmente na correria do dia a dia. "
      		+ "focando no seu tratamento ortodôntico, podemos citar:"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Remoção do biofilme dental com auxílio do fio;</li>"
              + "<li>Passa fio, fundamental nos pequenos espaços dispostos pelos brackets;</li>"
              + "<li>Creme, mais indicado para a situação;</li>"
              + "<li>Escova anatomicamente apropriada a melhor higienização;</li>"
              + "<li>Escova interdental, focando na finalização perfeita em sua função;</li>"
              + "<li>Protegendo a mucosa bucal, nada mais útil que a cera ortodôntica;</li>"
              + "<li>Para remover bactérias e resíduos nada mais prático que o nosso enxaguante bucal;</li>"
          + "</ul>");
      
      Produto tmp = contidoBase(prod);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod);
    	  inserirImagemGrande(imgG1);
//    	  inserirImagemGrande(imgG2);
//    	  inserirImagemGrande(imgG3);
//    	  inserirImagemGrande(imgG4);
//    	  inserirImagemGrande(imgG5);
//    	  inserirImagemGrande(imgG6);
//    	  inserirImagemGrande(imgG7);
//    	  inserirImagemGrande(imgG53);
    	  inserirImagemPequeno(imgP1);
//    	  inserirImagemPequeno(imgP2);
//    	  inserirImagemPequeno(imgP3);
//    	  inserirImagemPequeno(imgP4);
//    	  inserirImagemPequeno(imgP5);
//    	  inserirImagemPequeno(imgP6);
//    	  inserirImagemPequeno(imgP7);
//    	  inserirImagemPequeno(imgP53);
      } else {
    	  prod.setId(tmp.getId());
    	  atualizarProduto(prod);
      }
      
      Produto prod2 = new Produto();   
      
      prod2.setSrcImagem("img/home/featured-product/kitMasculinoPosCirurgico.png");
      prod2.setSrcImagemCarrinho("img/products/kitMasculinoPosCirurgico64x74.png");
      prod2.setTitulo("KIT Pós-cirurgico");
      prod2.setPreco(55D);
      prod2.setSubTitulo("Delicadeza com carinho");
      prod2.setDescritivo("Para cuidar de você");
      prod2.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod2.setClasse(EnumClasseProduto.KIT_POSCIRURGICO.getClasse());
      prod2.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod2.setQtdEstoque(20);
      prod2.setDisponivel(true);
//      
//      // Tratando secao de detalhe
      ImagemGrandeProduto imgG8 = new ImagemGrandeProduto();
      imgG8.setSrcImagem("img/products/signle-product/masculinoGrande.png");
      imgG8.setProduto(prod2);
//      
//      ImagemGrandeProduto imgG9 = new ImagemGrandeProduto();
//      imgG9.setSrcImagem("img/products/signle-product/escovaMasculinaGrande.png");
//      imgG9.setProduto(prod2);
//      
//      ImagemGrandeProduto imgG10 = new ImagemGrandeProduto();
//      imgG10.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG10.setProduto(prod2);
//      
//      ImagemGrandeProduto imgG11 = new ImagemGrandeProduto();
//      imgG11.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG11.setProduto(prod2);
//      
//      ImagemGrandeProduto imgG12 = new ImagemGrandeProduto();
//      imgG12.setSrcImagem("img/products/signle-product/enxaguaGrande.png");
//      imgG12.setProduto(prod2);
//      
      ImagemPequenoProduto imgP8 = new ImagemPequenoProduto();
      imgP8.setSrcImagem("img/products/signle-product/masculinoPequeno.png");
      imgP8.setProduto(prod2);
//      
//      ImagemPequenoProduto imgP9 = new ImagemPequenoProduto();
//      imgP9.setSrcImagem("img/products/signle-product/escovaMasculinaPequena.png");
//      imgP9.setProduto(prod2);
//      
//      ImagemPequenoProduto imgP10 = new ImagemPequenoProduto();
//      imgP10.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP10.setProduto(prod2);
//      
//      ImagemPequenoProduto imgP11 = new ImagemPequenoProduto();
//      imgP11.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP11.setProduto(prod2);
//      
//      ImagemPequenoProduto imgP12 = new ImagemPequenoProduto();
//      imgP12.setSrcImagem("img/products/signle-product/enxaguaPequeno.png");
//      imgP12.setProduto(prod2);
//      
      prod2.setTituloDescritivo("Kit Pós-cirurgico - O cuidado que você precisa.");
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
	              + "<li>Enxaguante bucal;</li>"
              + "</ul>");
      
      prod2.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores: "
    		  + "<ul class=\"list-unstyled\">"
    		  + "<li>Kit: 25 cm (Largura), 23 cm (Altura), 10 cm (Comprimento);</li>"
              + "<li>Escova ultra macia;</li>"
              + "<li>Fio dental: 50 metros;</li>"
              + "<li>Creme dental: 70g;</li>"
              + "<li>Enxaguante bucal: 250mL;</li>"
          + "</ul>");
      
      prod2.setAnotacaoComposicao("A recuperação pós-cirurgica é fundamental quando associada a boa higienização, do que compomos descreve-se:"
    		  + "<ul class=\"list-unstyled\">"
              + "<li>Escova ultra macia, possui a função de não agredir a região tratada;</li>"
              + "<li>Fio dental, remover biofilme dental com eficiência;</li>"
              + "<li>Creme dental, evitando a ploriferação de placa bacteriana;</li>"
              + "<li>Enxaguante bucal, extremamente útil na remoção de bactérias inibindo sua proliferação;</li>"
          + "</ul>");
//      
      tmp = contidoBase(prod2);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod2);
    	  inserirImagemGrande(imgG8);
//    	  inserirImagemGrande(imgG9);
//    	  inserirImagemGrande(imgG10);
//    	  inserirImagemGrande(imgG11);
//    	  inserirImagemGrande(imgG12);
    	  inserirImagemPequeno(imgP8);
//    	  inserirImagemPequeno(imgP9);
//    	  inserirImagemPequeno(imgP10);
//    	  inserirImagemPequeno(imgP11);
//    	  inserirImagemPequeno(imgP12);
      } else {
    	  prod2.setId(tmp.getId());
    	  atualizarProduto(prod2);
      }
      
      Produto prod3 = new Produto();   
      
      prod3.setSrcImagem("img/home/featured-product/kitPeriodontalMasculino.png");
      prod3.setSrcImagemCarrinho("img/products/kitPeriodontalMasculino64x74.png");
      prod3.setTitulo("Kit Periodontal");
      prod3.setPreco(65D);
      prod3.setSubTitulo("Tratamento e Controle");
      prod3.setDescritivo("Sua gengiva mais saudável");
      prod3.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod3.setClasse(EnumClasseProduto.KIT_PERIODONTAL.getClasse());
      prod3.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod3.setQtdEstoque(20);
      prod3.setDisponivel(true);
//      
//   // Tratando secao de detalhe
      ImagemGrandeProduto imgG13 = new ImagemGrandeProduto();
      imgG13.setSrcImagem("img/products/signle-product/masculinoGrande.png");
      imgG13.setProduto(prod3);
//      
//      ImagemGrandeProduto imgG14 = new ImagemGrandeProduto();
//      imgG14.setSrcImagem("img/products/signle-product/escovaMasculinaGrande.png");
//      imgG14.setProduto(prod3);
//      
//      ImagemGrandeProduto imgG15 = new ImagemGrandeProduto();
//      imgG15.setSrcImagem("img/products/signle-product/escovaInterGrande.png");
//      imgG15.setProduto(prod3);
//      
//      ImagemGrandeProduto imgG16 = new ImagemGrandeProduto();
//      imgG16.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG16.setProduto(prod3);
//      
//      ImagemGrandeProduto imgG17 = new ImagemGrandeProduto();
//      imgG17.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG17.setProduto(prod3);
//      
//      ImagemGrandeProduto imgG18 = new ImagemGrandeProduto();
//      imgG18.setSrcImagem("img/products/signle-product/enxaguaGrande.png");
//      imgG18.setProduto(prod3);
//      
//      ImagemGrandeProduto imgG19 = new ImagemGrandeProduto();
//      imgG19.setSrcImagem("img/products/signle-product/interdentalGrande.png");
//      imgG19.setProduto(prod3);
//      
      ImagemPequenoProduto imgP13 = new ImagemPequenoProduto();
      imgP13.setSrcImagem("img/products/signle-product/masculinoPequeno.png");
      imgP13.setProduto(prod3);
//      
//      ImagemPequenoProduto imgP14 = new ImagemPequenoProduto();
//      imgP14.setSrcImagem("img/products/signle-product/escovaMasculinaPequena.png");
//      imgP14.setProduto(prod3);
//      
//      ImagemPequenoProduto imgP15 = new ImagemPequenoProduto();
//      imgP15.setSrcImagem("img/products/signle-product/escovaInterPequena.png");
//      imgP15.setProduto(prod3);
//      
//      ImagemPequenoProduto imgP16 = new ImagemPequenoProduto();
//      imgP16.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP16.setProduto(prod3);
//      
//      ImagemPequenoProduto imgP17 = new ImagemPequenoProduto();
//      imgP17.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP17.setProduto(prod3);
//      
//      ImagemPequenoProduto imgP18 = new ImagemPequenoProduto();
//      imgP18.setSrcImagem("img/products/signle-product/enxaguaPequeno.png");
//      imgP18.setProduto(prod3);
//      
//      ImagemPequenoProduto imgP19 = new ImagemPequenoProduto();
//      imgP19.setSrcImagem("img/products/signle-product/interdentalPequeno.png");
//      imgP19.setProduto(prod3);
//      
      prod3.setTituloDescritivo("Kit Periodontal - Pensamos na sua gengiva");
      prod3.setAnotacaoPrincipalDescritivo("Sabemos que nos ater somente aos dentes não é o suficiente. "
    		+ "É comum a existência de danos irreversíveis à estrutura óssea, ocasionando percas dentárias, "
      		+ "com essa necessidade montamos o Kit essencial com total carinho à sua gengiva. "
      		+ "");
//      
      prod3.setAnotacaoDetalhe("<p>Veja nossa atenção a você:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ultra macia;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Enxaguante bucal;</li>"
	          + "</ul>");
//	  
	  prod3.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores: "
			  + "<ul class=\"list-unstyled\">"
			  + "<li>Kit: 25 cm (Largura), 23 cm (Altura), 10 cm (Comprimento);</li>"
	          + "<li>Escova ultra macia;</li>"
	          + "<li>Escova interdental: 3 a 7mm;</li>"
	          + "<li>Fita dental: 50 metros;</li>"
	          + "<li>Creme dental: 70g;</li>"
	          + "<li>Enxaguante bucal: 250mL;</li>"
	      + "</ul>");
//	  
	  prod3.setAnotacaoComposicao("Dos cuidados que pensamos à sua gengiva:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Escova ultra macia, cerdas macias limpam melhor a gengiva sem causar danos;</li>"
	          + "<li>Escova interdental, para os casos onde houve perda óssea, facilitando assim a higienização;</li>"
	          + "<li>Fio dental, remover biofilme dental considerando os espaços em excesso;</li>"
	          + "<li>Creme dental, evitando a ploriferação de placa bacteriana;</li>"
	          + "<li>Enxaguante bucal, extremamente útil na remoção de bactérias inibindo sua proliferação;</li>"
	      + "</ul>");
//      
	  tmp = contidoBase(prod3);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod3);
    	  inserirImagemGrande(imgG13);
//    	  inserirImagemGrande(imgG14);
//    	  inserirImagemGrande(imgG15);
//    	  inserirImagemGrande(imgG16);
//    	  inserirImagemGrande(imgG17);
//    	  inserirImagemGrande(imgG18);
//    	  inserirImagemGrande(imgG19);
    	  inserirImagemPequeno(imgP13);
//    	  inserirImagemPequeno(imgP14);
//    	  inserirImagemPequeno(imgP15);
//    	  inserirImagemPequeno(imgP16);
//    	  inserirImagemPequeno(imgP17);
//    	  inserirImagemPequeno(imgP18);
//    	  inserirImagemPequeno(imgP19);
      } else {
    	  prod3.setId(tmp.getId());
    	  atualizarProduto(prod3);
      }
	  
      Produto prod4 = new Produto();   
      
      prod4.setSrcImagem("img/home/featured-product/kitStandart.png");
      prod4.setSrcImagemCarrinho("img/products/kitStandart64x74.png");
      prod4.setTitulo("Kit Standard - Essencial");
      prod4.setPreco(35D);
      prod4.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod4.setEspecie(EnumEspecie.PRODUTO_MASCULINO.getEspecie());
      prod4.setClasse(EnumClasseProduto.KIT_STANDART.getClasse());
      prod4.setQtdEstoque(20);
      prod4.setDisponivel(true);
      
   // Tratando secao de detalhe
      ImagemGrandeProduto imgG20 = new ImagemGrandeProduto();
      imgG20.setSrcImagem("img/products/signle-product/standardGrande.png");
      imgG20.setProduto(prod4);
      
//      ImagemGrandeProduto imgG21 = new ImagemGrandeProduto();
//      imgG21.setSrcImagem("img/products/signle-product/escovaMasculinaGrande.png");
//      imgG21.setProduto(prod4);
//      
//      ImagemGrandeProduto imgG22 = new ImagemGrandeProduto();
//      imgG22.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG22.setProduto(prod4);
//      
//      ImagemGrandeProduto imgG23 = new ImagemGrandeProduto();
//      imgG23.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG23.setProduto(prod4);
//      
      ImagemPequenoProduto imgP20 = new ImagemPequenoProduto();
      imgP20.setSrcImagem("img/products/signle-product/standardPequeno.png");
      imgP20.setProduto(prod4);
//      
//      ImagemPequenoProduto imgP21 = new ImagemPequenoProduto();
//      imgP21.setSrcImagem("img/products/signle-product/escovaMasculinaPequena.png");
//      imgP21.setProduto(prod4);
//      
//      ImagemPequenoProduto imgP22 = new ImagemPequenoProduto();
//      imgP22.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP22.setProduto(prod4);
//      
//      ImagemPequenoProduto imgP23 = new ImagemPequenoProduto();
//      imgP23.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP23.setProduto(prod4);
      
      prod4.setTituloDescritivo("Kit Standard - Fundamental a qualquer momento");
      prod4.setAnotacaoPrincipalDescritivo("Todos possui a necessidade de higienização diária, com organização tudo fica mais fácil."
      		+ "");
      
      prod4.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova macia;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Creme dental;</li>"
	          + "</ul>");
	  
	  prod4.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: 6 cm (Largura), 5 cm (Altura), 21 cm (Comprimento);</li>"
	          + "<li>Escova ultra macia;</li>"
	          + "<li>Fio dental: 50 metros;</li>"
	          + "<li>Creme dental: 70g;</li>"
	      + "</ul>");
	  
	  prod4.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Necessaire de fácil adaptação e organização;</li>"
	          + "<li>Fio dental, remover biofilme dental;</li>"
	          + "<li>Creme dental, auxilia na remoção de placas;</li>"
	      + "</ul>");
      
	  tmp = contidoBase(prod4);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod4);
    	  inserirImagemGrande(imgG20);
//    	  inserirImagemGrande(imgG21);
//    	  inserirImagemGrande(imgG22);
//    	  inserirImagemGrande(imgG23);
    	  inserirImagemPequeno(imgP20);
//    	  inserirImagemPequeno(imgP21);
//    	  inserirImagemPequeno(imgP22);
//    	  inserirImagemPequeno(imgP23);
      } else {
    	  prod4.setId(tmp.getId());
    	  atualizarProduto(prod4);
      }
	  
      Produto prod5 = new Produto();   
      
      prod5.setSrcImagem("img/home/banner-slider/kitVitrineMasculinoInfantil.png");
      prod5.setSrcImagemCarrinho("img/products/kitVitrineMasculinoInfantil64x74.png");
      prod5.setTitulo("Kit Infantil - Para Meninos");
      prod5.setSubTitulo("Sucesso escolar");
      prod5.setDescritivo("");
      prod5.setPreco(55D);
      prod5.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      prod5.setEspecie(EnumEspecie.PRODUTO_INFANTIL.getEspecie());
      prod5.setClasse(EnumClasseProduto.KIT_ESCOLAR.getClasse());
      prod5.setQtdEstoque(20);
      prod5.setDisponivel(true);
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG24 = new ImagemGrandeProduto();
      imgG24.setSrcImagem("img/products/signle-product/infantilMasculinoGrande.png");
      imgG24.setProduto(prod5);
      
//      ImagemGrandeProduto imgG25 = new ImagemGrandeProduto();
//      imgG25.setSrcImagem("img/products/signle-product/escovaMasculinaGrande.png");
//      imgG25.setProduto(prod5);
//      
//      ImagemGrandeProduto imgG26 = new ImagemGrandeProduto();
//      imgG26.setSrcImagem("img/products/signle-product/cremeInfantilGrande.png");
//      imgG26.setProduto(prod5);
//      
//      ImagemGrandeProduto imgG27 = new ImagemGrandeProduto();
//      imgG27.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG27.setProduto(prod5);
//      
//      ImagemGrandeProduto imgG28 = new ImagemGrandeProduto();
//      imgG28.setSrcImagem("img/products/signle-product/toalhaGrande.png");
//      imgG28.setProduto(prod5);
//      
      ImagemPequenoProduto imgP24 = new ImagemPequenoProduto();
      imgP24.setSrcImagem("img/products/signle-product/infantilMasculinoPequeno.png");
      imgP24.setProduto(prod5);
//      
//      ImagemPequenoProduto imgP25 = new ImagemPequenoProduto();
//      imgP25.setSrcImagem("img/products/signle-product/escovaMasculinaPequena.png");
//      imgP25.setProduto(prod5);
//      
//      ImagemPequenoProduto imgP26 = new ImagemPequenoProduto();
//      imgP26.setSrcImagem("img/products/signle-product/cremeInfantilPequeno.png");
//      imgP26.setProduto(prod5);
//      
//      ImagemPequenoProduto imgP27 = new ImagemPequenoProduto();
//      imgP27.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP27.setProduto(prod5);
//      
//      ImagemPequenoProduto imgP28 = new ImagemPequenoProduto();
//      imgP28.setSrcImagem("img/products/signle-product/toalhaPequena.png");
//      imgP28.setProduto(prod5);
      
      prod5.setTituloDescritivo("Kit Infantil - Sucesso nas escolas");
      prod5.setAnotacaoPrincipalDescritivo("Educar as crianças a fazer a higienização bucal até mesmo fora de casa é um desafio. "
      		+ "Trate essa tarefa com uma boa necessaire em mãos. "
//      		+ "crianças estão longe da rotina de casa. Dispomos todos os itens que facilitam esse momento. "
//      		+ "Trazendo também independência a criança quanto a cuidar da saúde bucal"
      		+ "");
      
      prod5.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ultra macia;</li>"
	              + "<li>Pasta de dente infantil;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Toalinha;</li>"
	          + "</ul>");
	  
	  prod5.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Kit: 22 cm (Largura), 14 cm (Altura), 10 cm (Comprimento);</li>"
	          + "<li>Escova ultra macia;</li>"
	          + "<li>Fio dental: 50 metros;</li>"
	          + "<li>Creme dental: 70g;</li>"
	          + "<li>Toalha de rosto;</li>"
	      + "</ul>");
	  
	  prod5.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Estojo personalizado, formato anatômico de fácil utilização, com cores alegres;</li>"
	          + "<li>Escova macia com formato adequado para a boquinha das crianças;</li>"
	          + "<li>Pasta de dente, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental, evitando a formação de placa bacteriana;</li>"
	          + "<li>Toalha, com foco em facilitar a higienizaçao;</li>"
	      + "</ul>");
      
	  tmp = contidoBase(prod5);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod5);
    	  inserirImagemGrande(imgG24);
//    	  inserirImagemGrande(imgG25);
//    	  inserirImagemGrande(imgG26);
//    	  inserirImagemGrande(imgG27);
//    	  inserirImagemGrande(imgG28);
    	  inserirImagemPequeno(imgP24);
//    	  inserirImagemPequeno(imgP25);
//    	  inserirImagemPequeno(imgP26);
//    	  inserirImagemPequeno(imgP27);
//    	  inserirImagemPequeno(imgP28);
      } else {
    	  prod5.setId(tmp.getId());
    	  atualizarProduto(prod5);
      }
	  
      Produto prod6 = new Produto();   
      
      prod6.setSrcImagem("img/home/banner-slider/kitVitrineOrtoFeminino.png");
      prod6.setSrcImagemCarrinho("img/products/kitVitrineOrtoFeminino64x74.png");
      prod6.setTitulo("Kit Ortodontico - Feminino");
      prod6.setSubTitulo("Olhar feminino");
      prod6.setDescritivo("Requinte de mulher!");
      prod6.setPreco(65D);
      prod6.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
      prod6.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
      prod6.setClasse(EnumClasseProduto.KIT_ORTODONTICO.getClasse());
      prod6.setQtdEstoque(20);
      prod6.setDisponivel(true);
      
   // Tratando secao de detalhe
      ImagemGrandeProduto imgG29 = new ImagemGrandeProduto();
      imgG29.setSrcImagem("img/products/signle-product/femininoGrande.png");
      imgG29.setProduto(prod6);
      
//      ImagemGrandeProduto imgG30 = new ImagemGrandeProduto();
//      imgG30.setSrcImagem("img/products/signle-product/escovaOrtoGrande.png");
//      imgG30.setProduto(prod6);
//      
//      ImagemGrandeProduto imgG31 = new ImagemGrandeProduto();
//      imgG31.setSrcImagem("img/products/signle-product/escovaInterGrande.png");
//      imgG31.setProduto(prod6);
//      
//      ImagemGrandeProduto imgG32 = new ImagemGrandeProduto();
//      imgG32.setSrcImagem("img/products/signle-product/passaFioGrande.png");
//      imgG32.setProduto(prod6);
//      
//      ImagemGrandeProduto imgG33 = new ImagemGrandeProduto();
//      imgG33.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG33.setProduto(prod6);
//      
//      ImagemGrandeProduto imgG34 = new ImagemGrandeProduto();
//      imgG34.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG34.setProduto(prod6);
//      
//      ImagemGrandeProduto imgG35 = new ImagemGrandeProduto();
//      imgG35.setSrcImagem("img/products/signle-product/enxaguaGrande.png");
//      imgG35.setProduto(prod6);
//      
//      ImagemGrandeProduto imgG54 = new ImagemGrandeProduto();
//      imgG54.setSrcImagem("img/products/signle-product/ceraGrande.png");
//      imgG54.setProduto(prod6);
      
      ImagemPequenoProduto imgP29 = new ImagemPequenoProduto();
      imgP29.setSrcImagem("img/products/signle-product/femininoPequeno.png");
      imgP29.setProduto(prod6);
      
//      ImagemPequenoProduto imgP30 = new ImagemPequenoProduto();
//      imgP30.setSrcImagem("img/products/signle-product/escovaOrtoPequena.png");
//      imgP30.setProduto(prod6);
//      
//      ImagemPequenoProduto imgP31 = new ImagemPequenoProduto();
//      imgP31.setSrcImagem("img/products/signle-product/escovaInterPequena.png");
//      imgP31.setProduto(prod6);
//      
//      ImagemPequenoProduto imgP32 = new ImagemPequenoProduto();
//      imgP32.setSrcImagem("img/products/signle-product/passaFioPequeno.png");
//      imgP32.setProduto(prod6);
//      
//      ImagemPequenoProduto imgP33 = new ImagemPequenoProduto();
//      imgP33.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP33.setProduto(prod6);
//      
//      ImagemPequenoProduto imgP34 = new ImagemPequenoProduto();
//      imgP34.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP34.setProduto(prod6);
//      
//      ImagemPequenoProduto imgP35 = new ImagemPequenoProduto();
//      imgP35.setSrcImagem("img/products/signle-product/enxaguaPequeno.png");
//      imgP35.setProduto(prod6);
//      
//      ImagemPequenoProduto imgP54 = new ImagemPequenoProduto();
//      imgP54.setSrcImagem("img/products/signle-product/ceraPequena.png");
//      imgP54.setProduto(prod6);
      
      prod6.setTituloDescritivo("Kit Ortodôntico - A delicadeza da mulher");
      prod6.setAnotacaoPrincipalDescritivo("Toques femininos em uma linda necessaire.  "
//      		+ "O Kit Ortodôntico Feminino possui todos os itens necessários para uma boa higiene bucal considerando a essência da mulher."
      		+ "");
      
      prod6.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ortodôntica;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Passa fio;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Cera ortodôntica;</li>"
	              + "<li>Enxaguante bucal;</li>"
	          + "</ul>");
	  
	  prod6.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores:"
			  + "<ul class=\"list-unstyled\">"
			  + "<li>Kit: 25 cm (Largura), 23 cm (Altura), 10 cm (Comprimento);</li>"
			  + "<li>Escova ortodôntica: Ultra macia;</li>"
			  + "<li>Escova interdental: 3 a 7mm;</li>"
			  + "<li>Passa fio;</li>"
			  + "<li>Creme dental: 70g;</li>"
			  + "<li>Fio dental: 50 metros;</li>"
			  + "<li>Cera ortodôntica vigente no mercado;</li>"
			  + "<li>Enxaguante bucal: 250mL;</li>"
	      + "</ul>");
	  
	  prod6.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Lindo Kit feminino;</li>"
	          + "<li>Escova interdental, devido ao seu formato cônico e cilíndrico se encaixa entre os dentes, finalizando a higienização;</li>"
	          + "<li>Passa fio, essencial para facilitar o uso do fio dental;</li>"
	          + "<li>Pasta de dente, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental localizado entre os dentes;</li>"
	          + "<li>Protegendo a mucosa bucal, nada mais útil que a cera ortodôntica;</li>"
	          + "<li>Enxaguante bucal, remove bactérias;</li>"
	      + "</ul>");
      
	  tmp = contidoBase(prod6);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod6);
    	  inserirImagemGrande(imgG29);
//    	  inserirImagemGrande(imgG30);
//    	  inserirImagemGrande(imgG31);
//    	  inserirImagemGrande(imgG32);
//    	  inserirImagemGrande(imgG33);
//    	  inserirImagemGrande(imgG34);
//    	  inserirImagemGrande(imgG35);
//    	  inserirImagemGrande(imgG54);
    	  inserirImagemPequeno(imgP29);
//    	  inserirImagemPequeno(imgP30);
//    	  inserirImagemPequeno(imgP31);
//    	  inserirImagemPequeno(imgP32);
//    	  inserirImagemPequeno(imgP33);
//    	  inserirImagemPequeno(imgP34);
//    	  inserirImagemPequeno(imgP35);
//    	  inserirImagemPequeno(imgP54);
      } else {
    	  prod6.setId(tmp.getId());
    	  atualizarProduto(prod6);
      }
	  
      Produto prod7 = new Produto();   
      
      prod7.setSrcImagem("img/home/featured-product/kitPeriodontalFeminino.png");
      prod7.setSrcImagemCarrinho("img/products/kitPeriodontalFeminino64x74.png");
      prod7.setTitulo("Kit Periodontal - Feminino");
      prod7.setPreco(65D);
      prod7.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod7.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
      prod7.setClasse(EnumClasseProduto.KIT_PERIODONTAL.getClasse());
      prod7.setQtdEstoque(20);
      prod7.setDisponivel(true);
//      
//      // Tratando secao de detalhe
      ImagemGrandeProduto imgG36 = new ImagemGrandeProduto();
      imgG36.setSrcImagem("img/products/signle-product/femininoGrande.png");
      imgG36.setProduto(prod7);
//      
//      ImagemGrandeProduto imgG37 = new ImagemGrandeProduto();
//      imgG37.setSrcImagem("img/products/signle-product/escovaFemininaGrande.png");
//      imgG37.setProduto(prod7);
//      
//      ImagemGrandeProduto imgG38 = new ImagemGrandeProduto();
//      imgG38.setSrcImagem("img/products/signle-product/escovaInterGrande.png");
//      imgG38.setProduto(prod7);
//      
//      ImagemGrandeProduto imgG39 = new ImagemGrandeProduto();
//      imgG39.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG39.setProduto(prod7);
//      
//      ImagemGrandeProduto imgG40 = new ImagemGrandeProduto();
//      imgG40.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG40.setProduto(prod7);
//      
//      ImagemGrandeProduto imgG41 = new ImagemGrandeProduto();
//      imgG41.setSrcImagem("img/products/signle-product/enxaguaGrande.png");
//      imgG41.setProduto(prod7);
//      
//      ImagemGrandeProduto imgG42 = new ImagemGrandeProduto();
//      imgG42.setSrcImagem("img/products/signle-product/interdentalGrande.png");
//      imgG42.setProduto(prod7);
//      
      ImagemPequenoProduto imgP36 = new ImagemPequenoProduto();
      imgP36.setSrcImagem("img/products/signle-product/femininoPequeno.png");
      imgP36.setProduto(prod7);
//      
//      ImagemPequenoProduto imgP37 = new ImagemPequenoProduto();
//      imgP37.setSrcImagem("img/products/signle-product/escovaFemininaPequena.png");
//      imgP37.setProduto(prod7);
//      
//      ImagemPequenoProduto imgP38 = new ImagemPequenoProduto();
//      imgP38.setSrcImagem("img/products/signle-product/escovaInterPequena.png");
//      imgP38.setProduto(prod7);
//      
//      ImagemPequenoProduto imgP39 = new ImagemPequenoProduto();
//      imgP39.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP39.setProduto(prod7);
//      
//      ImagemPequenoProduto imgP40 = new ImagemPequenoProduto();
//      imgP40.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP40.setProduto(prod7);
//      
//      ImagemPequenoProduto imgP41 = new ImagemPequenoProduto();
//      imgP41.setSrcImagem("img/products/signle-product/enxaguaPequeno.png");
//      imgP41.setProduto(prod7);
//      
//      ImagemPequenoProduto imgP42 = new ImagemPequenoProduto();
//      imgP42.setSrcImagem("img/products/signle-product/interdentalPequeno.png");
//      imgP42.setProduto(prod7);
//      
      prod7.setTituloDescritivo("Kit Periodontal - Força feminina");
      prod7.setAnotacaoPrincipalDescritivo("A doença periodontal acomete gengiva e periodonto (estrutura de suporte aos dentes) "
      		+ "o principal fator de sucesso e controle do tratamento é a perfeita higienizaçao, "
      		+ "para isso existem itens que facilitam a higiene. Para sua comodidade montamos o "
      		+ "kit perfeito para o seu tratamento não deixando de pensar nos detalhes femininos, é claro!"
      		+ "");
//      
      prod7.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ultra macia;</li>"
	              + "<li>Escova interdental;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Fita dental;</li>"
	              + "<li>Enxaguante bucal;</li>"
	          + "</ul>");
//	  
	  prod7.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores:"
			  + "<ul class=\"list-unstyled\">"
			  + "<li>Kit: 25 cm (Largura), 23 cm (Altura), 10 cm (Comprimento);</li>"
			  + "<li>Escova Ultra macia;</li>"
			  + "<li>Escova interdental: 3 a 7mm;</li>"
	          + "<li>Creme dental: 70g;</li>"
	          + "<li>Fita dental: 25 metros;</li>"
	          + "<li>Enxaguante bucal: 250mL;</li>"
	      + "</ul>");
//	  
	  prod7.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Lindo estojo personalizado, que facilita a locomoção dos itens, mantendo-os devidamente organizado e livre de exposições externas;</li>"
	          + "<li>Escova macia e anatômica com cerdas macias que não agridem a gengiva, evitando traumas;</li>"
	          + "<li>Escova interdental, para os casos onde houve perda óssea, facilitando assim a higienização;</li>"
	          + "<li>Pasta de dente, com o princípio de remoção do biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental, evitando assim a formação de placa bacteriana;</li>"
	          + "<li>Enxaguante bucal remove bactérias causadoras da doença periodontal em ate 95%;</li>"
	      + "</ul>");
//      
	  tmp = contidoBase(prod7);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod7);
    	  inserirImagemGrande(imgG36);
//    	  inserirImagemGrande(imgG37);
//    	  inserirImagemGrande(imgG38);
//    	  inserirImagemGrande(imgG39);
//    	  inserirImagemGrande(imgG40);
//    	  inserirImagemGrande(imgG41);
//    	  inserirImagemGrande(imgG42);
    	  inserirImagemPequeno(imgP36);
//    	  inserirImagemPequeno(imgP37);
//    	  inserirImagemPequeno(imgP38);
//    	  inserirImagemPequeno(imgP39);
//    	  inserirImagemPequeno(imgP40);
//    	  inserirImagemPequeno(imgP41);
//    	  inserirImagemPequeno(imgP42);
      } else {
    	  prod7.setId(tmp.getId());
    	  atualizarProduto(prod7);
      }
	  
      Produto prod8 = new Produto();   
      
      prod8.setSrcImagem("img/home/featured-product/kitFemininoPosCirurgico.png");
      prod8.setSrcImagemCarrinho("img/products/kitFemininoPosCirurgico64x74.png");
      prod8.setTitulo("Kit Pós-Cirúrgico - Feminino");
      prod8.setPreco(55D);
      prod8.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod8.setEspecie(EnumEspecie.PRODUTO_FEMININO.getEspecie());
      prod8.setClasse(EnumClasseProduto.KIT_POSCIRURGICO.getClasse());
      prod8.setQtdEstoque(6);
      prod8.setDisponivel(true);
//      
//   // Tratando secao de detalhe
      ImagemGrandeProduto imgG43 = new ImagemGrandeProduto();
      imgG43.setSrcImagem("img/products/signle-product/femininoGrande.png");
      imgG43.setProduto(prod8);
//      
//      ImagemGrandeProduto imgG44 = new ImagemGrandeProduto();
//      imgG44.setSrcImagem("img/products/signle-product/escovaFemininaGrande.png");
//      imgG44.setProduto(prod8);
//      
//      ImagemGrandeProduto imgG45 = new ImagemGrandeProduto();
//      imgG45.setSrcImagem("img/products/signle-product/pastaGrande.png");
//      imgG45.setProduto(prod8);
//      
//      ImagemGrandeProduto imgG46 = new ImagemGrandeProduto();
//      imgG46.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG46.setProduto(prod8);
//      
//      ImagemGrandeProduto imgG47 = new ImagemGrandeProduto();
//      imgG47.setSrcImagem("img/products/signle-product/enxaguaGrande.png");
//      imgG47.setProduto(prod8);
//      
      ImagemPequenoProduto imgP43 = new ImagemPequenoProduto();
      imgP43.setSrcImagem("img/products/signle-product/femininoPequeno.png");
      imgP43.setProduto(prod8);
//      
//      ImagemPequenoProduto imgP44 = new ImagemPequenoProduto();
//      imgP44.setSrcImagem("img/products/signle-product/escovaFemininaPequena.png");
//      imgP44.setProduto(prod8);
//      
//      ImagemPequenoProduto imgP45 = new ImagemPequenoProduto();
//      imgP45.setSrcImagem("img/products/signle-product/pastaPequena.png");
//      imgP45.setProduto(prod8);
//      
//      ImagemPequenoProduto imgP46 = new ImagemPequenoProduto();
//      imgP46.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP46.setProduto(prod8);
//      
//      ImagemPequenoProduto imgP47 = new ImagemPequenoProduto();
//      imgP47.setSrcImagem("img/products/signle-product/enxaguaPequeno.png");
//      imgP47.setProduto(prod8);
//      
      prod8.setTituloDescritivo("Kit Pós-Cirúrgico - Toque feminino");
      prod8.setAnotacaoPrincipalDescritivo("Os cuidados no pós-cirúrgico são essenciais para o sucesso de um tratamento. "
      		+ "Entre eles destacamos a higienização bucal. "
      		+ "Pensando nisso montamos um kit que se adéqua a esse momento delicado da sua saúde. Lembrando do lado feminino."
      		+ "");
//      
      prod8.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova macia;</li>"
	              + "<li>Creme dental;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Enxaguante bucal;</li>"
	          + "</ul>");
//	  
	  prod8.setAnotacaoTecnica("Descritivo dos itens (dimensões):"
			  + "<ul class=\"list-unstyled\">"
			  + "<li>Kit: 25 cm (Largura), 23 cm (Altura), 10 cm (Comprimento);</li>"
	          + "<li>Escova ultra macia;</li>"
	          + "<li>Creme dental: 70g;</li>"
	          + "<li>Fio dental: 50 metros;</li>"
	          + "<li>Enxaguante bucal: 250mL;</li>"
	      + "</ul>");
//	  
	  prod8.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Lindo estojo personalizado, com detalhes que facilitam o uso, tornando fácil transportar o kit no dia a dia;</li>"
	          + "<li>Escova com cerdas macias que não agride a área operada;</li>"
	          + "<li>Creme dental, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental localizado entre os dentes;</li>"
	          + "<li>Enxaguante bucal, diminuindo a proliferação bacteriana na área operada evitando infecções;</li>"
	      + "</ul>");
//      
	  tmp = contidoBase(prod8);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod8);
    	  inserirImagemGrande(imgG43);
//    	  inserirImagemGrande(imgG44);
//    	  inserirImagemGrande(imgG45);
//    	  inserirImagemGrande(imgG46);
//    	  inserirImagemGrande(imgG47);
    	  inserirImagemPequeno(imgP43);
//    	  inserirImagemPequeno(imgP44);
//    	  inserirImagemPequeno(imgP45);
//    	  inserirImagemPequeno(imgP46);
//    	  inserirImagemPequeno(imgP47);
      } else {
    	  prod8.setId(tmp.getId());
    	  atualizarProduto(prod8);
      }
	  
      Produto prod9 = new Produto();   
      
      prod9.setSrcImagem("img/home/featured-product/kitInfantilFeminino.png");
      prod9.setSrcImagemCarrinho("img/products/kitInfantilFeminino64x74.png");
      prod9.setTitulo("Kit Infantil - Para meninas");
      prod9.setSubTitulo("Delicadeza das meninas");
      prod9.setDescritivo("Coisa que menina sabe...");
      prod9.setPreco(55D);
      prod9.setTipo(EnumTipoProduto.PRODUTO_DESTAQUE.getTipo());
      prod9.setEspecie(EnumEspecie.PRODUTO_INFANTIL.getEspecie());
      prod9.setClasse(EnumClasseProduto.KIT_ESCOLAR.getClasse());
      prod9.setQtdEstoque(20);
      prod9.setDisponivel(true);
      
      // Tratando secao de detalhe
      ImagemGrandeProduto imgG48 = new ImagemGrandeProduto();
      imgG48.setSrcImagem("img/products/signle-product/infantilFemininoGrande.png");
      imgG48.setProduto(prod9);
      
//      ImagemGrandeProduto imgG49 = new ImagemGrandeProduto();
//      imgG49.setSrcImagem("img/products/signle-product/escovaFemininaGrande.png");
//      imgG49.setProduto(prod9);
//      
//      ImagemGrandeProduto imgG50 = new ImagemGrandeProduto();
//      imgG50.setSrcImagem("img/products/signle-product/cremeInfantilGrande.png");
//      imgG50.setProduto(prod9);
//      
//      ImagemGrandeProduto imgG51 = new ImagemGrandeProduto();
//      imgG51.setSrcImagem("img/products/signle-product/fioGrande.png");
//      imgG51.setProduto(prod9);
//      
//      ImagemGrandeProduto imgG52 = new ImagemGrandeProduto();
//      imgG52.setSrcImagem("img/products/signle-product/toalhaGrande.png");
//      imgG52.setProduto(prod9);
//      
      ImagemPequenoProduto imgP48 = new ImagemPequenoProduto();
      imgP48.setSrcImagem("img/products/signle-product/infantilFemininoPequeno.png");
      imgP48.setProduto(prod9);
//      
//      ImagemPequenoProduto imgP49 = new ImagemPequenoProduto();
//      imgP49.setSrcImagem("img/products/signle-product/escovaFemininaPequena.png");
//      imgP49.setProduto(prod9);
//      
//      ImagemPequenoProduto imgP50 = new ImagemPequenoProduto();
//      imgP50.setSrcImagem("img/products/signle-product/cremeInfantilPequeno.png");
//      imgP50.setProduto(prod9);
//      
//      ImagemPequenoProduto imgP51 = new ImagemPequenoProduto();
//      imgP51.setSrcImagem("img/products/signle-product/fioPequeno.png");
//      imgP51.setProduto(prod9);
//      
//      ImagemPequenoProduto imgP52 = new ImagemPequenoProduto();
//      imgP52.setSrcImagem("img/products/signle-product/toalhaPequena.png");
//      imgP52.setProduto(prod9);
      
      prod9.setTituloDescritivo("Kit Infantil - Sucesso nas escolas");
      prod9.setAnotacaoPrincipalDescritivo("Kit infantil voltado às meninas. "
//      		+ "Com nosso produto essa missão torna-se elementar, contendo todo o carinho para esse período em que as "
//      		+ "crianças estão longe da rotina de casa. Dispomos todos os itens que facilitam esse momento. "
//      		+ "Trazendo também independência a criança quanto a cuidar da saúde bucal"
      		+ "");
      
      prod9.setAnotacaoDetalhe("<p>O que oferecemos:</p>"
	    		  + "<ul class=\"list-unstyled\">"
	              + "<li>Estojo personalizado;</li>"
	              + "<li>Escova ultra macia;</li>"
	              + "<li>Pasta de dente infantil;</li>"
	              + "<li>Fio dental;</li>"
	              + "<li>Toalinha;</li>"
	          + "</ul>");
	  
	  prod9.setAnotacaoTecnica("Descritivo dos itens (dimensões), considerando a variação conforme a disposição dos fornecedores:"
			  + "<ul class=\"list-unstyled\">"
			  + "<li>Kit: 22 cm (Largura), 14 cm (Altura), 10 cm (Comprimento);</li>"
	          + "<li>Escova ultra macia;</li>"
	          + "<li>Creme dental: 50g;</li>"
	          + "<li>Fio dental: 25 metros;</li>"
	          + "<li>Toalha de rosto;</li>"
	      + "</ul>");
	  
	  prod9.setAnotacaoComposicao("Vimos como fundamental:"
			  + "<ul class=\"list-unstyled\">"
	          + "<li>Estojo personalizado, formato anatômico de fácil utilização, com cores alegres;</li>"
	          + "<li>Escova macia com formato adequado para a boquinha das crianças;</li>"
	          + "<li>Pasta de dente, remove o biofilme dental;</li>"
	          + "<li>Fio dental, remove biofilme dental, evitando a formação de placa bacteriana;</li>"
	          + "<li>Toalha, com foco em facilitar a higienizaçao;</li>"
	      + "</ul>");
      
	  tmp = contidoBase(prod9);
      if (Util.isEmpty(tmp)) {
    	  inserirProduto(prod9);
    	  inserirImagemGrande(imgG48);
//    	  inserirImagemGrande(imgG49);
//    	  inserirImagemGrande(imgG50);
//    	  inserirImagemGrande(imgG51);
//    	  inserirImagemGrande(imgG52);
    	  inserirImagemPequeno(imgP48);
//    	  inserirImagemPequeno(imgP49);
//    	  inserirImagemPequeno(imgP50);
//    	  inserirImagemPequeno(imgP51);
//    	  inserirImagemPequeno(imgP52);
      } else {
    	  prod9.setId(tmp.getId());
    	  atualizarProduto(prod9);
      }
      
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
      
//      entityManager.getTransaction().commit();
  }
  
}

