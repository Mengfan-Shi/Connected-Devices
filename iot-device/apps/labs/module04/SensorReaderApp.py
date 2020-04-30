'''
@author: Mengfan Shi
'''
from labs.module04.SenseHatDeviceAdaptor import SenseHatDeviceAdaptor
from labs.module04.I2CSenseHatAdaptor import I2CSenseHatAdaptor

from threading import Thread
from time import sleep

test1 = SenseHatDeviceAdaptor()
test2 = I2CSenseHatAdaptor()

#use sleep method to run two threads separately
Thread(target = test1.run).start()
sleep(2)
Thread(target= test2.run).start()
sleep(2)

#compare two humidity data which from sensehat and I2Cbus
def Compare():
    h1 = test1.humid
    h2 = test2.humidity
    accuracy = 1.0 * abs(h1 - h2)/h1
    print("Accuracy: " + str(accuracy))
    print('\n')
    sleep(5)
     
Thread(target = Compare).start()