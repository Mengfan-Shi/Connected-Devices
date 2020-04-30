package neu.mshi.connecteddevices.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labbenchstudios.edu.connecteddevices.common.DevicePollingManager;

import neu.mshi.connecteddevices.common.DataUtil;
import neu.mshi.connecteddevices.common.SensorData;


public class GatewayAdaptor {
	private static final Logger _Logger = Logger.getLogger(GatewayAdaptor.class.getName());
	private int alertdiff = 5;
	SmtpClientConnector smtpconnector = new SmtpClientConnector();
	private DevicePollingManager manager;
	private long pollCycle;
	
	public GatewayAdaptor(long Cycle) {
		super();
		if (Cycle >= 1L) {
			pollCycle = Cycle;
		}
		manager = new DevicePollingManager(1);
		startpolling();
	}
	class getData implements Runnable {
		public void run() {
			FileReader fr = null;
			try {
				fr = new FileReader("C:\\Users\\apple\\test.json");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//transform json to sensordata instance
			SensorData sensorData = DataUtil.toSensorDataFromJsonFile(fr);
			double curHumidity = sensorData.getCurHumid();
			double curTemp = sensorData.getCurTemp();
			double avgHumidity = sensorData.getAvgHumid();
			double threshold = sensorData.getThreshold();
			// compare average humifity and setting threshold, if the alertdiff is reached send the alert email 
			if(Math.abs(avgHumidity - threshold) >= alertdiff) {
				_Logger.info("Start to send email...");
				try {
					smtpconnector.TestEmail(sensorData.result());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					_Logger.log(Level.WARNING, "Failed to send SMTP message.", e);
				}
			}
	}
	}
	//run getData() funciton at setting time interval
	private void startpolling() {
		_Logger.info("Start to get data...");
		manager.schedulePollingTask(new getData(), pollCycle);
		
	}
}
