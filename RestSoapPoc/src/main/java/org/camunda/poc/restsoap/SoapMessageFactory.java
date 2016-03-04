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

public class SoapMessageFactory {
	static Logger LOGGER = LoggerFactory.getLogger(SoapMessageFactory.class);
	public SoapMessageFactory() {
	}

	/**
	 * Method to create the SOAP Conn, Req, Response
	 */
	public String getSoapMessage(Properties prop, String inputFile, String url) {
		String response = "";
		try {
			LOGGER.info("ValidateAddressService : 3" + url);
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			FileInputStream inputStream = new FileInputStream(new File(inputFile));
			/* Process the SOAP Request */
			SOAPMessage soapMessage = createSOAPRequest(inputStream);
			SOAPMessage soapResponse = soapConnection.call(soapMessage, url);
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

	/**
	 * Method to create the SOAP Request
	 */
	private SOAPMessage createSOAPRequest(FileInputStream request) {
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
		LOGGER.info("ValidateAddressService : 4");
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
