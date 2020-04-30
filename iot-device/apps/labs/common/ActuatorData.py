'''
@author: apple
'''
import os
from datetime import datetime

COMMAND_OFF = 0
COMMAND_ON = 1
COMMAND_SET = 2
COMMAND_RESET = 3
STATUS_IDLE = 0
STATUS_ACTIVE = 1
ERROR_OK = 0
ERROR_COMMAND_FAILED = 1
ERROR_NON_RESPONSIBLE = -1


class ActuatorData(object):
    '''
    When the sensed temperature is different from the nominal Temp, initiate a ActuatorData instance
    to send specific messages to the sensehatledactivator ;
    set the actuator status
    '''
    timeStamp = None
    name = 'Not set'    
    hasError = False
    command = 0
    errCode = 0
    statusCode = 0
    stateData = None
    val = 0.0
    
    def __init__(self):
        '''
        Constructor
        '''
        self.updateTimeStamp()
        
    def updateData(self, cm, sc, ec, sd, vl):
        self.command = cm
        self.statusCode = sc
        self.errCode = ec
        self.stateData = sd
        self.val = vl
        
    def updateTimeStamp(self):
        self.timeStamp = str(datetime.now()) 
        
    def get_name(self):
        return self.name
    
    def get_hasError(self):
        return self.hasError
    
    def get_command(self):
        return self.command
    
    def get_errCode(self):
        return self.errCode
    
    def get_statusCode(self):
        return self.statusCode
    
    def get_stateData(self):
        return self.stateData
    
    def get_val(self):
        return self.val
    
    def get_timeStamp(self):
        return self.timeStamp
        
        