package neu.mshi.connecteddevices.labs.module08;

import java.util.logging.Logger;

public class TempActuatorSubscriberApp {
	// static
		private static final Logger _Logger = Logger.getLogger(TempActuatorSubscriberApp.class.getName());

		private static TempActuatorSubscriberApp _App;

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			_App = new TempActuatorSubscriberApp();
			try {
				_App.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public TempActuatorSubscriberApp() {
			super();
		}

		public void start() {
			// Mqtt connection built
			MqttServerConnector _mqttServer = new MqttServerConnector();
			// Enable ssl
			_mqttServer.connect(true);
			String topicName = "/v1.6/devices/tempmonitor/tempactuator";
			// Subscribe the topic
			_mqttServer.subscribe(topicName, 2);
		}

}