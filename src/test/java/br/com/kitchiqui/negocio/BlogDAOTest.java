/**
 * Vendas de Kits chiques.
 * 
 * @author Jose Alves
 */
package br.com.kitchiqui.negocio;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.kitchiqui.base.BlogDAO;
import br.com.kitchiqui.controller.BaseController;
import br.com.kitchiqui.modelo.Blog;
import br.com.kitchiqui.modelo.EnumAssuntoBlog;
import br.com.kitchiqui.util.Util;
import lombok.Cleanup;

public class BlogDAOTest extends BaseController {
	
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
     * Util na verificacao de blog ja existente na base de dados
     * @return
     */
    public Blog contidoBase(Blog blog) {
    	@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
                
        BlogDAO dao = new BlogDAO(entityManager);
        
        if (!Util.isEmpty(dao.findByStringField("srcImagem", blog.getSrcImagem(), true, 0, 1))) {
        	return dao.findByStringField("srcImagem", blog.getSrcImagem(), true, 0, 1).get(0);
        } else 
        	return null;
    }
    
    /**
     * Atualizando blog
     */
    public void atualizarBlog(Blog blog) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        BlogDAO dao = new BlogDAO(entityManager);
        dao.update(blog);
        entityManager.getTransaction().commit();
	}
    
    /**
     * Inserindo blog na base
     */
    public void inserirBlog(Blog blog) {
		@Cleanup
        final EntityManager entityManager = getInstanceEntity();
        entityManager.getTransaction().begin();
        
        BlogDAO dao = new BlogDAO(entityManager);
        dao.insert(blog);
        entityManager.getTransaction().commit();
	}
    
    @Test
    public void mainTest(){
    	@Cleanup
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("databaseDefault");

        @Cleanup
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
//        BlogDAO parDAO = new BlogDAO(entityManager);
        
        Blog b1 = new Blog();
        b1.setSrcImagem("img/blog/blog-01.jpg");
        b1.setAutor("Fabiana Almeida");
        b1.setDtPublicacao(Calendar.getInstance().getTime());
        b1.setRegistroProfissional("CRO GO-11108");
        b1.setTitulo("Qual o melhor tempo?");
        b1.setSubTitulo("A importância do tempo de escovação.");
        b1.setTipoAssunto(EnumAssuntoBlog.TEMPO_ESCOVACAO.getTipo());
        b1.setTexto("<p>"
        		+ "A escova e o fio dental são seus principais aliados para dentes brancos e um hálito fresco sempre. "
        		+ "A escova ajuda a remover grande parte da placa, prevenindo a cárie. Já o fio dental vai alcançar os restinhos de "
        		+ "comida naqueles cantinhos mais difíceis, como embaixo da gengiva e entre os dentes. "
        		+ "Também não dá para esquecer de escovar a língua. Então, será que você tem feito uma escovação ideal? "
        		+ "Que tal aprender de uma vez por todas o passo a passo?"
        		+ "</p>");
        
        Blog tmp = contidoBase(b1);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b1);
        } else {
        	b1.setId(tmp.getId());
        	atualizarBlog(b1);
        }
//        parDAO.insert(b1);
        
        Blog b2 = new Blog();
        b2.setSrcImagem("img/blog/blog-02.jpg");
        b2.setAutor("Fabiana Almeida");
        b2.setDtPublicacao(Calendar.getInstance().getTime());
        b2.setRegistroProfissional("CRO GO-11108");
        b2.setTitulo("A melhor maneira de higienizar");
        b2.setSubTitulo("Você sabe fazer higiene bucal?");
        b2.setTipoAssunto(EnumAssuntoBlog.COMO_HIGIENIZAR.getTipo());
        b2.setTexto("<p>"
        		+ "Para evitar esse problema, faça diariamente a higienização da língua para remover a saburra lingual. "
        		+ "Para isso, escolha os higienizadores linguais plásticos, do tipo CTC, que removem a saburra lingual sem machucar a língua e sem provocar ânsia e náuseas. "
        		+ "A escova foi desenvolvida para escovar os dentes e não a língua"
        		+ "</p>");
        
        tmp = contidoBase(b2);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b2);
        } else {
        	b2.setId(tmp.getId());
        	atualizarBlog(b2);
        }
        
//        parDAO.insert(b2);
//        entityManager.getTransaction().commit();
    }
}
