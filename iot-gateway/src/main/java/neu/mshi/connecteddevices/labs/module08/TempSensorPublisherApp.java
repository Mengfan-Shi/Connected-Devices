package neu.mshi.connecteddevices.labs.module08;

import java.util.logging.Logger;

public class TempSensorPublisherApp {
	// static
	private static final Logger _Logger = Logger.getLogger(TempSensorPublisherApp.class.getName());

	private static TempSensorPublisherApp _App;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		_App = new TempSensorPublisherApp();
		try {
			_App.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TempSensorPublisherApp() {
		super();
	}

	public void start() {
		// TODO Auto-generated method stub
		// Set mqtt message context
		String payload = "{ \"TempSensor\" : 11 }";
		_Logger.info(payload);
		// Mqtt connection built
		MqttClientConnector _mqttClient = new MqttClientConnector();
		// Enable TLS 
		_mqttClient.connect(true);
		String topicName = "/v1.6/devices/TempMonitor";
		// Publish the topic test.
		_mqttClient.publicMessage(topicName, 2, payload.getBytes());
	}

}
