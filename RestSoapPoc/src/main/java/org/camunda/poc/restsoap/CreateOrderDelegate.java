package org.camunda.poc.restsoap;

import java.util.Properties;

import javax.jms.JMSException;
import javax.naming.NamingException;

//import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;

public class CreateOrderDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
//		String createOrderResp = null;
//	    LOGGER.info("\n\n\n Delegate: CreateOrderDelegate ...."+execution.getVariable("inputCO"));
//	    try{
//	    	createOrderResp = new CreateOrderService().serviceInvocation();
//	    } catch (java.net.ConnectException ce) {
//	    	LOGGER.info("*****Unknown Error ***** :"+ ce.getMessage());
//		}
//		Utility utility = new Utility();
//		Properties props = utility.loadProps();
//		String inputFile = utility.inputFile_createOrder;
//		String inputXml = utility.getXml(inputFile);
////		LOGGER.info("\n\n\n Delegate: CreateOrderDelegate ....INPUT : \n"+inputXml);
//		LOGGER.info("\n\n\n Delegate: CreateOrderDelegate ....INPUT done: \n");
//
//		XmlParserFactory parser = new XmlParserFactory();
//		parser.getOrderXml(inputXml);
//		String customerOrderNumber = parser.getJsonData(createOrderResp);
//		parser.updateOrderXmlasNew(inputXml,customerOrderNumber);

//	    String inputXml = null;
//		try {
//			inputXml = new OceConsumer().run();
//		} catch (NamingException e) {
//			LOGGER.info("\n\n\n*****Naming Exception !!! *****");
//			e.printStackTrace();
//		} catch (JMSException e) {
//			LOGGER.info("\n\n\n*****JMS Exception !!! *****");
//			e.printStackTrace();
//		}

//	    execution.setVariable("orderrequest", inputXml);
//	    execution.setVariable("orderresponse", createOrderResp);
	}


}
