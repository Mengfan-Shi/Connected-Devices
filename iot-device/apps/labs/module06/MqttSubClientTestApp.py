'''
@author: apple
'''
from labs.module06.MqttClientConnector import MqttClientConnector
import logging
from time import sleep
'''
Subscribe "test" topic from the remote mqtt broker, convert the received json data to sensordata instance then convert it to json again.
'''
class MqttSubClientTestApp(object):
    address = '127.0.0.1'
    timeout = 30
    topic = "test"
    qos = 2
    time = 65

    connector = MqttClientConnector()
    connector.client.connect(address, timeout)
    connector.on_subscribe("topic", qos)
    connector.keep_listen(time)
    connector.disconnect(topic)
