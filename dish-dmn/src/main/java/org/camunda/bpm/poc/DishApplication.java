package org.camunda.bpm.poc;

import java.util.HashMap;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ProcessApplication("Dish App DMN")
public class DishApplication extends ServletProcessApplication {
		static Logger LOGGER = LoggerFactory.getLogger(DishApplication.class);
	
	  @PostDeploy
	  public void evaluateDecisionTable(ProcessEngine processEngine) {
	    DecisionService decisionService = processEngine.getDecisionService();
	    VariableMap variables = Variables.createVariables().putValue("season", "Summer");
//	    HashMap variables = new HashMap<String, String>();
	    decisionService.evaluateDecisionTableByKey("dish", variables);
	    LOGGER.info(" : DishApplication post-deploy execution done ..... \n\n");
	  }
}
