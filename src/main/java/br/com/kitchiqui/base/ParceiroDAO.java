/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.base;

import javax.persistence.EntityManager;

import br.com.kitchiqui.framework.persistence.DaoJpa2;
import br.com.kitchiqui.modelo.Parceiro;

public class ParceiroDAO extends DaoJpa2<Parceiro> {

	public ParceiroDAO(EntityManager entityManager) {
        super(Parceiro.class, entityManager);
    }
}
