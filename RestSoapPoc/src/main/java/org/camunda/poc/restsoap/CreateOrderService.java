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

public class CreateOrderService {
	static Logger LOGGER = LoggerFactory.getLogger(CreateOrderService.class);
	public CreateOrderService(){
	}

	public String serviceInvocation() throws java.net.ConnectException {
		Utility utility = new Utility();
		Properties props = utility.loadProps();
		String inputFile = utility.inputFile_createOrder;
		String url = props.getProperty("url.createorder");

		String output = null;
		try {
			String inputXml = utility.getXml(inputFile);
			Client client = Client.create();
//			Client client = getClient();
			WebResource webResource = client.resource(url);
			ClientResponse response = webResource.type("application/xml").put(ClientResponse.class,inputXml);
			LOGGER.info("*****RESPONSE ***** :"+ response);
			if (response.getStatus() == 200) {
				LOGGER.info("*****Response is Success***** :  "+ response.getStatus());
			}else if(response.getStatus() == 400){
				LOGGER.info("****Corrupted Request Object/XML*****OR***** CustomerOrderNumber is  already exists : "+response.getStatus());
//				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else if(response.getStatus() == 503){
				LOGGER.info("*****Service might be down***** :"+ response.getStatus());
//				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}else {
				LOGGER.info("*****Unknown Error ***** :"+ response.getStatus());
//				throw new RuntimeException("Failed : HTTP error code : "+ response);
			}
			output = response.getEntity(String.class);
			LOGGER.info("*****RESPONSE ***** :"+ output);
//			client.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

//	public Client getClient(){
//		Client client = Client.create();
//		return client;
//	}

}
