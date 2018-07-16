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

    	Blog b1 = new Blog();
        b1.setSrcImagem("img/blog/sobreCarie1170x550.jpg");
        b1.setAutor("Fabiana Almeida");
        b1.setDtPublicacao(Calendar.getInstance().getTime());
        b1.setRegistroProfissional("CRO GO-11108");
        b1.setTitulo("O que é cárie?");
        b1.setSubTitulo("Dos fatores da cárie");
        
        b1.setTituloArtigo("Falando sobre Cárie e prevenções");
        b1.setDescritivoArtigo("Trata-se de uma doença multifatorial, sendo preciso da interação de diversos fatores para ser desenvolvida...");
        b1.setSrcImagemArtigo("img/home/articles/sobreCarie270x179.jpg");
        
        b1.setTexto("<p style=\"text-align: justify;\">"
        		+ "Trata-se de uma doença multifatorial, sendo preciso da interação de diversos fatores para ser desenvolvida.<br />" 
        		+ "Entre eles estão a associação de bactérias presentes no meio bucal, dieta e o tempo em que o paciente leva "
        		+ "após uma refeição ate a próxima higienização.<br /><br />"
        		+ "Não e possível transmitir a doença carie, por que a formação de biofilme dental com potencial cariogênico "
        		+ "esta relacionado ao hábito de vida do paciente, incluindo sua dieta, técnicas de escovação e visitas ao "
        		+ "dentista como forma preventiva.<br /><br />"
        		+ "Na presença de resíduos alimentares as bactérias produzem ácidos que desmineralizam os dentes, causando "
        		+ "erosões dentárias (buracos nos dentes) inicialmente essas erosões se dão na camada mais superficial dos dentes, "
        		+ "que é o esmalte dentário, na maioria das vezes se torna profunda invadindo a próxima camada, "
        		+ "que chamamos de dentina.<br /><br />"
        		+ "Pessoas que tem uma dieta rica em açúcar e carboidratos, associada aos fatores "
        		+ "acima tem um predisposição maior a desenvolver a doença carie.<br /><br />" 
        		+ "A partir daí a prevenção já não e possível para resolver o problema, após a instalação de  doença o "
        		+ "paciente ira sentir sintomas como: sensibilidade, dor ao se alimentar, dor espontânea, odor "
        		+ "desagradável ao falar.<br /><br />"
        		+ "Desse momento em diante é preciso tratar a doença, um cirurgião dentista irá "
        		+ "avaliar o caso e escolher o melhor tratamento."
        		+ "</p>");
        
        Blog tmp = contidoBase(b1);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b1);
        } else {
        	b1.setId(tmp.getId());
        	atualizarBlog(b1);
        }
        
        Blog b2 = new Blog();
        b2.setSrcImagem("img/blog/sobreGengivite1170x550.jpg");
        b2.setAutor("Fabiana Almeida");
        b2.setDtPublicacao(Calendar.getInstance().getTime());
        b2.setRegistroProfissional("CRO GO-11108");
        b2.setTitulo("Gengivites");
        b2.setSubTitulo("Problemas causados pela Gengivite");
        
        b2.setTituloArtigo("Tratando Gengivites");
        b2.setDescritivoArtigo("Inflamação dos tecidos gengivais, sendo até a principal causa da perda dentaria entre adultos...");
        b2.setSrcImagemArtigo("img/home/articles/sobreGengivite270x179.png");
        
        b2.setTexto("<p style=\"text-align: justify;\">"
        		+ "Inflamação dos tecidos gengivais, ela e a forma mais simples das doenças gengivais, "
        		+ "porém  se não tratada rapidamente se torna a porta de entrada para sua forma mais grave como a "
        		+ "periodontite, sendo a principal causa da perda dentária em adultos.<br /><br />" 
        		+ "Sintomas: irritação, vermelhidão das papilas e tecidos gengivais, sensibilidade, inchaços e sangramentos da "
        		+ "gengiva geralmente ao escovar e ao se alimentar.<br /><br />" 
        		+ "O principal fator para a causa da gengivite e devido a má ou falta de higienização "
        		+ "bucal. Desse ponto em diante há a formação de placa bacteriana próxima a margem gengival, se não "
        		+ "tratado evoluí para a formação de tártaro, podendo evoluir para a forma mais grave da doença, "
        		+ "levando a perda do osso de suporte dentário, retração das gengivas que dão uma aparência de "
        		+ "dentes alongada e ate a  perdas dentárias.<br /><br />" 
        		+ "<strong>Como prevenir a gengivite?</strong><br />" 
        		+ "- Escovação correta e uso apropriado do fio dental para remover placa e restos de alimentos, "
        		+ "visitas ao dentista para fazer limpeza profilática como forma preventiva e controlar o "
        		+ "aparecimento de tártaro;<br /><br />" 
        		+ "- Alimentação correta para garantir nutrição adequada;<br /><br />" 
        		+ "- Evitar cigarros e outras formas de tabaco.<br /><br />" 
        		+ "<strong>Como tratar  a gengivite já instalada.</strong><br />" 
        		+ "Uma boa higiene bucal é essencial. A limpeza profissional também é extremamente importante, haja vista que "
        		+ "que a placa se acumula e endurece (ou torna-se tártaro), apenas o dentista pode removê-la.<br /><br />" 
        		+ "A partir daí o paciente deverá mudar seus hábitos de higiene, utilizando-se de um kit com produtos "
        		+ "e escovas indicados por seu dentista, fazendo a higiene correta e assim o controle da doença."
        		+ "</p>");
        
        tmp = contidoBase(b2);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b2);
        } else {
        	b2.setId(tmp.getId());
        	atualizarBlog(b2);
        }
        
        Blog b3 = new Blog();
        b3.setSrcImagem("img/blog/sobreEscovacao1170x550.jpg");
        b3.setAutor("Fabiana Almeida");
        b3.setDtPublicacao(Calendar.getInstance().getTime());
        b3.setRegistroProfissional("CRO GO-11108");
        b3.setTitulo("Higiene Bucal");
        b3.setSubTitulo("Os benefícios de uma boa higienização");
        
        b3.setTituloArtigo("A importância da higienização bucal");
        b3.setDescritivoArtigo("Principal aliada na prevenção de doenças bucais, como a cárie, gengivite e periodontite...");
        b3.setSrcImagemArtigo("img/home/articles/sobreEscovacao270x179.jpg");
        
        b3.setTexto("<p style=\"text-align: justify;\">"
        		+ "Principal aliada na prevenção de doenças bucais, como a cárie, gengivite e periodontite, essas "
        		+ "doenças ocasionam outros problemas como, por exemplo, a halitose - mau hálito. <br /><br />"
        		+ "Outro dano muito severo ocasionado a quem não tem esses cuidados e a perda precoce dos dentes, "
        		+ "causando prejuízo ao sistema digestivo e consequentemente a toda saúde.<br /><br />" 
        		+ "Entretanto, uma perfeita escovação é o melhor meio de <i>prevenção contra vários  problemas</i>, em "
        		+ "outras palavras, a higiene bucal é necessária para  manter a <i>saúde dos dentes e boca</i>, os cuidados "
        		+ "preventivos devem ser diário, para isso, existem técnicas e produtos que auxiliam nesses cuidados, "
        		+ "um profissional qualificado irá ajudar o paciente a escolher a melhor técnica de escovação e os "
        		+ "melhores produtos para o seu caso.<br /><br />" 
        		+ "Em suma, a <i>higiene bucal</i> é imprescindível <i>em todas as etapas da vida</i>. Os primeiros hábitos, "
        		+ "assim como uma adequada rotina devem ser inseridos ainda na infância, mas precisamente nos "
        		+ "primeiros dias de vida fazendo com que a criança leve esse hábito naturalmente.<br /><br />" 
        		+ "É recomendado que o paciente mantenha os dentes e boca sempre limpos, para  isso deverá fazer "
        		+ "sua higienização bucal sempre após as refeições, utilizando de produtos para auxiliar nesses "
        		+ "cuidados, e  seguindo a seguinte sequência de higienização:<br /><br />" 
        		+ "- Uso do fio dental;<br /><br />"
        		+ "- Escovação adequada dos dentes - com auxílio de uma escova super macia - com tempo médio de 2 minutos;<br /><br />"
        		+ "- Uso adequado de um enxaguante bucal."
        		+ "</p>");
        
        tmp = contidoBase(b3);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b3);
        } else {
        	b3.setId(tmp.getId());
        	atualizarBlog(b3);
        }
        
    }
}
