package br.com.kitchiqui.util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.kitchiqui.controller.ClienteMB;
import br.com.kitchiqui.controller.ProdutoMB;
import br.com.kitchiqui.modelo.Cliente;
import br.com.kitchiqui.modelo.CompraProduto;
import br.com.kitchiqui.modelo.Endereco;
import br.com.kitchiqui.modelo.Pagamento;
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
				
				if (Util.isEmpty(cliente.getNomeCompleto())) 
					cliente.setNomeCompleto("");
				
				Element nomeCompleto = doc.createElement("nomeCompleto");
				nomeCompleto.appendChild(doc.createTextNode(cliente.getNomeCompleto()));
				client.appendChild(nomeCompleto);
				
				if (Util.isEmpty(cliente.getNrCpf())) 
					cliente.setNrCpf("");
				
				Element nrCpf = doc.createElement("nrCpf");
				nrCpf.appendChild(doc.createTextNode(cliente.getNrCpf()));
				client.appendChild(nrCpf);
				
				if (Util.isEmpty(cliente.getNrRG())) 
					cliente.setNrRG("");
				
				Element nrRG = doc.createElement("nrRG");
				nrRG.appendChild(doc.createTextNode(cliente.getNrRG()));
				client.appendChild(nrRG);
				
				if (Util.isEmpty(cliente.getNrTelefone())) 
					cliente.setNrTelefone("");
				
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
				
				if (Util.isEmpty(cliente.getEndereco().getCep())) 
					cliente.getEndereco().setCep("");
				
				Element cep = doc.createElement("cep");
				cep.appendChild(doc.createTextNode(cliente.getEndereco().getCep()));
				endereco.appendChild(cep);
				
				if (Util.isEmpty(cliente.getEndereco().getNomeRua())) 
					cliente.getEndereco().setNomeRua("");
				
				Element nomeRua = doc.createElement("nomeRua");
				nomeRua.appendChild(doc.createTextNode(cliente.getEndereco().getNomeRua()));
				endereco.appendChild(nomeRua);
				
				if (Util.isEmpty(cliente.getEndereco().getNumero())) 
					cliente.getEndereco().setNumero("");
				
				Element numero = doc.createElement("numero");
				numero.appendChild(doc.createTextNode(cliente.getEndereco().getNumero()));
				endereco.appendChild(numero);
				
				if (Util.isEmpty(cliente.getEndereco().getComplemento())) 
					cliente.getEndereco().setComplemento("");
				
				Element complemento = doc.createElement("complemento");
				complemento.appendChild(doc.createTextNode(cliente.getEndereco().getComplemento()));
				endereco.appendChild(complemento);
				
				if (Util.isEmpty(cliente.getEndereco().getBairro())) 
					cliente.getEndereco().setBairro("");
				
				Element bairro = doc.createElement("bairro");
				bairro.appendChild(doc.createTextNode(cliente.getEndereco().getBairro()));
				endereco.appendChild(bairro);
				
				if (Util.isEmpty(cliente.getEndereco().getNomeCidade())) 
					cliente.getEndereco().setNomeCidade("");
				
				Element nomeCidade = doc.createElement("nomeCidade");
				nomeCidade.appendChild(doc.createTextNode(cliente.getEndereco().getNomeCidade()));
				endereco.appendChild(nomeCidade);
				
				if (Util.isEmpty(cliente.getEndereco().getEstado())) 
					cliente.getEndereco().setEstado("");
				
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
				
				if (Util.isEmpty(cliente.getPagamento().getNumeroCartao())) 
					cliente.getPagamento().setNumeroCartao("");
				
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
				
				if (Util.isEmpty(cliente.getPagamento().getNomeTitular())) 
					cliente.getPagamento().setNomeTitular("");
				
				Element nomeTitular = doc.createElement("nomeTitular");
				nomeTitular.appendChild(doc.createTextNode(cliente.getPagamento().getNomeTitular()));
				pagamento.appendChild(nomeTitular);
				
				//Carrinho
				int i = 1;
				for (Produto p : cliente.getListaCarrinho()) {
					Element carrinho = doc.createElement("carrinho");
					client.appendChild(carrinho);
					carrinho.setAttribute("id", String.valueOf(i));
					
					Element srcImagem = doc.createElement("srcImagem");
					srcImagem.appendChild(doc.createTextNode(p.getSrcImagem().toString()));
					carrinho.appendChild(srcImagem);
					
					Element compraProduto = doc.createElement("compraProduto");
					carrinho.appendChild(compraProduto);
					compraProduto.setAttribute("id", "1");
					
					Element codigoRastreio = doc.createElement("codigoRastreio");
					codigoRastreio.appendChild(doc.createTextNode(p.getCompraProduto().getCodigoRastreio().toString()));
					compraProduto.appendChild(codigoRastreio);
					
					Element dtCompra = doc.createElement("dtCompra");
					dtCompra.appendChild(doc.createTextNode(p.getCompraProduto().getDtCompra().toString()));
					compraProduto.appendChild(dtCompra);
					
					Element codCompra = doc.createElement("codCompra");
					codCompra.appendChild(doc.createTextNode(p.getCompraProduto().getCodCompra().toString()));
					compraProduto.appendChild(codCompra);
					
					Element statusCompra = doc.createElement("statusCompra");
					statusCompra.appendChild(doc.createTextNode(p.getCompraProduto().getStatusCompra()));
					compraProduto.appendChild(statusCompra);
					
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
	 * @throws java.text.ParseException 
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
			
			Endereco end = null;
			Pagamento pag = null;
			
			Produto prod = null;
			Produto tmpProd = null;
			Cliente cli = null;
			
			CompraProduto compProd = null;
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//			formato.setLenient(false);
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
//				System.out.println("\nItem :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					cli = new Cliente();
					
//					System.out.println("id: " + eElement.getAttribute("id"));
//					System.out.println("Nome: " + eElement.getElementsByTagName("nome").item(0).getTextContent());
					
//					cli.setId(UUID.fromString(eElement.getElementsByTagName("idtCliente").item(0).getTextContent()));
					cli.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
					cli.setNomeCompleto(eElement.getElementsByTagName("nomeCompleto").item(0).getTextContent());
					
					cli.setNrCpf(eElement.getElementsByTagName("nrCpf").item(0).getTextContent());
					cli.setNrRG(eElement.getElementsByTagName("nrRG").item(0).getTextContent());
					cli.setNrTelefone(eElement.getElementsByTagName("nrTelefone").item(0).getTextContent());
					
					cli.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
					cli.setSenha(eElement.getElementsByTagName("senha").item(0).getTextContent());
					cli.setDtNascimento(formato.parse(eElement.getElementsByTagName("dtNascimento").item(0).getTextContent()));
					
					cli.setTmpDtNascimento(eElement.getElementsByTagName("tmpDtNascimento").item(0).getTextContent());
					
					NodeList nListEndereco = eElement.getElementsByTagName("endereco");
					
					for (int i = 0; i < nListEndereco.getLength(); i++) {
						Node nodeEnd = nListEndereco.item(i);
						if (nodeEnd.getNodeType() == Node.ELEMENT_NODE) {

							Element elEnd = (Element) nodeEnd;
							end = new Endereco();
							
//							end.setId(UUID.fromString(elEnd.getElementsByTagName("idtEndereco").item(0).getTextContent()));
							end.setModoEnvio(Integer.parseInt(elEnd.getElementsByTagName("modoEnvio").item(0).getTextContent()));
							end.setPrecoModoEnvio(Double.parseDouble(elEnd.getElementsByTagName("precoModoEnvio").item(0).getTextContent()));
							
							end.setCep(elEnd.getElementsByTagName("cep").item(0).getTextContent());
							end.setNomeRua(elEnd.getElementsByTagName("nomeRua").item(0).getTextContent());
							end.setNumero(elEnd.getElementsByTagName("numero").item(0).getTextContent());
							
							end.setComplemento(elEnd.getElementsByTagName("complemento").item(0).getTextContent());
							end.setBairro(elEnd.getElementsByTagName("bairro").item(0).getTextContent());
							end.setNomeCidade(elEnd.getElementsByTagName("nomeCidade").item(0).getTextContent());
							
							end.setEstado(elEnd.getElementsByTagName("estado").item(0).getTextContent());
							cli.setEndereco(end);
						}
					}
					
					NodeList nListPagamento = eElement.getElementsByTagName("pagamento");
					
					for (int i = 0; i < nListPagamento.getLength(); i++) {
						Node nodePag = nListPagamento.item(i);
						if (nodePag.getNodeType() == Node.ELEMENT_NODE) {
							Element elPag = (Element) nodePag;
							pag = new Pagamento();
							
//							pag.setId(UUID.fromString(elPag.getElementsByTagName("idtPagamento").item(0).getTextContent()));
							pag.setTipoPagamento(Integer.parseInt(elPag.getElementsByTagName("tipoPagamento").item(0).getTextContent()));
							pag.setNumeroCartao(elPag.getElementsByTagName("numeroCartao").item(0).getTextContent());
							
							pag.setMesValidadeCartao(Integer.parseInt(elPag.getElementsByTagName("mesValidadeCartao").item(0).getTextContent()));
							pag.setAnoValidadeCartao(Integer.parseInt(elPag.getElementsByTagName("anoValidadeCartao").item(0).getTextContent()));
							pag.setCodigoSeguranca(Integer.parseInt(elPag.getElementsByTagName("codigoSeguranca").item(0).getTextContent()));
							
							pag.setNrParcelas(Integer.parseInt(elPag.getElementsByTagName("nrParcelas").item(0).getTextContent()));
							pag.setNomeTitular(elPag.getElementsByTagName("nomeTitular").item(0).getTextContent());
							cli.setPagamento(pag);
						}
					}
					// persistir...
					ClienteMB.gravarClienteBackUp(cli);
					
					NodeList nListCarrinho = eElement.getElementsByTagName("carrinho");
					for (int i = 0; i < nListCarrinho.getLength(); i++) {
						Node nodeProd = nListCarrinho.item(i);
						if (nodeProd.getNodeType() == Node.ELEMENT_NODE) {
							Element elProd = (Element) nodeProd;
							tmpProd = new Produto();
							
//							prod.setId(UUID.fromString(elProd.getElementsByTagName("idProduto").item(0).getTextContent()));
							tmpProd.setSrcImagem(elProd.getElementsByTagName("srcImagem").item(0).getTextContent());
							prod = ProdutoMB.pesquisaBackUp(tmpProd);
							
							NodeList nListCompra = elProd.getElementsByTagName("compraProduto");
							for (int j = 0; j < nListCompra.getLength(); j++) {
								Node nodeCompProd = nListCompra.item(j);
								if (nodeCompProd.getNodeType() == Node.ELEMENT_NODE) {
									Element elCompProd = (Element) nodeCompProd;
									compProd = new CompraProduto();
									
//									compProd.setId(UUID.fromString(elCompProd.getElementsByTagName("idtCompraProduto").item(0).getTextContent()));
									compProd.setStatusCompra(elCompProd.getElementsByTagName("statusCompra").item(0).getTextContent());
									compProd.setCodigoRastreio(elCompProd.getElementsByTagName("codigoRastreio").item(0).getTextContent());
									
									compProd.setCodCompra(Integer.parseInt(elCompProd.getElementsByTagName("codCompra").item(0).getTextContent()));
									compProd.setStatusCompra(elCompProd.getElementsByTagName("statusCompra").item(0).getTextContent());
									compProd.setDtCompra(formato.parse(elCompProd.getElementsByTagName("dtCompra").item(0).getTextContent()));
									
									prod.setCompraProduto(compProd);
								}
							}
							cli.getListaCarrinho().add(prod);
						}
					}
					// atualizando
					ClienteMB.atualizarClienteBackUp(cli);
				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return lista;
	}
}
