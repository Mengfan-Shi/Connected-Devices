package neu.mshi.connecteddevices.labs.module01;

import java.lang.management.ManagementFactory;
import java.util.logging.Logger;
import com.sun.management.OperatingSystemMXBean;

public class SystemMemUtilTask implements Runnable {
	private double totalmem, freemem, memUtil;
	private SystemPerformanceAdaptor _sysPerfAdaptor;
//  private static final Logger _Logger = Logger.getLogger(SystemPerformanceAdaptor.class.getSimpleName());


	public SystemMemUtilTask() {
		// TODO Auto-generated constructor stub
		super();
		/*
		 * while (true){ run(); String res = "memory = " +
		 * String.valueOf(getDataFromSensor()) + ";"; _Logger.info(res); try {
		 * Thread.sleep(2000); } catch (InterruptedException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
	}

	public SystemMemUtilTask(SystemPerformanceAdaptor systemPerformanceAdaptor) {
		// TODO Auto-generated constructor stub
		super();
		totalmem = 0.0;
		freemem = 0.0;
		memUtil = 0.0;
		_sysPerfAdaptor = systemPerformanceAdaptor;
	}

	public double getDataFromSensor() {
		// TODO Auto-generated method stub
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		totalmem = os.getTotalPhysicalMemorySize();
		freemem = os.getFreePhysicalMemorySize();
		memUtil = (1 - freemem/totalmem) * 100;	
		return memUtil;
	}

	public void run() {
		// TODO Auto-generated method stub
		getDataFromSensor();
		_sysPerfAdaptor.Show(-1, memUtil);
	}

}
