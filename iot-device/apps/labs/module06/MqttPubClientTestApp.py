'''
@author: apple
'''
import paho.mqtt.client as mqtt
from labs.common.SensorData import SensorData
from sense_hat import SenseHat
from labs.common.DataUtil import DataUtil
import logging
from labs.module06.MqttClientConnector import MqttClientConnector

'''
Get sample data from sensordata and convert it to json, publish the json data to the remote broker use the topic test.
'''
class MqttPubClientTestApp(object):
    sensorData = SensorData
    sense = SenseHat()
    curDgree = sense.get_temperature()
    sensorData.addValue(sensorData, curDgree)
    dataUtil = DataUtil
    msg = dataUtil.toJsonFromSensorData(dataUtil, sensorData)
    logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
    logging.info('Message converted to Json: \n' + str(msg))
    
    connector = MqttClientConnector()
    connector.client.on_connect = connector.on_connect
#     connector.client.on_message = connector.on_message
    connector.on_publish("test", msg, 2)
    print("Message published!")
    
    
    
    

            
    
    
    
    
    
