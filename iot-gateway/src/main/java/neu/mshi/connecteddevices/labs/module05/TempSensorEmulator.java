package neu.mshi.connecteddevices.labs.module05;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.labbenchstudios.edu.connecteddevices.common.DevicePollingManager;

import neu.mshi.connecteddevices.common.DataUtil;
import neu.mshi.connecteddevices.common.SensorData;
import neu.mshi.connecteddevices.labs.module02.SmtpClientConnector;


public class TempSensorEmulator {
	public SmtpClientConnector _smtpConnector;
	public SensorData sensorData;
	private DevicePollingManager _pollManager;
	private long _pollCycle;
	private int _alertDiff;

	private static final Logger _Logger = Logger.getLogger(TempSimulatorApp.class.getSimpleName());

/**
 * read the json file created by python application, which is stored in the local system
 */
	public TempSensorEmulator() {
		SensorData sd = null;
		FileReader fr = null;
		try {
			fr = new FileReader("/Users/apple/workspace/iot-device/apps/labs/module05/SensorData.json");
			sd = DataUtil.toSensorDataFromJsonFile(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		run(sd);
	}
/*
 * Output the log information of the converted SensorData object. 
 */
	
		public void run(SensorData sensorData) {
			System.out.println("Reading json file...");
			_Logger.info(DataUtil.toJsonFromSensorData(sensorData));
	
		}
}