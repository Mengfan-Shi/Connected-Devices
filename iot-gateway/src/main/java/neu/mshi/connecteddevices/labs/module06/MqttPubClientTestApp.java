package neu.mshi.connecteddevices.labs.module06;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Logger;

import neu.mshi.connecteddevices.common.DataUtil;
import neu.mshi.connecteddevices.common.SensorData;

public class MqttPubClientTestApp {
	// static
	private static final Logger _Logger = Logger.getLogger(MqttPubClientTestApp.class.getName());

	private static MqttPubClientTestApp _App;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_App = new MqttPubClientTestApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MqttPubClientTestApp() {
		super();
	}

	public void start() {
		// TODO Auto-generated method stub
		//create a new sensordata instance
		SensorData sensorData = createSensorData();
		//convert it to json data
		String payload = DataUtil.toJsonFromSensorData(sensorData);
		System.out.println("Read json from file...");
		//log the json data 
		_Logger.info(payload);
		MqttClientConnector _mqttClient = new MqttClientConnector();
		_mqttClient.connect();
		_mqttClient.publicMessage("test", 2, payload.getBytes());
		_mqttClient.disconnect();
	}

	private SensorData createSensorData() {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("/Users/apple/workspace/iot-device/apps/labs/module06/SensorData.json");
			return DataUtil.toSensorDataFromJsonFile(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
