package neu.mshi.connecteddevices.labs.module06;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
/**
 * This class is used to connect to the remote mqtt broker server.
 *
 */
public class MqttClientConnector {
	private IMqttClient publisher;

	public void connect() {
		try {
			System.out.println("Connect to host...");
			//generate unique publishID
			String publisherID = UUID.randomUUID().toString();
			this.publisher = new MqttClient("tcp://127.0.0.1:1883", publisherID);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(65);
			this.publisher.connect(options);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public void publicMessage(String topic, int qos, byte[] payload) {
		try {
			System.out.println("Message publish...");
			//set message content
			MqttMessage msg = new MqttMessage(payload);
			//set message desired qos
			msg.setQos(qos);
			msg.setRetained(true);
			//publish message
			this.publisher.publish(topic, msg);
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			
		}
	}

	public void disconnect() {
		try {
			System.out.println("Disconnect from host...");
			this.publisher.disconnect();
			this.publisher.close();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
