package org.camunda.poc.restsoap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateOrderDelegate implements JavaDelegate {
	static Logger LOGGER = LoggerFactory.getLogger(ValidateOrderDelegate.class);
	public void execute(DelegateExecution execution) throws Exception {
//	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ...."+execution.getVariable("customerOrderNumber"));
//	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ...."+execution.getVariable("requestId"));
	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ....\n"+execution.getVariable("outputCO"));
	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ....\n\n"+execution.getVariable("orderresponse"));
	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ...."+execution.getVariable("customerOrderNumber"));
//	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ...."+execution.getVariable("requestId"));
	    LOGGER.info("\n\n\n Delegate: ValidateOrderDelegate ...."+execution.getVariable("OrderNumber"));
	}
}
