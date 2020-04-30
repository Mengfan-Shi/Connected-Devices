package neu.mshi.connecteddevices.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * Use Gson library to encode a SensorData instance to a string in json format.
 */
public class DataUtil {
	public static String toJsonFromSensorData(SensorData sensorData) {
		String s = "";
		Gson gson = new Gson();
		s = gson.toJson(sensorData);
		return s;
	}
	
	public static void toJsonFile(String fileName, String data) {
		BufferedWriter writer = null;
		File file = new File("C:\\Users\\apple\\" + fileName + ".json");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false),"UTF-8"));
			writer.write(data);
		} catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(writer != null) {
					writer.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
				}
			}
		System.out.println("Success!");
		}

/*
 * Use Gson library to decode a json string into a SensorData object.
 */
	
	public static SensorData toSensorDataFromJson(String s) {
		SensorData sensorData = null;
		Gson gson = new Gson();
		sensorData = gson.fromJson(s, SensorData.class);
		return sensorData;
	}
/*
 * Use Gson library to read a json file and then decode it to a SensorData object. 
 */
	public static SensorData toSensorDataFromJsonFile(FileReader fr) {
		SensorData sensorData = null;
		BufferedReader br = null;
		br = new BufferedReader(fr);
		Gson gson = new GsonBuilder().create();
		sensorData = gson.fromJson(br, SensorData.class);
		return sensorData;	
	}
}
