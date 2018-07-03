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
