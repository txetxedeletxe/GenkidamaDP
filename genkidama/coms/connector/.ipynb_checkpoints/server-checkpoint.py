from .base import IPConnector
from ..connection import SocketConnection, GenkidamaThroughByteConnection

import socket
class IPServer(IPConnector): # TODO generalize this to other than TCP
    def _connect(self): 
        kwargs = self.connection_args
        socket_init = SocketConnection.__init__
        
        # Parse relevant args
        init_argsnames = socket_init.__code__.co_varnames[2:]
        
        arg_keys = tuple(filter(init_argsnames.__contains__,kwargs))
        arg_val = map(kwargs.__getitem__,arg_keys)
        
        arg_dict = dict(zip(arg_keys,arg_val))
        
        # Connect socket
        sck = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        sck.bind((self.hostname,self.port))
        
        sck.listen(1)
        s_acc, _ = sck.accept()
        
        return SocketConnection(s_acc,**arg_dict)
        
class GenkidamaIPServer(
        IPConnector):
    
    def _connect(self):
        kwargs = self.connection_args
        gen_init = GenkidamaThroughByteConnection.__init__
        
        # Parse relevant args #TODO abstract this piece
        init_argsnames = gen_init.__code__.co_varnames[2:]
        
        arg_keys = tuple(filter(init_argsnames.__contains__,kwargs))
        arg_val = map(kwargs.__getitem__,arg_keys)
        
        arg_dict = dict(zip(arg_keys,arg_val))
        
        # Get socket connection
        con = IPServer(self.hostname,self.port,connection_args=kwargs) # Allow other IP byte connections
        con = con.connect()
        
        # Init superclass
        return GenkidamaThroughByteConnection(con,**arg_dict)