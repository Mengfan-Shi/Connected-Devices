package neu.mshi.connecteddevices.labs.module08;

import com.ubidots.*;

public class UbidotsClientConnector {
	private ApiClient api;

	public UbidotsClientConnector() {
		super();
		// connect to Ubidots
		this.api = new ApiClient("BBFF-uNAPBBY8FJo5XYAIBLrSamz8N9WCKa");
		System.out.println("Connection success!");
	}
	
	public void publishData(double temp) {
		// get tempsensor by variable ID
		Variable variable = api.getVariable("5dc7749f73efc33661f1a941");
		// publish new value
		variable.saveValue(temp);
		System.out.println("Publish success!");
	}
}
