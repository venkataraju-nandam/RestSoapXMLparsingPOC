/**
 * 
 */



// fetch execution variables
var order = execution.getVariable("order");
var ec = execution.getVariable("executionContext");

ec.put('customerOrderNumber', order.customerOrderNumber);
ec.put('customerOrderNumber', order.requestId);

execution.setVariable("executionContext", ec);

var createorderresp = execution.getVariable('orderresponse');


//var createorderresp = execution.getVariable('createorderresponse');
//var createorder = createorderresp.substring(rawForecast.indexOf("\n"));

//var parsedOrderId = S(createorder).childElement("orderid").textContent();

// Order Id looks like this: <OrderId> 13 34 34</OrderId>
//var regex = /\((\d+) C\)/;

// return only the temperature in C:
//regex.exec(parsedOrderId)[1];