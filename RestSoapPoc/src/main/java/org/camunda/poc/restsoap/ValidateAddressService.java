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

public class ValidateAddressService {
	static Logger LOGGER = LoggerFactory.getLogger(ValidateAddressService.class);
	public ValidateAddressService(){
	}

	public String serviceInvocation() {
		LOGGER.info("ValidateAddressService : 1");
		Utility utility = new Utility();
		Properties props = utility.loadProps();
		String inputFile = utility.inputFile_ValidateAddress;
		String url = props.getProperty("url.validateaddress");

		String response = new SoapMessageFactory().getSoapMessage(props, inputFile, url);
		LOGGER.info("Response :" + response);
		return response;
	}
}
