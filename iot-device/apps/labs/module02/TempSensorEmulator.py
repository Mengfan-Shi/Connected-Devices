'''
@author: Mengfan Shi
'''
import random
import logging
from labs.common.SensorData import SensorData
from labs.module02.SmtpClientConnector import SmtpClientConnector

'''
Get real-time temperature values from sensehat , each value is stored in the SensorData Class using addValue method;
When the temp exceeds the threshold , send alert email to specified address.
'''
class TempSensorEmulator(object):
    '''
    classdocs
    '''  

    def getSensorData(self):
        d = random.uniform(0,30)
        SensorData.addValue(d)
        self.sendNotification()
        
    
    def sendNotification(self):
        if(abs(SensorData.getValue() - SensorData.getAvgValue()) >= self.alertDiff):
#             print(SensorData.curValue)
#             print(SensorData.sampleCount)
            logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
            logging.info('\n Current temp exceeds average by > ' + str(self.alertDiff) + '.Triggering alert...')
            data = SensorData.__str__()
            connector = SmtpClientConnector()
            connector.publishMessage('Excessive Temp', data)
            
            #connector.publishMessage("Excessive Temp", self.getSensorData())
    
    def __init__(self, params):
        '''
        Constructor
        '''
        self.alertDiff = params
        

 
          
