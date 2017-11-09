/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.base;

import javax.persistence.EntityManager;

import br.com.kitchiqui.framework.persistence.DaoJpa2;
import br.com.kitchiqui.modelo.Blog;

/**
 *
 * @author Jose Alves
 */
public class BlogDAO extends DaoJpa2<Blog>{

    public BlogDAO(EntityManager entityManager) {
        super(Blog.class, entityManager);
    }
}
