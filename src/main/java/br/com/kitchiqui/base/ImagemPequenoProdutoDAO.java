/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.base;

import javax.persistence.EntityManager;

import br.com.kitchiqui.framework.persistence.DaoJpa2;
import br.com.kitchiqui.modelo.ImagemGrandeProduto;
import br.com.kitchiqui.modelo.ImagemPequenoProduto;

public class ImagemPequenoProdutoDAO extends DaoJpa2<ImagemPequenoProduto> {

	public ImagemPequenoProdutoDAO(EntityManager entityManager) {
        super(ImagemPequenoProduto.class, entityManager);
    }

}
