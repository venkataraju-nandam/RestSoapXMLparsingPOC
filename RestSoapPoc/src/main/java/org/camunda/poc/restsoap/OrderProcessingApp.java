package org.camunda.poc.restsoap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.ibatis.session.SqlSession;
import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@ProcessApplication("OrderProcessingApplication")
public class OrderProcessingApp extends ServletProcessApplication {
	static Logger LOGGER = LoggerFactory.getLogger(OrderProcessingApp.class);
	@Inject
	private RuntimeService runtimeService;
	String inputFile_bulkUpdate = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\BulkOrderReq.xml";
	String inputFile_createOrder = "D:\\marsWS1\\att-order-process\\src\\main\\resources\\order.xml";

	@PostDeploy
	public void startProcessInstance(ProcessEngine processEngine) {
		LOGGER.info("\n\n\n*****processname process started !!! *****");
//		createOrderUpd(processEngine);
		invokeCreateOrderService(processEngine);
	}

	public void invokeCreateOrderService(ProcessEngine processEngine) {
//		String restServiceOutput = new InvokeServicesAtg().createOrder();
//		String restServiceOutput = new CreateOrderService().serviceInvocation();
		Map<String, Object> variables1 = new HashMap<String, Object>();
		variables1.put("orderresponse", "InitializingVariablesAtProcessStart");
//		variables1.put("orderresponse", restServiceOutput);
		// start a new instance of process
		processEngine.getRuntimeService().startProcessInstanceByKey("processname", variables1);
//		processEngine.getRuntimeService().startProcessInstanceByKey("OrderProc-Error", variables1);
	}



private void createOrderUpd(ProcessEngine processEngine){
	 ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) (processEngine).getProcessEngineConfiguration();
//	 SqlSession session = processEngineConfiguration.getDbSqlSessionFactory().getSqlSessionFactory().openSession();   //worked for tomcat
	 SqlSession session =  processEngineConfiguration.getSqlSessionFactory().openSession();
	    Connection connection = session.getConnection();
	    Statement jdbcStatement = null;
		try {
			jdbcStatement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    try {
	    	jdbcStatement.execute("ALTER TABLE ACT_RU_VARIABLE ALTER TEXT_ CLOB");
//	    	LOGGER.info("\n*****Table Altered !!! *****");
	    	jdbcStatement.execute("ALTER TABLE ACT_HI_VARINST ALTER TEXT_ CLOB");
//	    	LOGGER.info("\n*****Table Altered !!! *****");
	    	jdbcStatement.execute("ALTER TABLE ACT_HI_DETAIL ALTER TEXT_ CLOB");
//	    	LOGGER.info("\n*****Table Altered !!! *****");
		    jdbcStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

	/* My OLd Stuff-------------------------------------------------------------
	  @PostDeploy
	  public void startProcessInstance(ProcessEngine processEngine) {
		  LOGGER.info("processname process started !!! ");
			Properties prop = intialize();
//			invokeCreateOrderS(processEngine,prop);
			invokeCreateOrderService(processEngine);
//			invokeBulkUpdateUserRequest(processEngine);
	  }

	 public void invokeCreateOrder(ProcessEngine processEngine, Properties props){
		    String restServiceOutput = createOrder(processEngine,props);
//		    String restServiceOutput = bulkUpdateUserRequest(processEngine,props);
//		    String restServiceOutput = getOrderFromExternalService(processEngine,props);
		    Map<String, Object> variables1 = new HashMap<String, Object>();
		    variables1.put("orderstr", "<CustomerOrderNumber>VENKAT-032</CustomerOrderNumber>");
//		    variables1.put("orderresponse",restServiceOutput);
//		    variables1.put("orderresponse","<CustomerOrderNumber>715127 VenkataRaju </CustomerOrderNumber>");
//		    LOGGER.info("Response from Service : \n\n\n"+ restServiceOutput);

			// start a new instance of our process
		    processEngine.getRuntimeService().startProcessInstanceByKey("processname", variables1);
	 }

	 private String getOrderFromExternalService(ProcessEngine processEngine, Properties props){
		 String output=null;
			try {
				Client client = Client.create();
				WebResource webResource = null;
//				WebResource webResource = client.resource("http://localhost:8080/Orders/rest/orderservice/getOrderList");
				LOGGER.info("Hitting URL:  .......................\n\n");
				if(props.getProperty("url.createorder")!=null){
					webResource = client.resource(props.getProperty("url.createorder"));
				}else{
					webResource = client.resource("URL");
				}
				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
				LOGGER.info("Wow!!! You got a response");
				LOGGER.info("Response ::: "+response.getStatus());
				LOGGER.info("Response ::: "+response.getLength());
				output = response.getEntity(String.class);
				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
				}
			  } catch (Exception e) {
				e.printStackTrace();
			  }
//			LOGGER.info("\n \n \n Response ::: "+output);
			return output;
	  }

	  public String createOrder(ProcessEngine processEngine, Properties props){
		  String output = null;
		  try {
		    Client client = Client.create();
			WebResource webResource = client.resource(props.getProperty("url.createorder"));
//			ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, bulkUpdVo);
//			ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, getXml(props.getProperty("inputFile_createOrder")));
			// PUT OR POST ??????????
			ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, getXml(inputFile_createOrder));
			LOGGER.info("Got Response !!! "+response);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}
			output = response.getEntity(String.class);
			LOGGER.info(output);
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		  LOGGER.info("Output from Server .... \n"+output);
		  return output;
}

		private Properties intialize(){
			Properties prop = null;
			InputStream inputProp = null;
			FileInputStream input = null;
			prop = new Properties();
			try {
				LOGGER.info("Loading URLs from Configuration");
				try{
				inputProp = new FileInputStream(this.getClass().getClassLoader().getResourceAsStream("config_url.properties").toString());
				LOGGER.info("initialize.....    config_url.properties.............");
				}catch(java.io.FileNotFoundException fe){
					LOGGER.info("\n FileNotFound : "+fe.getMessage());
					try{
					inputProp = new FileInputStream("D:/marsWS1/att-order-process/src/main/resources/config_url.properties");
					}catch(java.io.FileNotFoundException fe1){
						LOGGER.info("\n Error: "+fe.getMessage());
					}
				}
				prop.load(inputProp);
				System.setProperty("proxySet", prop.getProperty("proxySet"));
				System.setProperty("http.proxyHost", prop.getProperty("http.proxyHost"));
				System.setProperty("http.proxyPort", prop.getProperty("http.proxyPort"));
			}catch(IOException e){
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
			return prop;
		}

		public String getXml(String inputFileName){
			BufferedReader br;
			StringBuilder sb=null;
			try {
				br = new BufferedReader(new FileReader(new File(inputFileName)));
				String line;
				sb = new StringBuilder();
				while((line=br.readLine())!= null){
				sb.append(line.trim());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			LOGGER.info("\n\n\n"+sb.toString()+"\n\n\n");
////			sb.replaceAll("\\u0020","");
//			String xmlString = sb.toString().replaceAll("\\u0020","");
////			String xmlString = sb.toString().replaceAll("(</[^>]+>)\\s+(<[^>]+>)","$1\n$2");
//			LOGGER.info("\n\n\n"+xmlString.toString()+"\n\n\n");
//			return xmlString.toString();
			return sb.toString();
		}

		  public String bulkUpdateUserRequest(ProcessEngine processEngine, Properties props){
			  String output = null;
			  try {
			    Client client = Client.create();
				WebResource webResource = client.resource(props.getProperty("url.bulkorderupdate"));
				// PUT OR POST ??????????
//				ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, bulkUpdVo);
//				ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, getXml(props.getProperty("inputFile_bulkUpdate")));
				ClientResponse response = webResource.type("application/xml").put(ClientResponse.class, getXml(inputFile_bulkUpdate));
				LOGGER.info("Got Response !!! "+response);

				if (response.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
					     + response.getStatus());
				}
				output = response.getEntity(String.class);
				LOGGER.info(output);
			  } catch (Exception e) {
				e.printStackTrace();
			  }
			  LOGGER.info("Output from Server .... \n"+output);
			  return output;
	  }



My OLd Stuff------------------------------------------------------------- */

}
