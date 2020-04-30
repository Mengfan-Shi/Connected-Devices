'''
@author: Mengfan Shi
'''
import _thread
from sense_hat import SenseHat
from time import sleep
import logging
from labs.module04.I2CSenseHatAdaptor import I2CSenseHatAdaptor
import threading

DEFAULT_RATE_IN_SEC = 5
#Read humidity data from sense_hat library
class SenseHatDeviceAdaptor(threading.Thread):
    humid = 0.0
    
    def __init__(self):
        self.sense = SenseHat()
        self.rateInSec = DEFAULT_RATE_IN_SEC
            
    def readHumidityfromSH(self):
        self.sense.clear()
        #read humidity data from sense_hat function get_humidity()
        self.humid = self.sense.get_humidity()
        logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        data = 'Humidity:' + str(self.humid)
        logging.info(data)
        #compare two humidity values
#         i = I2CSenseHatAdaptor
#         accuracy = abs(float(i.humidity) - float(self.humid)) / float(i.humidity)
#         print('accuracy:' + accuracy) 
        
                
    def run(self):
        while True:
            self.readHumidityfromSH()
            sleep(self.rateInSec)
        
        