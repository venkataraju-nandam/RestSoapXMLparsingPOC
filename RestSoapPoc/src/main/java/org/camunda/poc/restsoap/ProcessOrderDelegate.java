package org.camunda.poc.restsoap;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessOrderDelegate implements JavaDelegate {
	static Logger LOGGER = LoggerFactory.getLogger(ProcessOrderDelegate.class);
	public void execute(DelegateExecution execution) throws Exception {
//		LOGGER.info("\n\n\n (ProcessOrderDelegate)...RAISING BPMN EXCEPTION ........................");
//		 throw new BpmnError("My BPMN Error");

//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getVariable("response"));
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getVariableNames());
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getBpmnModelElementInstance());
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getBpmnModelInstance());
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getProcessEngineServices().getTaskService());
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getProcessEngineServices());
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getVariable("ATGResponse"));
//	    LOGGER.info("Delegate: Validate Address Call Processing ...."+execution.getVariable("ATGResponse").toString());

	    LOGGER.info("\n\n\n Delegate: ProcessOrderDelegate ...."+execution.getVariable("orderstr"));
	    LOGGER.info("\n\n\n Delegate: ProcessOrderDelegate ...."+execution.getVariable("orderresponse"));
	    LOGGER.info("\n\n\n Delegate: ProcessOrderDelegate ...."+execution.getVariable("inputCOxQ"));
//	    LOGGER.info("\n\n\n Delegate: Validate Address Call Processing ...."+execution.getVariable("orderresponse"));


	    //Implementation goes here
//	    new ExternalServiceInvoker().serviceInvocation_ValidateAddress();

	}
}
