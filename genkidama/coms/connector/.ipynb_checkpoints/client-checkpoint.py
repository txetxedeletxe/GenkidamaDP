from .base import IPConnector
from ..connection import SocketConnection, GenkidamaThroughByteConnection

import socket
class IPClient(IPConnector,MetaConnector): # TODO generalize this to other than TCP
    def _build(self):
        MetaConnector.__init__(self,SocketConnection)
        self.socket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
         
    def _get_connection_object(self):
        self.socket.connect((self.hostname,self.port))
        return self.socket
        
class GenkidamaIPClient(IPConnector):
    def _connect(self):
        kwargs = self.connection_args
        gen_init = GenkidamaThroughByteConnection.__init__
        
        # Parse relevant args #TODO abstract this piece
        init_argsnames = gen_init.__code__.co_varnames[2:]
        
        arg_keys = tuple(filter(init_argsnames.__contains__,kwargs))
        arg_val = map(kwargs.__getitem__,arg_keys)
        
        arg_dict = dict(zip(arg_keys,arg_val))
        
        # Get socket connection
        con = IPClient(self.hostname,self.port,connection_args=kwargs) # Allow other IP byte connections
        con = con.connect()
        
        # Init superclass
        return GenkidamaThroughByteConnection(con,**arg_dict)