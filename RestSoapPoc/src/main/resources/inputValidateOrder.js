

// fetch execution variables
var createOrdVar = execution.getVariable("parseCOVar");
var outputCO = execution.getVariable("outputCO");
var createorderresp = execution.getVariable('orderresponse');

execution.setVariable("inputValidateOrd", "InputValueOfValidateOrder");

var createorderrespjson =JSON.parse(createorderresp);
execution.setVariable('customerOrderNumber', createorderrespjson.CustomerOrderNumber);
execution.setVariable('OrderNumber', createorderrespjson.OrderNumber);
//execution.setVariable('requestId', createorderrespjson.RequestId);

//var customerOrderNumber = S(createorderresp).childElement("CustomerOrderNumber").textContent();
//var orderNumber = S(createorderresp).childElement("OrderNumber").textContent();

//execution.setVariable('customerOrderNumber1', customerOrderNumber);
//execution.setVariable('orderNumber1', orderNumber);

