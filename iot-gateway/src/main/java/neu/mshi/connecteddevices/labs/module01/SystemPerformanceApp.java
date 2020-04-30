/**
 * 
 */
package neu.mshi.connecteddevices.labs.module01;

import java.util.logging.Logger;
import com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp;
import com.labbenchstudios.edu.connecteddevices.common.DeviceApplicationException;

/**
 *
 */
public class SystemPerformanceApp extends BaseDeviceApp
{
	// static
	private static SystemPerformanceAdaptor _sysPerfAdaptor;
	private static long _pollCycle = 5L;
	private static final Logger _Logger =
		Logger.getLogger(SystemPerformanceApp.class.getSimpleName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SystemPerformanceApp app = new SystemPerformanceApp(SystemPerformanceApp.class.getSimpleName(), args);
		app.startApp();
	}
	
	// private var's
	//private SystemPerformanceAdaptor _sysPerfAdaptor = new SystemPerformanceAdaptor(5);
	
	
	
	// constructors
	
	/**
	 * Default.
	 * 
	 */
	public SystemPerformanceApp()
	{
		super();
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 */
	public SystemPerformanceApp(String appName)
	{
		super(appName);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param appName
	 * @param args
	 */
	public SystemPerformanceApp(String appName, String[] args)
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
		_Logger.info("Hello - SystemPerformanceApp here!");
		_sysPerfAdaptor = new SystemPerformanceAdaptor(_pollCycle);
	}
	
	/* (non-Javadoc)
	 * @see com.labbenchstudios.edu.connecteddevices.common.BaseDeviceApp#stop()
	 */
	@Override
	protected void stop() throws DeviceApplicationException
	{
		_Logger.info("Stopping SystemPerformanceApp...");
	}
	
}
