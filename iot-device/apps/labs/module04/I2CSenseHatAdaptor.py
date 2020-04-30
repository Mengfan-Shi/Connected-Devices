'''
@author: Mengfan Shi
'''

import smbus
from time import sleep
from labbenchstudios.common.ConfigUtil import ConfigUtil
from labbenchstudios.common import ConfigConst
import logging
import threading

i2cBus = smbus.SMBus(1) # Use I2C bus No.1 on Raspberry Pi3 +
enableControl = 0x2D
enableMeasure = 0x08
accelAddr = 0x1C # address for IMU (accelerometer)
magAddr = 0x6A # address for IMU (magnetometer)
pressAddr = 0x5C # address for pressure sensor
humidAddr = 0x5F # address for humidity sensor
begAddr = 0x28
totBytes = 6
DEFAULT_RATE_IN_SEC = 5

'''
Get relative humidity from I2cbus 
'''

class I2CSenseHatAdaptor(threading.Thread):
    rateInSec = DEFAULT_RATE_IN_SEC
    humidity = 0.0

    def __init__(self):
        super(I2CSenseHatAdaptor, self).__init__()
        self.config = ConfigUtil(ConfigConst.DEFAULT_CONFIG_FILE_NAME)
        self.config.loadConfig()
        print('Configuration data...\n' + str(self.config))
        self.initI2CBus()

    def initI2CBus(self):
        logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        logging.info("Initializing I2C bus and enabling I2C addresses...")
#         i2cBus.write_byte_data(accelAddr, 0, 0) 
#         i2cBus.write_byte_data(magAddr, 0, 0)
#         i2cBus.write_byte_data(pressAddr, 0, 0)
        # Select average configuration register
        i2cBus.write_byte_data(humidAddr, 0, 0)
        
    def displayHumidityData(self):
        # Humidity Calibration values
        #Read data back from 0x30(48), 1 byte
        val1 = i2cBus.read_byte_data(0x5f, 0x30)
        H0_rH = val1 /2
        # Read data back from 0x31(49), 1 byte
        val2 = i2cBus.read_byte_data(0x5f, 0x31)
        H1_rH= val2 /2
        # Read data back from 0x36(54), 2 bytes
        val3 = i2cBus.read_byte_data(0x5f, 0x36)
        val4 = i2cBus.read_byte_data(0x5f, 0x37)
        H0_T0_OUT = ((val4 & 0xFF) * 256) + (val3 & 0xFF)
        # Read data back from 0x3A(58), 2 bytes
        val5 = i2cBus.read_byte_data(0x5F, 0x3A)
        val6 = i2cBus.read_byte_data(0x5F, 0x3B)
        H1_T0_OUT = ((val6 & 0xFF) * 256) + (val5 & 0xFF)
        # Read data back from 0x28,0x29: humidity msb, humidity lsb
        val7 = i2cBus.read_byte_data(0x5F, 0x28)
        val8 = i2cBus.read_byte_data(0x5F, 0x29)
        H_OUT = ((val8 & 0xFF) * 256) + (val7 & 0xFF)
        # Read data back from 0x28(40) with command register 0x80(128), 6 bytes
        data = i2cBus.read_i2c_block_data(0x5f, 0x28 | 0x80, 6)
        #Convert to human-readable data
        self.humidity = ((1.0 * H1_rH) - (1.0 * H0_rH)) * ((1.0 * H_OUT) - (1.0 * H0_T0_OUT)) / ((1.0 * H1_T0_OUT) - (1.0 * H0_T0_OUT)) + (1.0 * H0_rH)        
#         logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        d1 = 'Relative Humidity: ' + str(self.humidity)
        d2 = 'Relative Humidity block data: ' + str(data)
        logging.info(d1)
        logging.info(d2)
    
    def run(self):
        while True:
                # NOTE: you must implement these methods
#                 self.displayAccelerometerData()
#                 self.displayMagnetometerData()
#                 self.displayPressureData()
            self.displayHumidityData()
            sleep(self.rateInSec)
        
        
        
        
        
        