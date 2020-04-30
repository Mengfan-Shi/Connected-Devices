'''
@author: Mengfan Shi
'''
import json
from labs.common.ActuatorData import ActuatorData
from labs.common.SensorData import SensorData

class DataUtil(object):
    '''
    Export the output result to a json file. 
    '''
    def toJsonfileFromSensorData(self, jsondata):
        fileObject = open('SensorData.json', 'w')
        fileObject.write(jsondata)
        fileObject.close()
    '''
    Use dictionary to convert a sensordata object to json string. 
    '''   
    def toJsonFromSensorData(self, sensorData):
        SD = {}
        SD["timeStamp"] = sensorData.getTimeStamp(sensorData)
        SD["name"] = sensorData.getName(sensorData)
        SD["curHumidity"] = sensorData.getHumid(sensorData)
        SD["avgHumidity"] = sensorData.getAvgHumid(sensorData)
        SD["curTemperature"] = sensorData.getTemp(sensorData)
        SD["avgTemperature"] = sensorData.getAvgTemp(sensorData)
        SD["threshold"] = sensorData.getthreshold(sensorData)
#         SD["totValue"] = sensorData.getTotValue(sensorData)
#         SD["sampleCount"] = sensorData.getsampleCount(sensorData)
        jsondata = json.dumps(SD)
        DataUtil.toJsonfileFromSensorData(self, jsondata)
        return jsondata
       
#         print(self.jsonData)
    '''
    Convert the json string to a sensor data instance. 
    '''
    def toSensorDataFromJson(self, jsonData):
        self.sensorData = SensorData
        data = json.loads(jsonData)
        name = data['name']
        timeStamp = data['timeStamp']
        avgValue = data['avgValue']
        minValue = data['minValue']
        maxValue = data['maxValue']
        curValue = data['curValue']
        totValue = data['totValue']
        sampleCount = data['sampleCount']
        self.sensorData = SensorData.createSD(self.sensorData,name,timeStamp, curValue, avgValue, minValue, maxValue, totValue, sampleCount)
        return self.sensorData
#     def toSensorDataFromJsonFile(self):
#         
#     def toJsonfromActuatorData(self):
#         
#     def toActuatorDataFromJson(self, jsonData):
#         adDict = json.loads(jsonData)
#         ad = ActuatorData()
#         ad.name = adDict['name']
#         ad.timeStamp = adDict['timeStamp']
#         ad.hasError = adDict['hasError']
#         ad.command = adDict['command']
#         ad.errCode = adDict['errCode']
#         ad.statusCode = adDict['statusCode']
#         ad.stateData = adDict['stateData']
#         ad.curValue = adDict['curValue']
#         return ad
#         
#     def toActuatorDataFromJsonfile(self):
#     

        