package neu.mshi.connecteddevices.project;

import java.util.logging.Logger;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;

//create coap server and add humidifier resourcehandler
public class CoapServerConnector {
	private static final Logger _Logger =
			Logger.getLogger(CoapServerConnector.class.getName());
	private CoapServer coapServer;
	
	public CoapServerConnector()
	{
		super();
	}
	public void addResource(CoapResource resource) {
		if (resource != null) {
			coapServer.add(resource);
		}
	}
	
	public void start() {
		if(coapServer == null) {
			_Logger.info("Creating CoAP server instance and 'humidifier' handler...");
			coapServer = new CoapServer();
			ResourceHandler rhandler = new ResourceHandler();
			coapServer.add(rhandler);
		}
		_Logger.info("Starting Coap Server...");
		coapServer.start();
	}
	public void stop() {
		_Logger.info("Stoping Coap Server...");
		coapServer.stop();
	}

}
