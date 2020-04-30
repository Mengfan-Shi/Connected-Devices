'''
@author: Mengfan Shi
'''
from datetime import datetime
import _thread
from sense_hat import SenseHat
from labs.common.SensorData import SensorData
from labs.common.ActuatorData import ActuatorData
from labs.module03.SmtpClientConnector import SmtpClientConnector
from time import sleep
from labs.module03.TempActuatorEmulator import TempActuatorEmulator
from labbenchstudios.common.ConfigUtil import ConfigUtil
from labbenchstudios.common import ConfigConst
from labs.common.DataUtil import DataUtil


class TempSensorAdaptor(object):
    '''
    classdocs
    '''
    rateInSec = 10.0
    alertDiff = 10.0
    curDegree = 0.0
    nominalTemp = 10.0
    sense=None

    '''
    Initiate all variables. 
    '''
    def __init__(self, a, r):
        self.sense = SenseHat()
        self.tempActuatorEmulator = TempActuatorEmulator()
        self.datautil = DataUtil
        self.alertDiff = a
        self.rateInSec = r
        self.sensorData = SensorData
        self.connector = SmtpClientConnector()
        self.config = ConfigUtil('../../../config/ConnectedDevicesConfig.props')
        self.config.loadConfig()
        self.nominalTemp = self.config.getProperty(ConfigConst.CONSTRAINED_DEVICE, 'nominalTemp')
        _thread.start_new_thread(self.run())
    
    '''
    Read temp value from SenseHat emulator: sense_hat.py
    '''
    def readTempFromSH(self):
        self.sense.clear()
        self.curDegree = self.sense.get_temperature()
    
    '''
    Parse the temp values to SensorData, output the current value and average value. 
    '''
    def addTempToSensorData(self):
        self.sensorData.addValue(self.sensorData, self.curDegree)
        now = datetime.now()
        print(str(now) + " Current Temp: "+ str(self.curDegree))
        print(str(now) + " Average Temp: " + str(self.sensorData.getAvgValue(self.sensorData)))
        print("\n")
    '''
    When the alert difference value is reached, send email to customer address. 
    '''
    def alert(self):
        if (abs(self.sensorData.getValue(self.sensorData) - self.sensorData.getAvgValue(self.sensorData)) >= self.alertDiff):
            print('\n Current temp exceeds average by > ' + str(self.alertDiff) + '. Converting data...')
#             output = self.sensorData.__str__(self.sensorData)
            output = self.datautil.toJsonFromSensorData(self.datautil, self.sensorData)
            self.datautil.toJsonfileFromSensorData(self.datautil, output)
            print('Json data:' + str(output))
            self.connector.publishMessage("Excessive Temp", output)
            print("Sending email success!")
    
    '''
    Compare the current value to the nominal temp values, if not execute the actuator. 
    '''
    def adjust(self):
        if self.sensorData.getValue(self.sensorData) < float(self.nominalTemp) or self.sensorData.getValue(self.sensorData) > float(self.nominalTemp):
            self.actuatorData = ActuatorData()
            self.actuatorData.updateData(1, 1, 0, "adjust", self.sensorData.getValue(self.sensorData))
            self.tempActuatorEmulator.processMessage(self.actuatorData)
        
    '''
    Implement a thread to do the task. 
    '''
    def run(self):
        while True:
            self.readTempFromSH()
            self.addTempToSensorData()
            self.alert()
            #self.adjust()
            sleep(self.rateInSec)
        
        
