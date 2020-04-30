'''
@author: Mengfan Shi
'''
from labs.module03.TempActuatorEmulator import TempActuatorEmulator
from labs.module03.TempSensorAdaptor import TempSensorAdaptor 
from labs.common.SensorData import SensorData

'''
run every 0.5 seconds, the alertdiff is set to 2
'''
test = TempSensorAdaptor(2, 0.5)