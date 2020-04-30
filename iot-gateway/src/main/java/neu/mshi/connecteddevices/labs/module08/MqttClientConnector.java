package neu.mshi.connecteddevices.labs.module08;

import java.util.UUID;

import javax.net.ssl.SSLSocketFactory;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.labbenchstudios.edu.connecteddevices.common.CertManagementUtil;

public class MqttClientConnector {
	private IMqttClient publisher;
	private SSLSocketFactory mySSLSocketFactory;
	private boolean TLS = false;

	public void connect(boolean flag) {
		try {
			this.TLS = flag;
			//create mqtt client ID
			String publisherID = UUID.randomUUID().toString();
			if (!this.TLS) {
				//TLS disabled
				System.out.println("Start to connect ubidots...");
				this.publisher = new MqttClient("tcp://industrial.api.ubidots.com:1883", publisherID);
			} else {
				//TLS enabled
				System.out.println("Start to connect to ubidots(ssl)...");
				this.publisher = new MqttClient("ssl://industrial.api.ubidots.com:8883", publisherID);
				// Load SSL certification file
				CertManagementUtil certManagementUtil = CertManagementUtil.getInstance();
				mySSLSocketFactory = certManagementUtil.loadCertificate("C:/ubidots_cert.pem");
			}
			//configure reconnection and timeout
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(65);
			if (this.TLS) {
				options.setSocketFactory(mySSLSocketFactory);
			}
			options.setUserName("BBFF-uNAPBBY8FJo5XYAIBLrSamz8N9WCKa");
			this.publisher.connect(options);
			System.out.println("Connection created!");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void publicMessage(String topicName, int QoS, byte[] payload) {
		try {
			System.out.println("Start to publish message...");
			//set message context and qos level
			MqttMessage msg = new MqttMessage(payload);
			msg.setQos(QoS);
			msg.setRetained(true);
			this.publisher.publish(topicName, msg);
			this.publisher.setCallback(new MqttCallback() {

				public void connectionLost(Throwable cause) {
					// TODO Auto-generated method stub
					
				}

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub
					disconnect();
				}});
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			System.out.println("Message has been sent, disconnect from host!");
			//connection closed
			this.publisher.disconnect();
			this.publisher.close();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
