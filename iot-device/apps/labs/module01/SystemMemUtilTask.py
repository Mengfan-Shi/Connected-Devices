'''

@author: Mengfan Shi
'''
import psutil

class SystemMemUtilTask(object):
    '''
    classdocs
    '''


    def __init__(self):
        '''
        Constructor
        '''
        self.memory = psutil.virtual_memory().percent
    
    def getDataFromSensor(self):
        
        return self.memory