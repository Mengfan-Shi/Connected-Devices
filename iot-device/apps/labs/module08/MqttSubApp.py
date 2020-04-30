'''

@author: Mengfan Shi
'''


from labs.module08.MqttSubConnector import MqttSubConnector

BROKER_ENDPOINT = "industrial.api.ubidots.com"
TLS_PORT = 8883  # Secure port
MQTT_USERNAME = "BBFF-uNAPBBY8FJo5XYAIBLrSamz8N9WCKa"  # Put here your Ubidots TOKEN
MQTT_PASSWORD = ""  # Leave this in blank
TOPIC = "/v1.6/devices/"
DEVICE_LABEL = "tempmonitor/tempactuator/lv"
_QoS = 0
_time = 300

testMqtt = MqttSubConnector()
'''
connect to host using token and port
'''
testMqtt.connect(MQTT_USERNAME, MQTT_PASSWORD, BROKER_ENDPOINT, TLS_PORT)
'''
set topic format
'''
topic = "{}{}".format(TOPIC, DEVICE_LABEL)
testMqtt.subscribe(topic, _QoS)
testMqtt.keep_listen(_time)
testMqtt.disconnect(topic)   