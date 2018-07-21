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
        b1.setAutor("Dra. Fabiana Almeida");
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
        		+ "paciente irá sentir sintomas como: sensibilidade, dor ao se alimentar, dor espontânea, odor "
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
        b2.setAutor("Dra. Fabiana Almeida");
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
        b3.setAutor("Dra. Fabiana Almeida");
        b3.setDtPublicacao(Calendar.getInstance().getTime());
        b3.setRegistroProfissional("CRO GO-11108");
        b3.setTitulo("Higiene Bucal");
        b3.setSubTitulo("Os benefícios de uma boa higienização");
        
        b3.setTituloArtigo("Higienização bucal");
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
        
        Blog b4 = new Blog();
        b4.setSrcImagem("img/blog/sobrePosCirurgico1170x550.jpg");
        b4.setAutor("Dra. Fabiana Almeida");
        b4.setDtPublicacao(Calendar.getInstance().getTime());
        b4.setRegistroProfissional("CRO GO-11108");
        b4.setTitulo("Cuidados no pós-cirurgico odontológico");
        b4.setSubTitulo("Atenção aos cuidados pós-cirurgico");
        
        b4.setTituloArtigo("Pós-cirurgico e cuidados");
        b4.setDescritivoArtigo("Existem diversos procedimentos cirúrgicos em odontologia, entre os mais comuns estão...");
        b4.setSrcImagemArtigo("img/home/articles/sobrePosCirurgico270x179.jpg");
        
        b4.setTexto("<p style=\"text-align: justify;\">"
        		+ "Existem diversos procedimentos cirúrgicos em odontologia, entre os mais comuns estão as extrações dentárias, "
        		+ "implantes, enxertos ósseos e gengivais entre outros.<br /><br />" 
        		+ "Após uma intervenção cirúrgica o organismo precisa de tempo para se regenerar, há cuidados que devem ser "
        		+ "tomados para evitar complicações, como hemorragias, inchaços e infecções, tornado assim o processo de "
        		+ "cicatrização mais rápido. Pensando nisso recomenda-se seguir os cuidados descritos:<br /><br />" 
        		+ "- Siga todas as orientações passadas por seu dentista, fazendo uso de medicamentos conforme orientado;<br /><br />"
        		+ "<strong>Para evitar o sangramento e inchaço:</strong><br />" 
        		+ "- Morda firmemente uma gaze por meia hora e mantenha a cabeça elevada;<br /><br />" 
        		+ "- Evite cuspir ou fazer sucção com canudo;<br /><br />" 
        		+ "- Evite fumar por pelo menos 24 hrs;<br /><br />" 
        		+ "- Aplique compressa de gelo sobre a face para evitar inchaços, tendo o cuidado de envolver o gelo a uma toalha para "
        		+ "não causar queimaduras na pele;<br /><br />" 
        		+ "<strong>Dieta pós-cirúrgica:</strong><br />" 
        		+ "- Ingerir bastante líquido;<br /><br />" 
        		+ "- Durante as primeiras 48 hrs a dieta deve ser líquida, pastosa e fria. Após esse período a dieta deve "
        		+ "aos poucos ir voltando ao normal, porém evitando a ingestão de grãos (pipoca, amendoim, gergelim, granola entre outros) "
        		+ "nesse período a ferida ainda está em cicatrização.<br /><br />" 
        		+ "<strong>Higienização:</strong><br />" 
        		+ "- Não faça bochecho ou escove os dentes algumas horas após a cirurgia, isso dará tempo para o organismo "
        		+ "formar um coagulo no local lesionado, iniciando assim o processo de cicatrização;<br /><br />" 
        		+ "- A escovação deve ser feita normalmente sempre após as refeições, preservando a área operada nunca "
        		+ "passar a escova sobre a lesão cirúrgica;<br /><br />" 
        		+ "- Faça o uso de fio dental, com uma escova macia escove todos os dentes delicadamente usando a "
        		+ "técnica de sua preferência, faça a escovação da língua, é recomendado usar uma solução de bochecho "
        		+ "com clorexidina para evitar o acúmulo de bactérias nesse período.<br /><br />" 
        		+ "<strong>Repouso:</strong><br />" 
        		+ "- Evite atividade física, esforço e exposição ao sol;<br /><br />" 
        		+ "- Faça o retorno ao cirurgião dentista conforme recomendado."
        		+ "</p>");
        
        tmp = contidoBase(b4);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b4);
        } else {
        	b4.setId(tmp.getId());
        	atualizarBlog(b4);
        }
        
        Blog b5 = new Blog();
        b5.setSrcImagem("img/blog/sobrePeriodontite1170x550.jpg");
        b5.setAutor("Dra. Fabiana Almeida");
        b5.setDtPublicacao(Calendar.getInstance().getTime());
        b5.setRegistroProfissional("CRO GO-11108");
        b5.setTitulo("Periodontite");
        b5.setSubTitulo("Para evitar a periodontite");
        
        b5.setTituloArtigo("Problemas da Periodontite");
        b5.setDescritivoArtigo("Além dessa patologia oral ser infecciosa e bacteriana atinge pessoas adultas, sendo uma das causas de perda dentária...");
        b5.setSrcImagemArtigo("img/home/articles/sobrePeriodontite270x179.jpg");
        
        b5.setTexto("<p style=\"text-align: justify;\">"
        		+ "Doença  infecciosa e bacteriana. Trata-se de uma patologia oral que atinge pessoas adultas, "
        		+ "seja ela de leve, médio ou de alto grau, é uma das causas de perda dentária.<br /><br />" 
        		+ "Essa doença é a fase progressiva da gengivite, que por sua vez é a inflamação gengival. A diferença "
        		+ "entre ambas é que a periodontite estendeu-se para os tecidos de suporte dos dentes, como o osso e "
        		+ "o ligamento periodontal.<br /><br />"
        		+ "Ela evolui a partir do tártaro, que por sua vez se origina do armazenamento da placa bacteriana.<br /><br />" 
        		+ "O melhor  tratamento é a prevenção, cuidar de uma higienização adequada, com técnica e produtos "
        		+ "adequados, associada a visitas regulares ao dentista.<br /><br />"
        		+ "Infelizmente em alguns casos a doença já está instalada, então deve-se procurar um profissional para orientar ao "
        		+ "tratamento mais adequado.<br /><br />" 
        		+ "Cada caso depende do grau da doença e somente um dentista poderá indicar a melhor solução. Sendo "
        		+ "variável do mais simples - procedimentos não cirúrgicos - avançando até casos mais graves "
        		+ "necessitando assim de intervenção cirúrgica.<br /><br />" 
        		+ "<strong>Para o caso de tratamento simples indica-se:</strong><br />"
        		+ "- Raspagem de tártaro, medicamentos, associado a boa higienização;<br /><br />"
        		+ "- Visitas regulares ao dentista.<br /><br />" 
        		+ "<strong>Nos casos mais graves o tratamento pode se estender a outros procedimentos como:</strong><br />"
        		+ "- Reparação cirúrgica;<br /><br />"
        		+ "- Enxerto ósseo, gengival entre outros."
        		+ "</p>");
        
        tmp = contidoBase(b5);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b5);
        } else {
        	b5.setId(tmp.getId());
        	atualizarBlog(b5);
        }
        
        Blog b6 = new Blog();
        b6.setSrcImagem("img/blog/sobreEscovacaoAdulto1170x550.jpg");
        b6.setAutor("Dra. Fabiana Almeida");
        b6.setDtPublicacao(Calendar.getInstance().getTime());
        b6.setRegistroProfissional("CRO GO-11108");
        b6.setTitulo("Escovação para Adultos");
        b6.setSubTitulo("Das técnicas para adultos escovar");
        
        b6.setTituloArtigo("Escovação - Adultos");
        b6.setDescritivoArtigo("A escovação ideal deve remover toda a placa bacteriana sem danificar a gengiva é evitar impor força excessiva...");
        b6.setSrcImagemArtigo("img/home/articles/sobreEscovacaoAdulto270x179.png");
        
        b6.setTexto("<p style=\"text-align: justify;\">"
        		+ "A escovação ideal deve remover toda a placa bacteriana sem danificar a gengiva, "
        		+ "indica-se evitar impor força excessiva ao dente para não causar danos ao esmalte dentário.<br /><br />"
        		+ "Nós dentistas indicamos técnicas para tornar sua escovação mais eficaz.<br /><br />" 
        		+ "Na maioria dos casos o adulto já tem um hábito inserido em sua rotina (forma que "
        		+ "escovou a vida inteira) necessitando de dedicação para a mudança do costume que "
        		+ "muitas vezes não é correto, lembrando que a educação dos movimentos somente será "
        		+ "necessária para pacientes onde a técnica habitual não é eficaz.<br /><br />" 
        		+ "<strong>Das técnicas, descreve-se:</strong><br />" 
        		+ "- Inicie sua higienização bucal pelo uso do fio dental ("
        		+ "esse irá limpar entre os dentes onde a escova não consegue alcançar, devendo "
        		+ "ser colocado pelos dentes deslizando-o na superfície de um dente e depois "
        		+ "repetir o movimento até o próximo);<br /><br />" 
        		+ "<strong>Dentes superiores:</strong><br />" 
        		+ "- Coloque a cabeça da escova contra os dentes do fundo da boca fazendo um ângulo de "
        		+ "45° graus, entre a gengiva e o dente, faça movimentos suaves de vai e vem, "
        		+ "conte 10 vezes;<br /><br />"
        		+ "- Em seguida faça movimentos de varredura de cima para baixo, repetindo 10 vezes;<br /><br />"
        		+ "- Continue repetindo o movimento para os demais, "
        		+ "escove a cada dois dentes por vez;<br /><br />" 
        		+ "- Esse movimento deve ser feito em todas as faces dos dentes - vestibular "
        		+ "(frente), palatino (dentro) e encima que é onde mastigamos.<br /><br />" 
        		+ "<strong>Dentes inferiores:</strong><br />" 
        		+ "- Deve-se repetir todos os movimentos, porém a varredura deve ser de baixo para cima;<br /><br />"
        		+ "- Indica-se escovar a língua com movimentos de traz para frente, tenha o cuidado para não levar resíduos "
        		+ "para o interior da garganta;<br /><br />" 
        		+ "- Para finalizar use um enxaguante bucal, o ideal e fazer 1 bochecho de 1 minuto."
        		+ "</p>");
        
        tmp = contidoBase(b6);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b6);
        } else {
        	b6.setId(tmp.getId());
        	atualizarBlog(b6);
        }
        
        Blog b7 = new Blog();
        b7.setSrcImagem("img/blog/sobreEscovacaoInfantil1170x550.jpg");
        b7.setAutor("Dra. Fabiana Almeida");
        b7.setDtPublicacao(Calendar.getInstance().getTime());
        b7.setRegistroProfissional("CRO GO-11108");
        b7.setTitulo("Escovação para Crianças");
        b7.setSubTitulo("Das técnicas para criança escovar");
        
        b7.setTituloArtigo("Escovação - Crianças");
        b7.setDescritivoArtigo("Escovar os dentes de uma criança requer muito cuidado e dedicação é importante inserir esses...");
        b7.setSrcImagemArtigo("img/home/articles/sobreEscovacaoInfantil270x179.png");
        
        b7.setTexto("<p style=\"text-align: justify;\">"
        		+ "Escovar os dentes de uma criança requer muito cuidado e dedicação. É importante "
        		+ "inserir esses hábitos nos primeiros meses de vida, assim como  visitar o dentista "
        		+ "para que isso se torne algo natural e a criança leve os cuidados com a saúde bucal "
        		+ "para toda vida, evitando problemas futuros para a vida adulta.<br /><br />" 
        		+ "A escovação deve ser levada a sério, a criança deve ser estimulada, "
        		+ "instruída desde sempre a uma boa higiene bucal, mas não devemos forçar evitando assim "
        		+ "traumas ou rejeição por sua parte, este momento dever ser consciente e prazeroso.<br /><br />" 
        		+ "Inicialmente a criança dever ser motivada a passar o fio dental, seguindo da "
        		+ "escovação desenvolvendo assim sua coordenação motora e também o sentimento de "
        		+ "responsabilidade pela sua saúde oral. É importante os pais revisar a escovação "
        		+ "(escovar novamente) para que a higiene fique perfeita.<br /><br />"
        		+ "Com o desenvolvimento da coordenação motora os pais devem remover o hábito de escovar novamente, "
        		+ "passando a responsabilidade para a criança criando assim o cargo de somente "
        		+ "monitorar o processo.<br /><br />" 
        		+ "<strong>Técnica de escovação:</strong><br />" 
        		+ "- Com os dentes cerrados, realize movimentos circulares com as pontas das "
        		+ "cerdas da escova sobre os dentes;<br /><br />"
        		+ "- Faça movimentos durante 10 segundos em cada grupo de dois dentes nas faces voltadas para a bochecha, nas "
        		+ "faces internas, e nas faces de mastigação de cada dente. “Tecnicamente chamado de "
        		+ "fones”, porém é conhecida como técnica da bolinha;<br /><br />" 
        		+ "- Proucre escovar a parte superior da língua;<br /><br />" 
        		+ "- Dica: Invente uma história, faça brincadeiras e este momento será tão legal "
        		+ "que a criança não vai esperar você pedir pra ela ir escovar os dentes, "
        		+ "tomando essa iniciativa por conta."
        		+ "</p>");
        
        tmp = contidoBase(b7);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b7);
        } else {
        	b7.setId(tmp.getId());
        	atualizarBlog(b7);
        }
        
        Blog b8 = new Blog();
        b8.setSrcImagem("img/blog/sobreOrto1170x550.jpg");
        b8.setAutor("Dra. Fabiana Almeida");
        b8.setDtPublicacao(Calendar.getInstance().getTime());
        b8.setRegistroProfissional("CRO GO-11108");
        b8.setTitulo("Ortodontia");
        b8.setSubTitulo("Cuidados com o aparelho ortodôntico");
        
        b8.setTituloArtigo("Aparelho Ortodôntico");
        b8.setDescritivoArtigo("O uso de aparelho ortodôntico requer muitos cuidados, já que o tratamento na maioria das vezes se estende...");
        b8.setSrcImagemArtigo("img/home/articles/sobreOrto270x179.jpg");
        
        b8.setTexto("<p style=\"text-align: justify;\">"
        		+ "O uso de aparelho ortodôntico requer muitos cuidados, já que o tratamento na maioria das "
        		+ "vezes se estende há um longo período. É preciso uma interação entre ortodontista e "
        		+ "paciente para que haja sucesso no tratamento. Selecionei dicas importantes para vocês.<br /><br />" 
        		+ "<strong>Visitas regulares ao ortodontista</strong><br />" 
        		+ " O aparelho ortodôntico precisa ser ativado para que haja o desenvolvimento do "
        		+ "tratamento, os retornos podem ser mensais ou como o profissional planejar.<br /><br />" 
        		+ "<strong>Cuidados com a alimentação</strong><br />" 
        		+ "Com o uso do aparelho ortodôntico o paciente deve mudar alguns hábitos alimentares, "
        		+ "é preciso sempre observar a textura. Alimentos duros e pegajosos devem ser evitados "
        		+ "como, por exemplo:<br /><br />" 
        		+ "- Balas, chicletes, pirulitos e caramelos;<br /><br />" 
        		+ "- Frutas duras como, maçã, goiaba e frutas de caroço devem ser evitadas ou consumidas "
        		+ "cortadas.<br /><br />" 
        		+ "- Carnes duras ou assadas devem ser evitadas ou consumir picada;<br /><br />" 
        		+ "- Procure sempre observar a textura e evitar alimentos mais densos.<br /><br />" 
        		+ "<strong>Higienização</strong><br />" 
        		+ "É preciso uma atenção maior a higienização, pois ela se torna mais difícil, "
        		+ "assim o paciente deve escolher acessórios adequados, dedicar tempo e habilidade.<br /><br />" 
        		+ "Escolha a técnica adequada, faça uso de itens como passa fio para facilitar o uso do fio dental.<br /><br />" 
        		+ "Com uma escova ortodôntica faça a escovação como se estivesse fazendo na escovação convencional, utilize "
        		+ "escovas do tipo interdental para finalizar a higiene de forma a limpar onde a escova tradicional "
        		+ "não alcança.<br /><br />" 
        		+ "Use sempre uma solução de bochecho.<br /><br />" 
        		+ "Em caso de machucados, utilize cera ortodôntica para proteção da mucosa, ela irá proteger a área "
        		+ "machucada facilitando a cicatrização.<br /><br />" 
        		+ "<strong>Não seja curioso</strong><br />" 
        		+ "Evite mexer no aparelho ou morder objetos estranhos para evitar a sua quebra e consequentemente "
        		+ "causar trauma a gengivas língua ou bochechas."
        		+ "</p>");
        
        tmp = contidoBase(b8);
        if (Util.isEmpty(tmp)) {
        	inserirBlog(b8);
        } else {
        	b8.setId(tmp.getId());
        	atualizarBlog(b8);
        }
    }
}
