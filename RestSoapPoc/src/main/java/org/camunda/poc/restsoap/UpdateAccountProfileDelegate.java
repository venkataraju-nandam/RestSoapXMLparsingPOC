package org.camunda.poc.restsoap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateAccountProfileDelegate implements JavaDelegate {
	static Logger LOGGER = LoggerFactory.getLogger(UpdateAccountProfileDelegate.class);
	public void execute(DelegateExecution execution) throws Exception {
	    LOGGER.info("\n\n\n Delegate: UpdateAccountProfileDelegate ....");
	    String updateAccProfileResp = new UpdateAccountProfileService().serviceInvocation();
//	    execution.setVariable("updateAccProfileResponse", updateAccProfileResp);
	    LOGGER.info("\n\n\n Delegate: UpdateAccountProfileDelegate ...."+updateAccProfileResp);
	    }
}
