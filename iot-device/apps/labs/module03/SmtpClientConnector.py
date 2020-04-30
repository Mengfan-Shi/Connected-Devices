'''
@author: Mengfan Shi
'''
from labbenchstudios.common.ConfigUtil import ConfigUtil
import logging
from labbenchstudios.common import ConfigConst
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import smtplib
from labs.common.SensorData import SensorData
from labs.common.ActuatorData import ActuatorData


class SmtpClientConnector(object):
    '''
    configure the remote server to send alert email;
        whenever an email is sent, log out the success message
    '''
    def __init__(self):
        '''
        Constructor
        '''  
        self.config = ConfigUtil('../../../config/ConnectedDevicesConfig.props')
#         self.config = ConfigUtil('/Users/apple/workspace/iot-device/config/ConnectedDevicesConfig.props')
        self.config.loadConfig()
        logging.info('Configuration data...\n' + str(self.config))
     
    def publishMessage(self, topic, data):
        host = self.config.getProperty(ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.HOST_KEY)
        port = self.config.getProperty(ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.PORT_KEY)
        fromAddr = self.config.getProperty(ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.FROM_ADDRESS_KEY)
        toAddr = self.config.getProperty(ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.TO_ADDRESS_KEY)
        authToken = self.config.getProperty(ConfigConst.SMTP_CLOUD_SECTION, ConfigConst.USER_AUTH_TOKEN_KEY)
#         nominalTemp = self.config.getProperty(ConfigConst.CONSTRAINED_DEVICE, 'nominalTemp')
#         if(SensorData.getValue()):
#             actuate = ActuatorData()
        
        
        msg = MIMEMultipart()
        msg['From'] = fromAddr
        msg['To'] = toAddr
        msg['Subject'] = topic
        msgBody = str(data)
         
        msg.attach(MIMEText(msgBody))
         
        msgText = msg.as_string()
         
        smtpServer = smtplib.SMTP_SSL(host, port)
        smtpServer.ehlo()
        smtpServer.login(fromAddr, authToken)
        smtpServer.sendmail(fromAddr, toAddr, msgText)
        smtpServer.close()
        logging.info('Sending Successful!\n\n')
