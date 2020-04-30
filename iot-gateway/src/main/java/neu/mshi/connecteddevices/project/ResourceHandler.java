package neu.mshi.connecteddevices.project;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

import com.google.gson.Gson;

import neu.mshi.connecteddevices.common.DataUtil;
import neu.mshi.connecteddevices.common.SensorData;


public class ResourceHandler extends CoapResource {
	private static final Logger _Logger =
			Logger.getLogger(ResourceHandler.class.getName ());
	public int alertdiff = 10;
	public double curHumidity;
	public double avgHumidity;
	public double curTemp;
	public double avgTemp;
	public double threshold;
	public SensorData sensorData;
	public ResourceHandler() {
		super("humidifier", true);
	}
	
	@Override
	public void handleGET (CoapExchange ce) {
		ce.respond (ResponseCode.VALID, "GET worked!");
		_Logger.info("Received GET request from client.");
	}
	
	@Override
	public void handlePUT(CoapExchange ce) {
		String data = ce.getRequestText();
		_Logger.info("Received json: " + data + "\n");
		ce.respond(ResponseCode.CREATED, "PUT worked!");
		_Logger.info("Received PUT request from client.\n");
		//transform the received json payload to sensordata instance
		sensorData = DataUtil.toSensorDataFromJson(data);
		//save json message to gateway local file system
		DataUtil.toJsonFile("test", data);
		curHumidity = sensorData.getCurHumid();
		avgHumidity = sensorData.getAvgHumid();
		curTemp = sensorData.getCurTemp();
//		avgTemp = sensorData.getAvgTemp();
		threshold = sensorData.getThreshold();
//		System.out.println(curHumidity);
		String VariableLabel1 = "humiditysensor";
		String VariableLabel2 = "tempsensor";
		String VariableLabel3 = "humiditythreshold";
		String VariableLabel4 = "avghumidity";
//		if(Math.abs(avgHumidity - threshold) >= alertdiff) {
//			_Logger.info("Start to send Email...");
//			SmtpClientConnector s = new SmtpClientConnector();
//			try {
//				s.TestEmail(sensorData.result());
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		MqttClientConnector m = new MqttClientConnector();
		m.connect(true);
		String topicName = "/v1.6/devices/humidifier";
//		String payload = "{\"" + VariableLabel1 + "\":" + curHumidity + "," + "\"" + VariableLabel2 + "\":" + curTemp + "," + "\"" + VariableLabel3 + "\":" + threshold + "\"" + VariableLabel4 + "\":" + avgHumidity + "}";
		String payload = "{\"" + VariableLabel1 + "\":" + curHumidity + "," + "\"" + VariableLabel2 + "\":" + curTemp + ","  + "\"" + VariableLabel4 + "\":" + avgHumidity + "}";
		String p2 = "{\"" + VariableLabel3 + "\":" + threshold + "}";
		//publish variable values to Ubidots dashboad
		m.publicMessage(topicName, 2, payload.getBytes());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.publicMessage(topicName, 2, p2.getBytes());
		m.disconnect();
//		
//		m.publicMessage(topicName, 2, p2.getBytes());
//		m.publicMessage(topicName, 2, p3.getBytes());
//		m.disconnect();
	}
	
//	public double getCurHumidity() {
//		return curHumidity;
//	}
//
//	public void setCurHumidity(double curHumidity) {
//		this.curHumidity = curHumidity;
//	}
//
//	public double getAvgHumidity() {
//		return avgHumidity;
//	}
//
//	public void setAvgHumidity(double avgHumidity) {
//		this.avgHumidity = avgHumidity;
//	}
//
//	public double getCurTemp() {
//		return curTemp;
//	}
//
//	public void setCurTemp(double curTemp) {
//		this.curTemp = curTemp;
//	}
//
//	public double getAvgTemp() {
//		return avgTemp;
//	}
//
//	public void setAvgTemp(double avgTemp) {
//		this.avgTemp = avgTemp;
//	}
//
//	public double getThreshold() {
//		return threshold;
//	}
//
//	public void setThreshold(double threshold) {
//		this.threshold = threshold;
//	}
//
//	public SensorData getSensorData() {
//		return sensorData;
//	}
//
//	public void setSensorData(SensorData sensorData) {
//		this.sensorData = sensorData;
//	}

	@Override
	public void handlePOST(CoapExchange ce) {
		ce.respond(ResponseCode.CREATED, "POST worked!");
		_Logger.info("Received POST request from client.\n");		
	}
	
	@Override
	public void handleDELETE(CoapExchange ce) {
		ce.respond(ResponseCode.DELETED, "DELETE worked!");
		_Logger.info("Received DELETE requesr from client.\n");
	}
	
	
}
