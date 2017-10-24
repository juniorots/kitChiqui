/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.base;

import javax.persistence.EntityManager;

import br.com.kitchiqui.framework.persistence.DaoJpa2;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.ImagemGrandeProduto;

public class ImagemGrandeProdutoDAO extends DaoJpa2<ImagemGrandeProduto> {

	public ImagemGrandeProdutoDAO(EntityManager entityManager) {
        super(ImagemGrandeProduto.class, entityManager);
    }
}
