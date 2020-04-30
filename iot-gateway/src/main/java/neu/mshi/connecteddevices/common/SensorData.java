package neu.mshi.connecteddevices.common;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SensorData {
	private String timeStamp = null;
	private String name = "Humidifier";
//	private float curValue = 0.0f;
	private double curHumidity = 0.0;
//	private float avgValue = 0.0f;
	private double avgHumidity = 0.0;
	private double curTemperature = 0.0;
	private double avgTemperature  = 0.0;
	private double threshold = 0.0;
//	private float minValue = 0.0f;
//	private float maxValue = 0.0f;
//	private float totValue = 0.0f;
//	private int sampleCount = 0;

	public SensorData() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method is used to store generated temp value.
	 * @param val:the random temp value which generated in TempSensorEmulator every 15 seconds 
	 */

//	public void addValue(float val) {
//		++this.sampleCount;
//		this.curValue = val;
//		this.totValue += val;
//		if (this.curValue < this.minValue) {
//			this.minValue = this.curValue;
//		}
//		if (this.curValue > this.maxValue) {
//			this.maxValue = this.curValue;
//		}
//		if (this.totValue != 0 && this.sampleCount > 0) {
//			this.avgValue = this.totValue / this.sampleCount;
//		}
//	}
	public String getTimeStamp() {
		SimpleDateFormat s = new SimpleDateFormat();
		s.applyPattern("yyyy-MM-dd HH:mm:ss a");
		Date d = new Date();
		timeStamp = s.format(d);
		return timeStamp;
		
	}
	
	public String getName() {
		return name;
	}
	
	public double getCurHumid() {
		return curHumidity;
	}
	
	public void setCurHumid(double curHumidity) {
		this.curHumidity = curHumidity;
	}
	
	public double getAvgHumid() {
		return avgHumidity;
	}
	
	public void setAvgHumid(double avgHumidity) {
		this.avgHumidity = avgHumidity;
		
	}
	
	public double getCurTemp() {
		return curTemperature;
	}
	
	public void setCurTemp(double curTemperature) {
		this.curTemperature = curTemperature;
	}
	
	public double getAvgTemp() {
		return avgTemperature;
	}
	
	public void setAvgTemp(double avgTemperature) {
		this.avgTemperature = avgTemperature;
		
	}
	
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

//	public float getCurValue() {
//		return curValue;
//	}

//	public void setCurValue(float curValue) {
//		this.curValue = curValue;
//	}
//
//	public float getAvgValue() {
//		return avgValue;
//	}
//
//	public void setAvgValue(float avgValue) {
//		this.avgValue = avgValue;
//	}
//
//	public float getMinValue() {
//		return minValue;
//	}
//
//	public void setMinValue(float minValue) {
//		this.minValue = minValue;
//	}
//
//	public float getMaxValue() {
//		return maxValue;
//	}
//
//	public void setMaxValue(float maxValue) {
//		this.maxValue = maxValue;
//	}
//
//	public float getTotValue() {
//		return totValue;
//	}
//
//	public void setTotValue(float totValue) {
//		this.totValue = totValue;
//	}
//
//	public int getSampleCount() {
//		return sampleCount;
//	}
//
//	public void setSampleCount(int sampleCount) {
//		this.sampleCount = sampleCount;
//	}
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the formatted email message 
	 */
	
	public String result() {
		SimpleDateFormat s = new SimpleDateFormat();
		s.applyPattern("yyyy-MM-dd HH:mm:ss a");
		Date d = new Date();
		return "<pre>Humidifier:</pre><pre>\tTime: "+s.format(d)+"\n\tCurrent Humidity: "+this.getCurHumid()+"\n\tAverage Humidity: "+this.getAvgHumid()+"\n\tCurrent Temperature: "+this.getCurTemp()+"\n\tAverage Temperature: "+this.getAvgTemp()+"\n\tHumidity Threshold: "+this.getThreshold()+"</pre>";
	}
}
