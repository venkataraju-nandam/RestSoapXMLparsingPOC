package org.camunda.poc.restsoap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalServiceInvoker {
	static Logger LOGGER = LoggerFactory.getLogger(ExternalServiceInvoker.class);
	static String inputFile = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\ValidateAddressReq.xml";
	static String outputFile = "D:\\marsWS1\\att-order-process\\src\\main\resources\\ValidateAddressRes.xml";
	static String inputFile_ValidateAddress = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\ValidateAddressReq.xml";
	static String outputFile_ValidateAddress = "D:\\marsWS1\\att-order-process\\src\\main\resources\\ValidateAddressRes.xml";

	public void serviceInvocation_ValidateAddress() {
		LOGGER.info("ExternalServiceInvoker : 1");
		Utility utility = new Utility();
		Properties props = utility.loadProps();
		String inputFile = utility.inputFile_ValidateAddress;
		String response = invokeService(props, inputFile);
		LOGGER.info("Response :" + response);
	}

	private String invokeService(Properties prop, String inputFile) {
		String response = "";
		try {
			System.getProperties().put("http.proxyHost", "135.28.13.11");
	        System.getProperties().put("http.proxyPort", "8080");
	        System.getProperties().put("http.proxySet", "true");
			LOGGER.info("ExternalServiceInvoker : 3" + prop.getProperty("url.validateaddress"));
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			FileInputStream inputStream = new FileInputStream(new File(inputFile));
			SOAPMessage soapMessage = createSOAPRequest(inputStream);
			SOAPMessage soapResponse = soapConnection.call(soapMessage, prop.getProperty("url.validateaddress"));
			/* Process the SOAP Response */
			response = printSOAPResponse(soapResponse);
			soapConnection.close();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}
		return response;
	}

	private SOAPMessage createSOAPRequest(FileInputStream message) throws Exception {
		SOAPMessage soapMessage = getSoapMessage(message);
		return soapMessage;
	}

	private SOAPMessage getSoapMessage(FileInputStream request) {

		SOAPMessage message = null;
		MessageFactory factory;
		MimeHeaders headers = new MimeHeaders();

		headers.addHeader("SOAPAction", "");
		headers.addHeader("Content-Type", "text/xml; charset=UTF-8");

		try {
			factory = MessageFactory.newInstance();
			// FileInputStream file = new FileInputStream(new File(inputFile));
			message = factory.createMessage(headers, request);

		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private String printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		LOGGER.info("ExternalServiceInvoker : 4");
		StringBuffer sb;
		String finalstring;
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		// System.out.print("\n Response SOAP Message = ");
		// StreamResult result = new StreamResult(System.out);
		// FileOutputStream output = new FileOutputStream(new File(outputFile));
		StringWriter outWriter = new StringWriter();
		StreamResult result = new StreamResult(outWriter);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(sourceContent, result);
		sb = outWriter.getBuffer();
		finalstring = sb.toString();
		return finalstring;
	}

}
