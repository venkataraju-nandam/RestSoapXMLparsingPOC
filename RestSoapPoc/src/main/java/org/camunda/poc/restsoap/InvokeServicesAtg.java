package org.camunda.poc.restsoap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InvokeServicesAtg {
	static Logger LOGGER = LoggerFactory.getLogger(InvokeServicesAtg.class);
	public InvokeServicesAtg(){
	}

	public String createOrder() {
		Utility utility = new Utility();
		Properties props = utility.loadProps();
		String inputFile = utility.inputFile_createOrder;

		String output = null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(props.getProperty("url.createorder"));
			ClientResponse response = webResource.type("application/xml").put(ClientResponse.class,
					getXml(inputFile));
			if (response.getStatus() == 200) {
				LOGGER.info("*****Response is Success***** :  "+ response.getStatus());
			}else if(response.getStatus() == 400){
				LOGGER.info("****Corrupted Request Object/XML***** : "+response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else if(response.getStatus() == 503){
				LOGGER.info("*****Service might be down***** :"+ response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else {
				LOGGER.info("*****Unknown Error ***** :"+ response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}
			output = response.getEntity(String.class);
//			client.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	private String getOrderFromAtgService() {
		Utility utility = new Utility();
		Properties props = utility.loadProps();

		String output = null;
		try {
			Client client = Client.create();
			WebResource webResource = null;
			LOGGER.info("Hitting URL:  .......................\n\n");
			if (props.getProperty("url.createorder") != null) {
				webResource = client.resource(props.getProperty("url.getorder"));
			} else {
				webResource = client.resource("http://host:port/orders/11111");
			}
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			if (response.getStatus() == 200) {
				LOGGER.info("*****Response is Success***** :  "+ response.getStatus());
			}else if(response.getStatus() == 400){
				LOGGER.info("****Corrupted Request Object/XML***** : "+response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else if(response.getStatus() == 503){
				LOGGER.info("*****Service might be down***** :"+ response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else {
				LOGGER.info("*****Unknown Error ***** :"+ response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}
			output = response.getEntity(String.class);
//			client.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public String bulkUpdateUserRequest() {
		Utility utility = new Utility();
		Properties props = utility.loadProps();
		String inputFile = utility.inputFile_bulkUpdate;

		String output = null;
		try {
			Client client = Client.create();
			WebResource webResource = client.resource(props.getProperty("url.bulkorderupdate"));
			ClientResponse response = webResource.type("application/xml").put(ClientResponse.class,
					getXml(inputFile));
			if (response.getStatus() == 200) {
				LOGGER.info("*****Response is Success***** :  "+ response.getStatus());
			}else if(response.getStatus() == 400){
				LOGGER.info("****Corrupted Request Object/XML***** : "+response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else if(response.getStatus() == 503){
				LOGGER.info("*****Service might be down***** :"+ response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else {
				LOGGER.info("*****Unknown Error ***** :"+ response.getStatus());
				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}
			output = response.getEntity(String.class);
//			client.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
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
		LOGGER.info("\n\n\n" + sb.toString() + "\n\n\n");
		// String xmlString = sb.toString().replaceAll("\\u0020","");
		//// sb.toString().replaceAll("(</[^>]+>)\\s+(<[^>]+>)","$1\n$2");
		// return xmlString.toString();
		return sb.toString();
	}
//	public void buildErrorResponse(ClientResponse response){
//	if (response.getStatus() == 200) {
//		LOGGER.info("*****Response is Success***** :  "+ response.getStatus());
//	}else if(response.getStatus() == 400){
//		LOGGER.info("****Corrupted Request Object/XML***** : "+response.getStatus());
//		throw new RuntimeException("Failed : HTTP error code : "+ response);
//	}else if(response.getStatus() == 503){
//		LOGGER.info("*****Service might be down***** :"+ response.getStatus());
//		throw new RuntimeException("Failed : HTTP error code : "+ response);
//	}else {
//		LOGGER.info("*****Unknown Error ***** :"+ response.getStatus());
//		throw new RuntimeException("Failed : HTTP error code : "+ response);
//	}
//		return;
//	}
}
