/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.kitchiqui.base.ProdutoDAO;
import br.com.kitchiqui.modelo.EnumTipoProduto;
import br.com.kitchiqui.modelo.Produto;
import lombok.Cleanup;

/**
 * Responsavel por dar a carga inicial na base de dados, visando consistencia no sistema.
 * @author junior
 *
 */
public class CargaInicial {
	
	public static void carregarVitrinePrincipal() {
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
	      
	      Produto produto2 = new Produto();   
	      
	      produto2.setSrcImagem("img/home/banner-slider/shoe2.png");
	      produto2.setTitulo("TÊNIS BRANCO");
	      produto2.setSubTitulo("Suavidade no estilo");
	      produto2.setDescritivo("Conquista cada passo");
	      produto2.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
	      
	      dao.insert(produto2);      
	      
	      Produto produto3 = new Produto();   
	      
	      produto3.setSrcImagem("img/home/banner-slider/shoe3.png");
	      produto3.setTitulo("TÊNIS PRETO");
	      produto3.setSubTitulo("Estilo Blade");
	      produto3.setDescritivo("Dominando um estilo");
	      produto3.setTipo(EnumTipoProduto.PRODUTO_VITRINE.getTipo());
	      
	      dao.insert(produto3);      
	      entityManager.getTransaction().commit();
	}
	
}
