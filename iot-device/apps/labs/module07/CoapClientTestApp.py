'''
@author: Mengfan Shi
'''
from labs.module07.CoapClientConnector import CoapClientConnector
'''
connect to local host
'''
host = "coap://127.0.0.1:5683/temp"
test = CoapClientConnector(host)
test.test()
        
        