package neu.mshi.connecteddevices.project;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;

//run gateway app every 1 min
public class GatewayApp extends BaseDeviceApp {
	private static final Logger _Logger =
			Logger.getLogger(GatewayApp.class.getSimpleName());
	private static long pollCycle = 60L;
	private static CoapServerConnector server;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GatewayApp app = new GatewayApp(GatewayApp.class.getSimpleName(), args);
		server = new CoapServerConnector();
		server.start();
//		MqttClientConnector m = new MqttClientConnector();
//		m.connect(true);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		app.startApp();
	}
	
	public GatewayApp()
	{
		super();
	}
	
	public GatewayApp(String appName)
	{
		super(appName);
	}
	
	public GatewayApp(String appName, String[] args)
	{
		super(appName, args);
	}
	
	@Override
	protected void start() throws DeviceApplicationException
	{
		_Logger.info("Hello - GatewayApp here!");
		GatewayAdaptor adaptor = new GatewayAdaptor(pollCycle);
	}
	
	@Override
	protected void stop() throws DeviceApplicationException
	{
		_Logger.info("Stopping GatewayApp...");
	}
	
	

}
