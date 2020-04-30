package neu.mshi.connecteddevices.labs.module02;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.labbenchstudios.edu.connecteddevices.common.DevicePollingManager;
import neu.mshi.connecteddevices.common.SensorData;


public class TempSensorEmulator {
	public SmtpClientConnector _smtpConnector;
	public SensorData sensorData;
	private DevicePollingManager _pollManager;
	private long _pollCycle;
	private int _alertDiff;

	private static final Logger _Logger = Logger.getLogger(TempSimulatorApp.class.getSimpleName());

	public TempSensorEmulator(long pollCycle, int alertDiff) {
		super();
		sensorData = new SensorData();
		_smtpConnector = new SmtpClientConnector();
		_alertDiff = alertDiff;
		/*
		 *pollCycle means time interval, should be greater than 1
		 */
		if (pollCycle >= 1L) {
			_pollCycle = pollCycle;
		}
		_pollManager = new DevicePollingManager(1);
		startPolling();
	}
//get sensordata
	private void startPolling() {
		// TODO Auto-generated method stub
		_Logger.info("Start to read Temperature data...");
		_pollManager.schedulePollingTask(new getSensorData(), _pollCycle);
	}
//send alert email
	public void sendNotification() {
		try {
			_smtpConnector.TestEmail(sensorData.result());
		} catch (Exception e) {
			_Logger.log(Level.WARNING, "Failed to send SMTP message.", e);
		}
	}
	/**
	 * 
	 * @param min: the lower bound of the generated temp value
	 * @param max: the upper bound of the generated temp value
	 * @return: return random value between 0, 30
	 */
	public static float getRandomFloat(final float min, final float max) {
		return min + ((max - min) * new Random().nextFloat());
	}
	/**
	 * In order to use the schedulepollingTask the getSensorData() method is supposed to implement a thread
	 */
	class getSensorData implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			SimpleDateFormat s = new SimpleDateFormat();
			s.applyPattern("yyyy-MM-dd HH:mm:ss a");
			Date d = new Date();
			float t = getRandomFloat((float) 0.0, (float) 30.0);
			//sensorData.addValue(t);
			//System.out.println(s.format(d) + "\tDegree: " + String.valueOf(sensorData.getCurValue()));
			//System.out.println(s.format(d) + "\tAvg:    " + String.valueOf(sensorData.getAvgValue()));
			//System.out.println();
			if (Math.abs(sensorData.getCurValue() - sensorData.getAvgValue()) > _alertDiff) {
				_Logger.info("Sending email...");
				sendNotification();
			}
		}
	}
}
