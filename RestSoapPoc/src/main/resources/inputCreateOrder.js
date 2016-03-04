/**
 * 
 */

var createorderresp = execution.getVariable('orderresponse');

execution.setVariable('inputCO', 'InputValueOfCreateOrder');

//var createorderresp = execution.getVariable('createorderresponse');
//var createorder = createorderresp.substring(rawForecast.indexOf("\n"));

//var parsedOrderId = S(createorder).childElement("orderid").textContent();

// Order Id looks like this: <OrderId> 13 34 34</OrderId>
//var regex = /\((\d+) C\)/;

// return only the temperature in C:
//regex.exec(parsedOrderId)[1];