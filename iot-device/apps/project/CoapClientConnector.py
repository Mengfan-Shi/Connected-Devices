'''
@author: Mengfan Shi
'''
from coapthon.utils import parse_uri
from coapthon.client.helperclient import HelperClient

class CoapClientConnector(object):
    '''
    In the project, only use POST request to send data from Raspberry Pi to Gateway(laptop)
    '''

    def __init__(self, host):
        '''
        '''
        host, port, self.path = parse_uri(host)
        self.client = HelperClient(server = (host, port))
    
    def handleGET(self):
        response = self.client.get(self.path)
        print(response)
        
    def handlePOST(self, payload):
        response = self.client.post(self.path, payload)
        print(response)
    
    def handlePUT(self, payload):
        response = self.client.put(self.path, payload)
        print(response)
    
    def handleDELETE(self):
        response = self.client.delete(self.path)
        print(response)
    
    def test(self, payload):
        self.handlePUT(payload)
        
        