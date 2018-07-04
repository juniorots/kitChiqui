package br.com.kitchiqui.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.Produto;

/**
 * Concentrador de atividades backup dos dados uteis
 * ao Kitchiqui
 * 
 * @author Jose Alves
 *
 */
public class BackUp {
	
	/**
	 * Persiste as informacoes no XML
	 * @param clientes
	 */
	public static boolean gravarDadosCliente(List<Cliente> clientes) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("kitchiqui");
			doc.appendChild(rootElement);

			int tmp = 1;
			
			for (Cliente cliente : clientes) {
				Element client = doc.createElement("cliente");
				rootElement.appendChild(client);
				client.setAttribute("id", String.valueOf(tmp));
				
				Element nome = doc.createElement("nome");
				nome.appendChild(doc.createTextNode(cliente.getNome()));
				client.appendChild(nome);
				
				Element nomeCompleto = doc.createElement("nomeCompleto");
				nomeCompleto.appendChild(doc.createTextNode(cliente.getNomeCompleto()));
				client.appendChild(nomeCompleto);
				
				Element nrCpf = doc.createElement("nrCpf");
				nrCpf.appendChild(doc.createTextNode(cliente.getNrCpf()));
				client.appendChild(nrCpf);
				
				Element nrRG = doc.createElement("nrRG");
				nrRG.appendChild(doc.createTextNode(cliente.getNrRG()));
				client.appendChild(nrRG);
				
				Element nrTelefone = doc.createElement("nrTelefone");
				nrTelefone.appendChild(doc.createTextNode(cliente.getNrTelefone()));
				client.appendChild(nrTelefone);
				
				Element email = doc.createElement("email");
				email.appendChild(doc.createTextNode(cliente.getEmail()));
				client.appendChild(email);
				
				Element senha = doc.createElement("senha");
				senha.appendChild(doc.createTextNode(cliente.getSenha()));
				client.appendChild(senha);
				
				Element dtNascimento = doc.createElement("dtNascimento");
				dtNascimento.appendChild(doc.createTextNode(cliente.getDtNascimento().toString()));
				client.appendChild(dtNascimento);
				
				Element tmpDtNascimento = doc.createElement("tmpDtNascimento");
				tmpDtNascimento.appendChild(doc.createTextNode(cliente.getTmpDtNascimento()));
				client.appendChild(tmpDtNascimento);
				
				// Endereco
				Element endereco = doc.createElement("endereco");
				client.appendChild(endereco);
				endereco.setAttribute("id", String.valueOf(tmp));
				
				Element modoEnvio = doc.createElement("modoEnvio");
				modoEnvio.appendChild(doc.createTextNode(cliente.getEndereco().getModoEnvio().toString()));
				endereco.appendChild(modoEnvio);
				
				Element precoModoEnvio = doc.createElement("precoModoEnvio");
				precoModoEnvio.appendChild(doc.createTextNode(cliente.getEndereco().getPrecoModoEnvio().toString()));
				endereco.appendChild(precoModoEnvio);
				
				Element cep = doc.createElement("cep");
				cep.appendChild(doc.createTextNode(cliente.getEndereco().getCep()));
				endereco.appendChild(cep);
				
				Element nomeRua = doc.createElement("nomeRua");
				nomeRua.appendChild(doc.createTextNode(cliente.getEndereco().getNomeRua()));
				endereco.appendChild(nomeRua);
				
				Element numero = doc.createElement("numero");
				numero.appendChild(doc.createTextNode(cliente.getEndereco().getNumero()));
				endereco.appendChild(numero);
				
				Element complemento = doc.createElement("complemento");
				complemento.appendChild(doc.createTextNode(cliente.getEndereco().getComplemento()));
				endereco.appendChild(complemento);
				
				Element bairro = doc.createElement("bairro");
				bairro.appendChild(doc.createTextNode(cliente.getEndereco().getBairro()));
				endereco.appendChild(bairro);
				
				Element nomeCidade = doc.createElement("nomeCidade");
				nomeCidade.appendChild(doc.createTextNode(cliente.getEndereco().getNomeCidade()));
				endereco.appendChild(nomeCidade);
				
				Element estado = doc.createElement("estado");
				estado.appendChild(doc.createTextNode(cliente.getEndereco().getEstado()));
				endereco.appendChild(estado);
				
				//Pagamento
				Element pagamento = doc.createElement("pagamento");
				client.appendChild(pagamento);
				pagamento.setAttribute("id", String.valueOf(tmp));
				
				Element tipoPagamento = doc.createElement("tipoPagamento");
				tipoPagamento.appendChild(doc.createTextNode(cliente.getPagamento().getTipoPagamento().toString()));
				pagamento.appendChild(tipoPagamento);
				
				Element numeroCartao = doc.createElement("numeroCartao");
				numeroCartao.appendChild(doc.createTextNode(cliente.getPagamento().getNumeroCartao()));
				pagamento.appendChild(numeroCartao);
				
				Element mesValidadeCartao = doc.createElement("mesValidadeCartao");
				mesValidadeCartao.appendChild(doc.createTextNode(cliente.getPagamento().getMesValidadeCartao().toString()));
				pagamento.appendChild(mesValidadeCartao);
				
				Element anoValidadeCartao = doc.createElement("anoValidadeCartao");
				anoValidadeCartao.appendChild(doc.createTextNode(cliente.getPagamento().getAnoValidadeCartao().toString()));
				pagamento.appendChild(anoValidadeCartao);
				
				Element codigoSeguranca = doc.createElement("codigoSeguranca");
				codigoSeguranca.appendChild(doc.createTextNode(cliente.getPagamento().getCodigoSeguranca().toString()));
				pagamento.appendChild(codigoSeguranca);
				
				Element nrParcelas = doc.createElement("nrParcelas");
				nrParcelas.appendChild(doc.createTextNode(cliente.getPagamento().getNrParcelas().toString()));
				pagamento.appendChild(nrParcelas);
				
				Element nomeTitular = doc.createElement("nomeTitular");
				nomeTitular.appendChild(doc.createTextNode(cliente.getPagamento().getNomeTitular()));
				pagamento.appendChild(nomeTitular);
				
				//Carrinho
				int i = 1;
				for (Produto p : cliente.getListaCarrinho()) {
					Element carrinho = doc.createElement("carrinho");
					client.appendChild(carrinho);
					carrinho.setAttribute("id", String.valueOf(i));
					
					Element idCliente = doc.createElement("idCliente");
					idCliente.appendChild(doc.createTextNode(cliente.getId().toString()));
					carrinho.appendChild(idCliente);
					
					Element idProduto = doc.createElement("idProduto");
					idProduto.appendChild(doc.createTextNode(p.getId().toString()));
					carrinho.appendChild(idProduto);
					
					i++;
				}
				
				tmp++;
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("/opt/kitchiqui/dates/kitchiqui-dates-01.xml"));
			
			// Output to console for testing
//			StreamResult result = new StreamResult(System.out);
			
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Captando as informacoes contidas no XML principal, controle indices:
	 * 
	 * 1 - Ultimo backUp gerado pelo sistema
	 * 2 - Penultimo backUp gerado;
	 * 3 - AntePenultimo backUp gerado;
	 * @return
	 */
	public static List<Cliente> leDadosCliente() {
		List<Cliente> lista = new ArrayList<>();
		try {
			File fXmlFile = new File("/opt/kitchiqui/dates/kitchiqui-dates-01.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
//			System.out.println("Raiz :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("cliente");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
//				System.out.println("\nItem :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("id: " + eElement.getAttribute("id"));
					System.out.println("Nome: " + eElement.getElementsByTagName("nome").item(0).getTextContent());
				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return lista;
	}
}
