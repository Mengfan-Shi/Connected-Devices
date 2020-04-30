'''
@author: Mengfan Shi
'''
from labs.common.SensorData import SensorData
from coapthon.client.helperclient import HelperClient
from coapthon.utils import parse_uri
from labs.common.DataUtil import DataUtil
import logging

class CoapClientConnector(object):
    '''
    add one sample data to initiate a sensordata instance and convert it to json
    '''
    def __init__(self, host):
        self.sensorData = SensorData
        self.sensorData.addValue(self.sensorData,23.34)
        self.data = DataUtil.toJsonFromSensorData(self, self.sensorData)
        logging.basicConfig(format='%(asctime)s:%(levelname)s:%(message)s', level=logging.DEBUG)
        logging.info("Json to send: " + self.data)
        host, port, self.path = parse_uri(host)
        self.client = HelperClient(server = (host, port))
    '''
    GET method in protocol CoAP
    '''   
    def handleGET(self):
        response = self.client.get(self.path)
        print(response) 
    '''
    POST method in protocol CoAP
    '''
    def handlePOST(self, payload):
        response = self.client.post(self.path, payload)
        print(response)
    '''
    PUT method in protocol CoAP
    '''
    def handlePUT(self, payload):
        response = self.client.put(self.path, payload)
        print(response)
    '''
    DELETE method in protocol CoAP
    '''
    def handleDELETE(self):
        response = self.client.delete(self.path)
        print(response)
    '''
    test all theses four methods
    '''
    def test(self):
        self.handleGET()
        self.handlePOST(self.data)
        self.handlePUT(self.data)
        self.handleDELETE()
           
        
        
        