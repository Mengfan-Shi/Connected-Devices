'''
@author: Mengfan Shi
'''
from labs.common.ActuatorData import ActuatorData
from project.SenseHatLedActivator import SenseHatLedActivator
import logging
from sense_hat import SenseHat

class HumidityActuatorEmulator(object):
    '''
    Compare processed humidity value and threshold, set actions on actuator(SenseHat Ledscreen)
    '''
    flag = None
    
    def __init__(self):

        self.ad = ActuatorData()
        self.sh = SenseHat()
    
    def displayMsg(self):
        logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        logging.info("Start to show message...\n")
        self.led = SenseHatLedActivator()
        self.led.setEnableLedFlag(True)
        if self.flag == 1:
            self.led.setDisplayMessage("Decrease!")
        elif self.flag == 2:
            self.led.setDisplayMessage("Increase!")
        elif self.flag == 3:
            self.led.setDisplayMessage("Keep!")
        self.led.run()
        
    def process(self, data, threshold):
        if self.ad.get_val() == 0:
            self.ad = data
        else:
            val = data.get_val()
            if val != threshold:
                if val > threshold:
                    self.flag = 1
                elif val < 20:
                    self.flag = 2 
                else:
                    self.flag = 3

                self.displayMsg()
                self.ad.updateData(data.get_command(), data.get_statusCode(), data.get_errCode(), data.get_statusCode, val)
                self.ad.updateTimeStamp()
                logging.info("ActuatorData created!\n")
                
                    
                    
        
            