'''
@author: apple
'''
import paho.mqtt.client as mqtt
from labs.common.SensorData import SensorData
from labs.common.DataUtil import DataUtil
from time import sleep
import logging

'''
This class defines functions to connect to the MQTT broker.x
'''
class MqttClientConnector(object):
    '''
    intialize the mqtt client
    '''
    client = mqtt.Client()
    '''
    connect to the broker
    '''
    def on_message(self,client, userdata, msg):
        logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        logging.info("Received Json: "+msg.topic+" "+str(msg.payload))
        # Convert the received json data to sensordata instance
        self.sensorData = SensorData
        self.sensorData = DataUtil.toSensorDataFromJson(self,str(msg.payload)[1:].lstrip('\'').rstrip('\''))
        # Convert the instance to json again
        data = DataUtil.toJsonFromSensorData(self, self.sensorData)
        # Log new JSON string.
        logging.info("The new JSON string: "+data)
        
    def disconnect(self,topic):
        self.client.unsubscribe(topic)
        self.client.disconnect()
        print("Disconnected from host...")
        
    def subscribe(self,topic,QoS):
        print("On subscribe to the topic: " + topic + " with qos in " + str(QoS))
        self.client.subscribe(topic,QoS)
        
    def keep_listen(self,time):
        print("Keep alive for " + str(time) + " seconds.")
        self.client.loop_start()
        sleep(time)
        self.client.loop_stop()
        
    def connect(self,address,timeout):
        print("Starting to connect to host" + address)
        self.client.on_connect = self.on_connect
        self.client.on_message = self.on_message
        self.client.connect(address, 1883, timeout)