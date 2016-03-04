package org.camunda.poc.restsoap;

import java.util.Properties;
import javax.jms.*;
import javax.naming.*;
import org.apache.activemq.transport.TransportListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.camunda.bpm.att.timer.serv.OrderVo;

public class OceConsumer {
	static Logger LOGGER = LoggerFactory.getLogger(OceConsumer.class);
	// cd D:\apache-activemq-510\bin
	// activemq start

	private String queueName = "myqueue";
	private String user = "admin";
	private String password = "admin";
//	private String url = "tcp://localhost:61616";
//	private String url = "tcp://hostname:61616";  //desktop
	private String url = "tcp://10.81.160.104:61616";  //laptop
	private boolean transacted = false;
	private boolean isRunning = false;

	private String oceURL = "tcp://135.213.34.143:8500";
	private String oceQueueName = "JNDI_QUEUE_AMQ";
	private String oceUser = "admin";
	private String ocePassword = "password";

	public OceConsumer()
	{
		LOGGER.info("Queue Initiated ...");
	}

	public String run() throws NamingException, JMSException
	{
		System.getProperties().put("http.proxyHost", "135.28.13.11");
        System.getProperties().put("http.proxyPort", "8080");
        System.getProperties().put("http.proxySet", "true");

		isRunning = true;
		javax.naming.Context ctx = getConnectionContext();
		ConnectionFactory connectionFactory = (ConnectionFactory)ctx.lookup("ConnectionFactory");
		Connection connection = connectionFactory.createConnection(user, password);
		connection.start();

		Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
		Destination destination = (Destination)ctx.lookup("slQueue");
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = null;
		TextMessage txtMsg = null;

		while (isRunning)
		{
			LOGGER.info("Waiting for message...");
			message = consumer.receive();
			if (message != null && message instanceof TextMessage) {
				txtMsg = (TextMessage)message;
				LOGGER.info("Received: " + txtMsg.getText());
				isRunning = false;
			}
		}
		LOGGER.info("Closing connection");
		consumer.close();
		session.close();
		connection.close();
		return txtMsg.getText();
	}

	public Context getConnectionContext() throws NamingException, JMSException
	{
		//JNDI properties
		Properties props = getQueueConnectionProperties();
		Context ctx = new InitialContext(props);
		return ctx;
	}
	public Properties getQueueConnectionProperties() throws NamingException, JMSException
	{
		//JNDI properties
		Properties props = new Properties();
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.setProperty(Context.PROVIDER_URL, url);
		//specify queue propertyname as queue.jndiname
		props.setProperty("queue.slQueue", queueName);
		return props;
	}

}
