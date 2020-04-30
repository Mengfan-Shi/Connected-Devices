'''
@author: Mengfan Shi
'''
from labs.module02.TempSensorEmulator import TempSensorEmulator
from labs.common.SensorData import SensorData
from time import sleep

'''
Set the temp diff threshold as 5
'''
test = TempSensorEmulator(5)
'''
Generate random temp value every 5 seconds.
'''
while True:
    test.getSensorData()
    sleep(15)  
