package org.camunda.poc.restsoap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalServicesDelegate implements JavaDelegate {
	static Logger LOGGER = LoggerFactory.getLogger(ExternalServicesDelegate.class);
	public void execute(DelegateExecution execution) throws Exception {
	    LOGGER.info("Delegate: Validate Address Call Processing ");
	    //Implementation goes here
	    new ExternalServiceInvoker().serviceInvocation_ValidateAddress();
	}
}

