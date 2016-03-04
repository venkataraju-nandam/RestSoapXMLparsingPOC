package org.camunda.poc.restsoap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.parser.JSONParser;

import com.sun.jersey.json.impl.provider.entity.JSONObjectProvider;

public class XmlParserFactory {
	static Logger LOGGER = LoggerFactory.getLogger(XmlParserFactory.class);
//	public static void main(String argv[]) {
//		// new XmlParserFactory().updateXml();
//		// new XmlParserFactory().getOrderXml();
////		new XmlParserFactory().updateOrderXml();
//		new XmlParserFactory().getJsonData();
//	}

	public void updateOrderXmlasNew(String inputXmlObj, String customerOrderNumber) {
		LOGGER.info("\n\n\n XmlParserFactory ....");
		try {
			String inputXml = null;
			if(inputXmlObj !=null){
				inputXml = inputXmlObj;
			}else{
				Utility utility = new Utility();
				inputXml = utility.getXml(utility.inputFile_createOrder);
			}
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(inputXml.getBytes("utf-8"))));
			printDoc(doc);

			// Get the root element
			Node parentNode = doc.getFirstChild();
			// loop the child nodes
			NodeList childNodes = parentNode.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node orderNode = childNodes.item(i);
				// append a new node
				Element parentCustomerOrderNumber = doc.createElement("bpm:ParentCustomerOrderNumber");
				parentCustomerOrderNumber.appendChild(doc.createTextNode(customerOrderNumber));
				orderNode.appendChild(parentCustomerOrderNumber);
			}

			LOGGER.info("\n\n\n");
//			printDoc(doc);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			LOGGER.info("Updated Doc :------------------------------------------ \n  " + doc);
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\order_my_1.xml"));
			transformer.transform(source, result);
			LOGGER.info("Done...");
		} catch (TransformerException tfe) {
			tfe.printStackTrace();

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public void updateOrderXml(String inputXmlObj) {
		LOGGER.info("\n\n\n XmlParserFactory ....");
		try {
			String inputXml = null;
			if(inputXmlObj !=null){
				inputXml = inputXmlObj;
			}else{
				Utility utility = new Utility();
				inputXml = utility.getXml(utility.inputFile_createOrder);
			}
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(inputXml.getBytes("utf-8"))));
			printDoc(doc);

			// Get the root element
			Node parentNode = doc.getFirstChild();
			// loop the child nodes
			NodeList childNodes = parentNode.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node orderNode = childNodes.item(i);
//				LOGGER.info("\n\n Child Node " + i + " : " + orderNode.getNodeName());
				// append a new node
				Element customerOrderNumber111 = doc.createElement("bpm:ParentOrderNo");
				customerOrderNumber111.appendChild(doc.createTextNode("55555"));
				orderNode.appendChild(customerOrderNumber111);
			}

			LOGGER.info("\n\n\n");
			printDoc(doc);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public void printDoc(Document doc){
		// Get the root element
		Node parentNode = doc.getFirstChild();
		LOGGER.info("Parent Node : " + parentNode.getNodeName());
		// loop the staff child node
		NodeList parentNodeList = parentNode.getChildNodes();
		for (int i = 0; i < parentNodeList.getLength(); i++) {
			Node orderNode = parentNodeList.item(i);
			LOGGER.info("Order Node " + i + " : " + orderNode.getNodeName());
			 NodeList list = orderNode.getChildNodes();
			 for (int j = 0; j < list.getLength(); j++) {
			 Node node = list.item(j);
			 LOGGER.info("Child Node " + j + " : " +node.getNodeName());
//			 	if(j>5) break;
			 }
		}
	}

	public void getOrderXml(String inputXmlObj) {
		LOGGER.info("\n\n\n XmlParserFactory ....");
		try {
			String inputXml = null;
			if(inputXmlObj !=null){
				inputXml = inputXmlObj;
			}else{
				Utility utility = new Utility();
				inputXml = utility.getXml(utility.inputFile_createOrder);
			}
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// Document doc = docBuilder.parse(inputXml);
			Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(inputXml.getBytes("utf-8"))));

			printDoc(doc);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getJsonData(String createOrderResponse){
		LOGGER.info("\n\n\n XmlParserFactory ....");
		String customerOrderNumber= null;
		JSONParser parser = new JSONParser();
	    String s = "[{\"RequestId\":\"\",\"CustomerOrderNumber\":\"11111\",\"ResponseCode\":null,\"OrderNumber\":\"111111\"}]";
	    try{
	    	 Object parseObj = null;
	    	if(createOrderResponse!=null){
	    		  parseObj = parser.parse(createOrderResponse);
	    	}else{
	    		LOGGER.info("createOrderResponse is null");
	    		parseObj = parser.parse(s);
	    	}

//	        JSONArray array = (JSONArray)parseObj;
	    	 JSONArray array = new JSONArray();
	    	 array.add(parseObj);
	        LOGGER.info("The 1st element of array");
//	        LOGGER.info(array.get(0));

	        for (int i = 0; i < array.size(); i++) {
	            JSONObject jsonObjectRow = (JSONObject) array.get(i);
	            customerOrderNumber = (String) jsonObjectRow.get("CustomerOrderNumber");
	            String responseCode = (String) jsonObjectRow.get("ResponseCode");
	            LOGGER.info(customerOrderNumber);
	            LOGGER.info(responseCode);
	            break;
	        }
	     }catch(ParseException pe){
	        LOGGER.info("position: " + pe.getPosition());
//	        LOGGER.info(pe);
	     }
	    return customerOrderNumber;
	}

	public void getJsonExample(){
	JSONParser parser = new JSONParser();
    String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
    try{
       Object obj = parser.parse(s);
       JSONArray array = (JSONArray)obj;
       LOGGER.info("The 2nd element of array");
//       LOGGER.info(array.get(1));
//       LOGGER.info();

       JSONObject obj2 = (JSONObject)array.get(1);
       LOGGER.info("Field \"1\"");
//       LOGGER.info(obj2.get("1"));

       s = "{}";
       obj = parser.parse(s);
//       LOGGER.info(obj);

       s = "[5,]";
       obj = parser.parse(s);
//       LOGGER.info(obj);

       s = "[5,,2]";
       obj = parser.parse(s);
//       LOGGER.info(obj);
    }catch(ParseException pe){
       LOGGER.info("position: " + pe.getPosition());
//       LOGGER.info(pe);
    }
    }
}
