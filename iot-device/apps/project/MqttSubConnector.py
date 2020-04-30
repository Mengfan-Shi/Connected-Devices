'''

@author: Mengfan Shi
'''

from time import sleep
import paho.mqtt.client as mqtt
import ssl
from sense_hat import SenseHat
import json

TLS_CERT_PATH = "/home/pi/Desktop/ubidots_cert.pem"
connected = False  # Stores the connection status
class MqttSubConnector(object):
    '''
    Subscribe from the threshold topic on Ubidots 
    '''
    def __init__(self):
        '''
        Constructor
        '''
        self.client = mqtt.Client()
        self.sh = SenseHat()  
    def on_connect(self,client,userdata, flags, rc):
        if rc == 0:
            print("start to connect...")
            global connected  # Use global variable
            connected = True  # Signal connection
        else:
            print("Connection failed.")
    '''
    set SenseHat screen message based on humidity threshold
    '''
    def on_message(self,client, userdata, msg):
        print("\tReceived JSON msg: "+str(msg.payload))
        self.humidity = self.sh.get_humidity()
        data = json.loads(msg.payload)
        if(self.humidity < data):
            self.sh.show_message("Decrease!")
        elif self.humidity < 20:
            self.sh.show_message("Increase!")
        else:
            self.sh.show_message("Keep!")        
        
    def disconnect(self,topic):
        self.client.unsubscribe(topic)
        print("Unsubscribe topic: " + topic)
        self.client.disconnect()
        print("Disconnected from host!")
    #subscribe function    
    def subscribe(self,topic,QoS):
        print("Subscribe to the topic: " + topic + " with a QoS in " + str(QoS))
        self.client.subscribe(topic,QoS)
    #keep connection alive   
    def keep_listen(self,time):
        print("Open this connection for " + str(time) + " seconds.")
        self.client.loop_start()
        sleep(time)
        self.client.loop_stop()
    #connect to host enable tls    
    def connect(self, mqtt_username, mqtt_password, broker_endpoint, port):
        global connected
        if not connected:
            self.client.username_pw_set(mqtt_username, password=mqtt_password)
            self.client.on_connect = self.on_connect
            self.client.on_message = self.on_message
            
            self.client.tls_set(ca_certs=TLS_CERT_PATH, certfile=None,
                                keyfile=None, cert_reqs=ssl.CERT_REQUIRED,
                                tls_version=ssl.PROTOCOL_TLSv1_2, ciphers=None)
            self.client.tls_insecure_set(False)
            self.client.connect(broker_endpoint, port=port)
            self.client.loop_start()
            attempts = 0
            while not connected and attempts < 5:  # Wait for connection
                print(connected)
                print("starting to connect...")
                sleep(1)
                attempts += 1
        if not connected:
            print("Could not connect!")
            return False
        return True  