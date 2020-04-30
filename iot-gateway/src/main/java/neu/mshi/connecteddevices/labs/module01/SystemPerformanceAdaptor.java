package neu.mshi.connecteddevices.labs.module01;

import com.labbenchstudios.edu.connecteddevices.common.DevicePollingManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class SystemPerformanceAdaptor {
	private static final Logger _Logger = Logger.getLogger(SystemPerformanceAdaptor.class.getSimpleName());
	private static long _pollCycle;
	private DevicePollingManager _pollManager;
	private static String cpuUtil;
	private static String memUtil;
	
	public SystemPerformanceAdaptor() {
		super();
	}	
	
	public SystemPerformanceAdaptor(long pollCycle) {
		//super();
		if (pollCycle >= 1L) {
			_pollCycle = pollCycle;	
		}
		
		_pollManager = new DevicePollingManager(2);
		startPolling();
	}
	
	public void startPolling() {
		_Logger.info("Creating and scheduling CPU Utilization poller...");
		//_pollManager.schedulePollingTask(
				//new SystemCpuUtilTask("Cpu", _pollCycle), _pollCycle);
		_pollManager.schedulePollingTask(new SystemCpuUtilTask(this), _pollCycle);
		_Logger.info("Creating and schedulig Memory Utilization poller...");
		//_pollManager.schedulePollingTask(
			//	new SystemMemUtilTask("Mem", _pollCycle), _pollCycle);
		_pollManager.schedulePollingTask(new SystemMemUtilTask(this), _pollCycle);
	}
	
	public void Show(int flag, double result) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		/*Set the output format for the time stamp;*/
		Date d = new Date();
		/*The flag value defines the callback for the CPU and Memory; flag1 for cpu, flag-1 for memory*/
		if(flag == 1) {
			cpuUtil = String.valueOf(result);
			//java.util.logging.SimpleFormatter.format="%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %2$s %5$s%6$s%n";
			//_Logger.info(cpuUtil);
			System.out.println(s.format(d) + "INFO: CPU Utilization = " + cpuUtil);
		}
		else if(flag == -1) {
			memUtil = String.valueOf(result);
			System.out.println(s.format(d) + "INFO: Memory Utilization = " + memUtil);
			}
		
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		SystemPerformanceAdaptor _sysPerfAdaptor = new SystemPerformanceAdaptor(_pollCycle);
//		_sysPerfAdaptor.startPolling();
//
//	}

}
