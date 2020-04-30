'''
@author: Mengfan Shi
'''
from time import sleep
from labs.module01 import SystemCpuUtilTask
import logging
from labs.module01 import SystemMemUtilTask

class SystemPerformanceAdaptor(object):
    '''
    The run() method invokes the CPU Task and Memory Task to call the two values: cpuUtil and memUtil;
    sets the log output format and print out the two logs every 5 seconds;
    the total run times of this method is 10 times.
    '''
    def run(self):
        for i in range(10): 
        
            '''
            Constructor
            ''' 
            cpuUtil = SystemCpuUtilTask.SystemCpuUtilTask().getDataFromSensor()
            memUtil = SystemMemUtilTask.SystemMemUtilTask().getDataFromSensor()
            logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
            perfData = 'CPU Utilization=' + str(cpuUtil) + ';'
            logging.info(perfData)
            perfData = 'Memory Utilization=' + str(memUtil) + ';'
            logging.info(perfData)
            sleep(5)
            
        
        
       