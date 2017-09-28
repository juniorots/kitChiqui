/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.base;

import javax.persistence.EntityManager;

import br.com.kitchiqui.framework.persistence.DaoJpa2;
import br.com.kitchiqui.modelo.Produto;

public class ProdutoDAO extends DaoJpa2<Produto>{

	public ProdutoDAO(EntityManager entityManager) {
        super(Produto.class, entityManager);
    }
}
