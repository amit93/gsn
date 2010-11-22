
__author__      = "Tonio Gsell <tgsell@tik.ee.ethz.ch>"
__copyright__   = "Copyright 2010, ETH Zurich, Switzerland, Tonio Gsell"
__license__     = "GPL"
__version__     = "$Revision: 2381 $"
__date__        = "$Date: 2010-11-15 14:51:38 +0100 (Mon, 15. Nov 2010) $"
__id__          = "$Id: BackLogStatusPlugin.py 2381 2010-11-15 13:51:38Z tgsell $"
__source__      = "$URL: https://gsn.svn.sourceforge.net/svnroot/gsn/branches/permasense/gsn/tools/backlog/python/BackLogStatusPlugin.py $"

import struct
import time
from threading import Event

import BackLogMessage
from AbstractPlugin import AbstractPluginClass

DEFAULT_STATUS_INTERVAL = 10.0
DEFAULT_BACKLOG = True

class BackLogStatusPluginClass(AbstractPluginClass):
    '''
    This plugin offers the functionality to receive commands from the GSN Backlog wrapper.
    It also sends BackLogStatus messages.
    
    Any new status information coming from this program should be implemented here.
    '''

    '''
    data/instance attributes:
    _startTime
    _interval
    _stopped
    _sleeper
    '''

    def __init__(self, parent, config):
        self._startTime = time.time()
        AbstractPluginClass.__init__(self, parent, config, DEFAULT_BACKLOG)
        
        value = self.getOptionValue('poll_interval')
        if value is None:
            self._interval = DEFAULT_STATUS_INTERVAL
        else:
            self._interval = float(value)
        
        self.info('interval: ' + str(self._interval))

        self._stopped = False
        self._sleeper = Event()
    
    
    def getMsgType(self):
        return BackLogMessage.BACKLOG_STATUS_MESSAGE_TYPE
        
        
    def isBusy(self):
        return False
    
    
    def msgReceived(self, message):
        if message[0] == 1:
            self.info('received command resend')
            self._backlogMain.resend()


    def run(self):
        self.info('started')
        while not self._stopped:
            self._sleeper.wait(self._interval)
            if self._sleeper.isSet():
                continue
            
            packet = struct.pack('<i', self.getErrorCounter())
            packet += struct.pack('<i', self.getExceptionCounter())
            backlogstatus = self.getBackLogStatus()
            backlogdbentries = int(backlogstatus[0])
            backlogdbsize = int(backlogstatus[1])
            minstoretime = int(backlogstatus[2])
            maxstoretime = int(backlogstatus[3])
            meanstoretime = int(backlogstatus[4])
            minremovetime = int(backlogstatus[5])
            maxremovetime = int(backlogstatus[6])
            meanremovetime = int(backlogstatus[7])
            packet += struct.pack('<i', backlogdbentries)
            packet += struct.pack('<i', backlogdbsize)
            gsnpeerstatus = self.getGSNPeerStatus()
            incounter = int(gsnpeerstatus[0])
            outcounter = int(gsnpeerstatus[1])
            backlogcounter = int(gsnpeerstatus[2])
            connectionLosses = int(gsnpeerstatus[3])
            uptime = int(time.time()-self._startTime)
            packet += struct.pack('<i', incounter)
            packet += struct.pack('<i', outcounter)
            packet += struct.pack('<i', backlogcounter)
            packet += struct.pack('<i', connectionLosses)
            packet += struct.pack('<i', uptime)
            packet += struct.pack('<i', minstoretime)
            packet += struct.pack('<i', maxstoretime)
            packet += struct.pack('<i', meanstoretime)
            packet += struct.pack('<i', minremovetime)
            packet += struct.pack('<i', maxremovetime)
            packet += struct.pack('<i', meanremovetime)
            
            self.processMsg(self.getTimeStamp(), packet, self._priority, self._backlog)
            
        self.info('died')
        
    
    def stop(self):
        self._stopped = True
        self._sleeper.set()
        self.info('stopped')
