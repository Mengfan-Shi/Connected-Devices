'''
@author: Mengfan Shi
'''
import logging
import _thread
import datetime
from sense_hat import SenseHat
from labs.common.SensorData import SensorData
from labs.common.ActuatorData import ActuatorData
from labs.module03.SmtpClientConnector import SmtpClientConnector
from time import sleep
from labs.module03.TempActuatorEmulator import TempActuatorEmulator
from labbenchstudios.common.ConfigUtil import ConfigUtil
from labbenchstudios.common import ConfigConst


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
    Initial all the variable I need
    '''
    def __init__(self, a, r):
        '''
        Constructor
        '''
        self.sense = SenseHat()
        self.tempActuatorEmulator = TempActuatorEmulator()
        self.alertDiff = a
        self.rateInSec = r
        self.sensorData = SensorData
        self.connector = SmtpClientConnector()
        self.config = ConfigUtil('../../../config/ConnectedDevicesConfig.props')
        self.config.loadConfig()
        self.nominalTemp = self.config.getProperty(ConfigConst.CONSTRAINED_DEVICE, 'nominalTemp')
#         self.nominalTemp = self.config.getProperty()
        _thread.start_new_thread(self.run())
    
    '''
    Connect to senseHat and obtain the environment temperature.
    '''
    def readTempFromSH(self):
        self.sense.clear()
        self.curDegree = self.sense.get_temperature()
    
    '''
    Store the temperature into SenseData.
    '''
    def addTempToSensorData(self):
        self.sensorData.addValue(self.sensorData, self.curDegree)
        now = datetime.datetime.now()
        print(str(now) + "\t" + str(self.curDegree))
        print(str(now) + "\t" + str(self.sensorData.getAvgValue(self.sensorData)))
        print("\n")
    
    '''
    Determine whether or not the particular temperature is higher or lower than the average temperature and then alert the user by sending email.
    '''
    def alert(self):
        if (abs(self.sensorData.getValue(self.sensorData) - self.sensorData.getAvgValue(self.sensorData)) >= self.alertDiff):
            # Start to send email
            logging.info('\n Current temp exceeds average by > ' + str(self.alertDiff) + '. Triggering alert...')
            print("Starting sending email...")
            output = self.sensorData.__str__(self.sensorData)
            self.connector.publishMessage("Excessive Temp", output)
    
    '''
    Determine whether or not the new temperature value is higher or lower than the nominal temperature which is set in ConnectedDevicesConfig
    '''
    def adjust(self):
        if self.sensorData.getValue(self.sensorData) < float(self.nominalTemp) or self.sensorData.getValue(self.sensorData) > float(self.nominalTemp):
            self.actuatorData = ActuatorData()
            self.actuatorData.updateData(1, 1, 0, "adjust", self.sensorData.getValue(self.sensorData))
            self.tempActuatorEmulator.processMessage(self.actuatorData)
    
    '''
    Start a new thread to do the task.
    '''
    def run(self):
        while True:
            self.readTempFromSH()
            self.addTempToSensorData()
            self.alert()
            self.adjust()
            sleep(self.rateInSec)
        
        
