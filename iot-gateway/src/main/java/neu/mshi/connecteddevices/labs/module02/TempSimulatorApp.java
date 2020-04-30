/**
 * 
 */
package neu.mshi.connecteddevices.labs.module02;

import java.util.logging.Logger;

import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;



/**
 *
 */
public class TempSimulatorApp extends BaseDeviceApp
{
	// static
	private static TempSensorEmulator _sensor;
	private static long _pollCycle = 15L;
	private static final Logger _Logger =
		Logger.getLogger(TempSimulatorApp.class.getSimpleName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		TempSimulatorApp app = new TempSimulatorApp(TempSimulatorApp.class.getSimpleName(), args);
		app.startApp();
	}
	
	// private var's
	
	
	// constructors
	
	/**
	 * Default.
	 * 
	 */
	public TempSimulatorApp()
	{
		super();
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 */
	public TempSimulatorApp(String appName)
	{
		super(appName);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 * @param args
	 */
	public TempSimulatorApp(String appName, String[] args)
	{
		super(appName, args);
	}
	
	// protected methods
	
	/* (non-Javadoc)
	 * @see com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp#start()
	 */
	@Override
	protected void start() throws DeviceApplicationException
	{
		_Logger.info("Hello - TempSimulatorApp here!");
		TempSensorEmulator _sensor = new TempSensorEmulator(_pollCycle,5);
	}
	
	/* (non-Javadoc)
	 * @see com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp#stop()
	 */
	@Override
	protected void stop() throws DeviceApplicationException
	{
		_Logger.info("Stopping TempSimulatorApp...");
	}
	
}
