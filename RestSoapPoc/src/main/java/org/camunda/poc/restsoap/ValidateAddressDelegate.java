package org.camunda.poc.restsoap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateAddressDelegate implements JavaDelegate {
	static Logger LOGGER = LoggerFactory.getLogger(ValidateAddressDelegate.class);
	public void execute(DelegateExecution execution) throws Exception {
	    LOGGER.info("\n\n\n Delegate: ValidateAddressDelegate ....\n\n"+execution.getVariable("orderresponse"));
	    String validateAddressResp = new ValidateAddressService().serviceInvocation();
//	    execution.setVariable("validateAddrResponse", validateAddressResp);
	    LOGGER.info("\n\n\n Delegate: ValidateAddressDelegate ...."+execution.getVariable("CustomerOrderNumber"));
	    LOGGER.info("\n\n\n Delegate: ValidateAddressDelegate ...."+execution.getVariable("OrderNumber"));
	    LOGGER.info("\n\n\n Delegate: ValidateAddressDelegate ...."+execution.getVariable("inputValidateOrd"));

	    }
}
