package org.camunda.poc.restsoap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

public class Utility {
	static Logger LOGGER = LoggerFactory.getLogger(Utility.class);
	public Utility() {
	}

	String config_properties = "D:/marsWS1/att-order-process/src/main/resources/config_url.properties";
	String inputFile_bulkUpdate = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\BulkOrderReq.xml";
	String inputFile_createOrder = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\order.xml";
//	String inputFile_createOrder = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\order_new.xml";
	String inputFile_ValidateAddress = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\ValidateAddressReq.xml";
	String inputFile_UpdateAccountProfile = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\UpdateAccountProfile.xml";

	public Properties loadProps() {
		// LOGGER.info(System.getProperty("user.dir"));
		// ClassLoader classLoader = getClass().getClassLoader();
		// File file = new
		// File(classLoader.getResource("config_url.properties").getFile());
		// LOGGER.info(file.getPath());
		// LOGGER.info(file.getAbsolutePath());
		Properties prop = null;
		InputStream inputProp = null;
		FileInputStream input = null;
		prop = new Properties();
		try {
			LOGGER.info("Loading URLs from Configuration");
			try {
				inputProp = new FileInputStream(
						this.getClass().getClassLoader().getResourceAsStream("config_url.properties").toString());
				LOGGER.info("initialize.....   getResourceAsStream config_url.properties.............");
			} catch (java.io.FileNotFoundException fe) {
				// LOGGER.info("\n FileNotFound : " + fe.getMessage());
				try {
					inputProp = new FileInputStream(config_properties);
					// inputProp = new
					// FileInputStream("D:/marsWS1/att-order-process/src/main/resources/config_url.properties");
				} catch (java.io.FileNotFoundException fe1) {
					LOGGER.info("\n Error: " + fe.getMessage());
				}
			}
			prop.load(inputProp);
			/* Set Proxy URL */
			System.setProperty("proxySet", prop.getProperty("proxySet"));
			System.setProperty("http.proxyHost", prop.getProperty("http.proxyHost"));
			System.setProperty("http.proxyPort", prop.getProperty("http.proxyPort"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getXml(String inputFileName) {
		BufferedReader br;
		StringBuilder sb = null;
		try {
			br = new BufferedReader(new FileReader(new File(inputFileName)));
			String line;
			sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		LOGGER.info("\n\n\n" + sb.toString() + "\n\n\n");
//		LOGGER.info("\n\n *****Order XML parsing is Successful***** \n\n");
		// String xmlString = sb.toString().replaceAll("\\u0020","");
		//// sb.toString().replaceAll("(</[^>]+>)\\s+(<[^>]+>)","$1\n$2");
		// return xmlString.toString();
		return sb.toString();
	}
}
