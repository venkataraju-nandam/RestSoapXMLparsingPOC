package org.camunda.bpm.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

public class ProcessDishRulesDelegate implements JavaDelegate {

	 static Logger LOGGER = LoggerFactory.getLogger(ProcessDishRulesDelegate.class);

	public void execute(DelegateExecution execution) throws Exception {

		LOGGER.info(" : ProcessDishRulesDelegate Initiated ..... \n\n");
		DecisionService decisionService = execution.getProcessEngineServices().getDecisionService();
		VariableMap variables = Variables.createVariables().putValue("season", "Fall");
		DmnDecisionTableResult decisionResult = decisionService.evaluateDecisionTableByKey("dish", variables);

		Map<String, Object> mapResult = decisionResult.getSingleResult().getEntryMap();
		LOGGER.info(" : Business Rules have been evaluated for Season : Fall    \n\n");
		
	}
}
