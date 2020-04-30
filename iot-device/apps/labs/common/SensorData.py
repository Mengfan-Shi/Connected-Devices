'''
@author: Mengfan Shi
'''
import os
from datetime import datetime

class SensorData(object):

    timeStamp = None
    name      = 'Humidifier'
#     curValue  = 0
    curHumid  = 0
    avgHumid  = 0
    curTemp   = 0
    avgTemp   = 0
    minHumid  = 0
    minTemp   = 0
    maxHumid  = 0
    maxTemp   = 0
#     avgValue  = 0
#     minValue  = 0
#     maxValue  = 0
    totHumid  = 0
    totTemp   = 0
    HumidCount = 0
    TempCount  = 0
    threshold  = 0

    def __init__(self):
        '''
        Constructor
        '''
        self.timeStamp = str(datetime.now())
        
    def createSD(self, name, timeStamp, curHumid, avgHumid, curTemp, avgTemp,minHumid, maxHumid,minTemp, maxTemp, totHumid, totTemp, HumidCount, TempCount):
        self.name = name
        self.timeStamp = timeStamp
        self.curHumid = curHumid
        self.avgHumid = avgHumid
        self.curTemp  = curTemp
        self.avgTemp  = avgTemp
        self.minHumid = minHumid
        self.maxHumid = maxHumid
        self.minTemp  = minTemp
        self.maxTemp  = maxTemp
        self.totHumid = totHumid
        self.totTemp = totTemp
        self.HumidCount = HumidCount
        self.TempCount = TempCount
#         self.minValue = minValue
#         self.maxValue = maxValue
#         self.totValue = totValue
#         self.sampleCount = sampleCount
        return self    
            
    def addHumid(self, newVal):
        self.HumidCount += 1
        self.timeStamp = str(datetime.now())
        self.curHumid = newVal
        self.totHumid += newVal
        
        if (self.curHumid < self.minHumid):
            self.minHumid = self.curHumid
        if (self.curHumid > self.maxHumid):
            self.maxHumid = self.curHumid        
        if (self.totHumid != 0 and self.HumidCount > 0):
            self.avgHumid = self.totHumid / self.HumidCount
    
    def addTemp(self, newVal):
        self.TempCount += 1
        self.timeStamp = str(datetime.now())
        self.curTemp = newVal
        self.totTemp += newVal
        
        if (self.curTemp < self.minTemp):
            self.minTemp = self.curTemp
        if (self.curTemp > self.maxTemp):
            self.maxTemp = self.curTemp        
        if (self.totTemp != 0 and self.TempCount > 0):
            self.avgTemp = self.totTemp / self.TempCount
    
    def addthreshold(self, newVal):
        self.threshold = newVal
        
    '''
    for assignemnt5, add getTimeStamp(), getTotValue(). getsampleCount() these three functions. 
    '''
    def getTimeStamp(self):
        return self.timeStamp
    
    def getName(self):
        return self.name
        
    def getAvgHumid(self):
        return self.avgHumid
    
    def getAvgTemp(self):
        return self.avgTemp
    
    def getMaxHumid(self):
        return self.maxHumid
    
    def getMaxTemp(self):
        return self.maxTemp
    
    def getMinHumid(self):
        return self.minHumid
    
    def getMinTemp(self):
        return self.maxTemp

    def getHumid(self):
        return self.curHumid
    
    def getTemp(self):
        return self.curTemp
    
    def setName(self, name):
        self.name = name
        
    def getTotHumid(self):
        return self.totHumid
    
    def getTotTemp(self):
        return self.totTemp
    
    def getHumidCount(self):
        return self.HumidCount
    
    def getTempCount(self):
        return self.TempCount
    
    def getthreshold(self):
        return self.threshold
    
    def __str__(self):
        customStr = \
                str(self.name + ':' + \
                os.linesep + '\tTime:    ' + self.timeStamp + \
                os.linesep + '\tCurrent Humidity: ' + str(self.curHumid) + \
                os.linesep + '\tAverage Humidity: ' + str(self.avgHumid) + \
                os.linesep + '\tCurrent Temperature: ' + str(self.curTemp)) + \
                os.linesep + '\tAverage Temperature: ' + str(self.avgTemp) + \
                os.linesep + '\tCurrent Threshold: ' + str(self.threshold)
#                 os.linesep + '\tSamples: ' + str(self.sampleCount) + \
#                 os.linesep + '\tMin:       ' + str(self.minValue) + \
#                 os.linesep + '\tMax:       ' + str(self.maxValue))
            
        return customStr
     
