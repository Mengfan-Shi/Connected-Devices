'''
@author: Mengfan Shi
'''
from sense_hat import SenseHat
import logging
from labs.common.SensorData import SensorData
from project.CoapClientConnector import CoapClientConnector
from labs.common.ActuatorData import ActuatorData
from project.HumidityActuatorEmulator import HumidityActuatorEmulator
from labs.common.DataUtil import DataUtil
from time import sleep
import _thread

class DeviceAdaptor(object):
    
    def __init__(self, threshold, t):
        '''
        '''
        self.sh = SenseHat()
        self.sensorData = SensorData
        self.threshold = threshold
        self.actuator = HumidityActuatorEmulator()
        self.rateInSec = t
        self.datautil = DataUtil
        host = "coap://10.0.0.13:5683/humidifier"
        self.coapclientconnector = CoapClientConnector(host)
        _thread.start_new_thread(self.run())
    '''
    read humidity and temperature data from SenseHat
    '''
    def read(self):
        self.sh.clear()
        self.humidity = self.sh.get_humidity()
        self.temp = self.sh.get_temperature()
    '''
    add target data(humidity, temperature, threshold) to SensorData 
    '''
    def addData(self):
        self.sensorData.addHumid(self.sensorData, self.humidity)
        self.sensorData.addTemp(self.sensorData, self.temp)
        self.sensorData.addthreshold(self.sensorData, self.threshold)
    '''
    Set actions on actuator based on target data
    '''
    def adjust(self):
        self.actuatorData = ActuatorData()
        self.actuatorData.updateData(1, 1, 0, "adjust", self.sensorData.getHumid(self.sensorData))
        self.actuator.process(self.actuatorData, self.threshold)
    '''
    transform SensorData instance to Json and transfer it to the Gateway
    '''
    def transfer(self):
        output = self.datautil.toJsonFromSensorData(self.datautil, self.sensorData)
        logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        logging.info("Json to sent: " + str(output) + "\n")
        self.datautil.toJsonfileFromSensorData(self.datautil, output)
        self.coapclientconnector.test(output)
        logging.info("Json sent success!\n")
    
    '''
    DeviceApp would run at a setting time interval
    '''
    def run(self):
        while True:
            self.read()
            self.addData()
            self.adjust()
            self.transfer()
            sleep(self.rateInSec)
        
    
        
        
        
        
    
    
        
        
        