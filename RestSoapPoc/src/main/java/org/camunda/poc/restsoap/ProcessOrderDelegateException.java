package org.camunda.poc.restsoap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessOrderDelegateException implements JavaDelegate {
	static Logger LOGGER = LoggerFactory.getLogger(ProcessOrderDelegateException.class);
	public void execute(DelegateExecution execution) throws Exception {
//		throw new BpmnError("My BPMN Error");    // Use this where you want to raise exception
		LOGGER.info("\n\n\n BPMN Exception Caught (ProcessOrderDelegateException):  ....ALTERNATIVE FLOW FOR ERROR IN PROCESS (Handle Error).........");

	}
}
