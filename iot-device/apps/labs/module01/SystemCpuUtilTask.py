'''
@author: Mengfan Shi
'''
import psutil

class SystemCpuUtilTask(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
        self.cpu = psutil.cpu_percent()
    
    def getDataFromSensor(self):
        
        return self.cpu