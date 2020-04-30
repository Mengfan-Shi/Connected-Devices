package neu.mshi.connecteddevices.labs.module01;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.logging.Logger;

public  class SystemCpuUtilTask implements Runnable {
	private SystemPerformanceAdaptor _sysPerfAdaptor;
	private double cpuUtil;
	//private static final Logger _Logger = Logger.getLogger(SystemPerformanceAdaptor.class.getSimpleName());

	public SystemCpuUtilTask() {
		// TODO Auto-generated constructor stub
		super();
//		while (true){
//			run();
//			String res = "cpu= " + String.valueOf(cpuUtil) + ";";
//			_Logger.info(res);
//			
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

	public SystemCpuUtilTask(SystemPerformanceAdaptor systemPerformanceAdaptor) {
		// TODO Auto-generated constructor stub
		super();
		cpuUtil = 0.0;
		_sysPerfAdaptor = systemPerformanceAdaptor;
		
	}


	public void run() {
		// TODO Auto-generated method stub
		getDataFromSensor();
		_sysPerfAdaptor.Show(1, cpuUtil);
	}
	
	public double getDataFromSensor() {
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		cpuUtil = os.getSystemCpuLoad()*100;
		return cpuUtil;
	}


	

}
