'''

@author: Mengfan Shi
'''


from project.MqttSubConnector import MqttSubConnector

BROKER_ENDPOINT = "industrial.api.ubidots.com"
TLS_PORT = 8883  # Secure port
MQTT_USERNAME = "BBFF-uNAPBBY8FJo5XYAIBLrSamz8N9WCKa"  # Put here your Ubidots TOKEN
MQTT_PASSWORD = ""  # Leave this in blank
TOPIC = "/v1.6/devices/"
DEVICE_LABEL = "humidifier/humiditythreshold/lv"
QoS = 0
time = 300

testMqtt = MqttSubConnector()
'''
connect to host using token and port
'''
testMqtt.connect(MQTT_USERNAME, MQTT_PASSWORD, BROKER_ENDPOINT, TLS_PORT)
'''
set topic format, connection lasts for 5 minutes
'''
topic = "{}{}".format(TOPIC, DEVICE_LABEL)
testMqtt.subscribe(topic, QoS)
testMqtt.keep_listen(time)
testMqtt.disconnect(topic)   