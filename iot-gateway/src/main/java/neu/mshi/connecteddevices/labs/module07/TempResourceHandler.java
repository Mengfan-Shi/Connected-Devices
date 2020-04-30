package neu.mshi.connecteddevices.labs.module07;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import neu.mshi.connecteddevices.common.DataUtil;
import neu.mshi.connecteddevices.common.SensorData;

public class TempResourceHandler extends CoapResource
{
// static
private static final Logger _Logger =
	Logger.getLogger(TempResourceHandler.class.getName ());
// constructors
public TempResourceHandler ()
{
	super ("temp", true);
}
// public methods
@Override
/**
 * GET function in Coap, retrieves the information of the resource identified by the request URI.
 */
public void handleGET (CoapExchange ce)
{
	ce.respond (ResponseCode.VALID, "GET worked!");
	_Logger.info("Received GET request from client.");
}
@Override
/**
 * POST method in Coap, requests the server to create a new subordinate resource under parent URI.
 */
public void handlePOST(CoapExchange ce)
{
	String data = ce.getRequestText();
	//System.out.println(data);
	_Logger.info("Received json: " + data + "\n");
	ce.respond(ResponseCode.CREATED, "POST worked!");
	_Logger.info("Received POST request from client.\n");
	SensorData sensorData = DataUtil.toSensorDataFromJson(data);
	String data2 = DataUtil.toJsonFromSensorData(sensorData);
	_Logger.info("New converted json: " + data2);
	
}
@Override
/**
 * PUT method in Coap, requests the resource be updated or create with the enclosed message body.
 */
public void handlePUT(CoapExchange ce)
{
	//super.handlePUT(ce);
	ce.respond(ResponseCode.VALID, "PUT worked!");
	_Logger.info("Received PUT request from client.");
}
@Override
/**
 * DELETE method in Coap, requests the resource be deleted.
 */
public void handleDELETE(CoapExchange ce)
{
	//super.handleDELETE(ce);
	ce.respond(ResponseCode.DELETED, "DELETE worked!");
	_Logger.info("Received DELETE request from client.");
}
}