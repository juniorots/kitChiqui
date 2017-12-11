/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.base;

import javax.persistence.EntityManager;

import br.com.kitchiqui.framework.persistence.DaoJpa2;
import br.com.kitchiqui.modelo.MalaDireta;

public class MalaDiretaDAO extends DaoJpa2<MalaDireta> {

	public MalaDiretaDAO(EntityManager entityManager) {
        super(MalaDireta.class, entityManager);
    }	
}
