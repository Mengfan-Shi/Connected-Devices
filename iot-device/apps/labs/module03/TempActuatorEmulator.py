'''
@author: Mengfan Shi
'''
from labs.common.ActuatorData import ActuatorData
from labs.module03.SenseHatLedActivator import SenseHatLedActivator

class TempActuatorEmulator(object):
    '''
    classdocs
    '''
    diff = 0.0
    flag = None
    
    def __init__(self):
        '''
        Constructor
        '''
        self._adata = ActuatorData()
    
    '''
    After getting the differential from two degrees, start to tell the user how the temperature is going by showing message on senseHat.
    '''
    def displayMSG(self):
        print("Start to show message...")
        self.senseHatLedActivator = SenseHatLedActivator()
        self.senseHatLedActivator.setEnableLedFlag(True)
        if self.flag == True:
            self.senseHatLedActivator.setDisplayMessage("Turn down " + str(self.diff)[:4])
        elif self.flag == False:
            self.senseHatLedActivator.setDisplayMessage("Turn up " + str(self.diff)[:4])
        self.senseHatLedActivator.run()
    
    '''
    Compare the new value and the previous value of the ActuatorData.
    If different, calculate the differential value.
    After showing the message on senseHat, store the new value into ActuatorData.
    '''
    def processMessage(self,adata):
        if self._adata.get_val == 0:
            self._adata = adata;
        else:
            old_val = self._adata.get_val()
            new_val = adata.get_val()
            if old_val != new_val:
                if float(old_val) > float(new_val):
                    # If -, notice user to decrease the temperature
                    self.diff = abs(float(old_val) - float(new_val))
                    self.flag = True
                else:
                    # If +, notice user to increase the temperature
                    self.diff = abs(float(old_val) - float(new_val))
                    self.flag = False
                self.displayMSG()
                self._adata.updateData(adata.get_command(),adata.get_statusCode(),adata.get_errCode(),adata.get_stateData(),new_val)
                self._adata.updateTimeStamp()
                print("New ActuatorData instance created.")
                print(self._adata.get_timeStamp())

