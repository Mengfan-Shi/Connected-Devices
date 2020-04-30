package neu.mshi.connecteddevices.labs.module08;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TempSensorCloudPublisherApp {
	private static final Logger _Logger = Logger.getLogger(TempSensorCloudPublisherApp.class.getName());

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		double min_random = 14.0;
		double max_random = 35.0;
		while(true) {
			//generate random temp value
			double random_temp = min_random + Math.random() * (max_random - min_random);
			_Logger.info("Connecting to host...");
			UbidotsClientConnector u = new UbidotsClientConnector();
			_Logger.info("publish value...r");
			System.out.println(String.valueOf(random_temp));
			//publish temp value
			u.publishData(random_temp);
			//set time interval
			TimeUnit.SECONDS.sleep(60);
		}
	}

}
