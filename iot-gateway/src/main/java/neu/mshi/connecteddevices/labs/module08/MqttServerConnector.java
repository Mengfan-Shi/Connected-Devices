package neu.mshi.connecteddevices.labs.module08;

import java.util.Arrays;
import java.util.UUID;

import javax.net.ssl.SSLSocketFactory;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.labbenchstudios.edu.connecteddevices.common.CertManagementUtil;

public class MqttServerConnector {
	private IMqttClient subscriber;
	private SSLSocketFactory mySSLSocketFactory;
	private boolean TLS = false;

	public void connect(boolean flag) {
		try {
			this.TLS = flag;
			//create subscriber ID
			String subscriberID = UUID.randomUUID().toString();
			if (!this.TLS) {
				//disable TLS
				System.out.println("Start to connect to ubidots...");
				this.subscriber = new MqttClient("tcp://industrial.api.ubidots.com:1883", subscriberID);
			} else {
				//enable TLS
				System.out.println("Start to connect to ubidots(ssl)...");
				this.subscriber = new MqttClient("ssl://industrial.api.ubidots.com:8883", subscriberID);
				// Load SSL certification file
				CertManagementUtil certManagementUtil = CertManagementUtil.getInstance();
				mySSLSocketFactory = certManagementUtil.loadCertificate("C:/ubidots_cert.pem");
			}
			// Config reconnection and timeout
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(65);
			if (this.TLS) {
				options.setSocketFactory(mySSLSocketFactory);
			}
			options.setUserName("BBFF-uNAPBBY8FJo5XYAIBLrSamz8N9WCKa");
			this.subscriber.connect(options);
			System.out.println("Connection created!");
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void subscribe(String topic, int QoS) {
		try {
			this.subscriber.subscribe(topic,QoS);
			System.out.print("Subscribe success!");
			this.subscriber.setCallback(new MqttCallback() {

				public void connectionLost(Throwable cause) {
					// TODO Auto-generated method stub
					
				}

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					// TODO Auto-generated method stub
					System.out.println(topic + ": " + Arrays.toString(message.getPayload()));
					//System.out.println(topic + ": " + message.getPayload());
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
					// TODO Auto-generated method stub
					
				}});
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
