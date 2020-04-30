'''
@author: Mengfan Shi
'''
from labs.module05.TempSensorAdaptor import TempSensorAdaptor 
from labs.common.SensorData import SensorData

'''
run every 1 second, the alertdiff is set to 3
'''
test = TempSensorAdaptor(3, 1)