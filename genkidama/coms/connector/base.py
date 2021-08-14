from .interface import Connector

class ConnectorBase(Connector):
    def __init__(self,connection_args=None):
        self.connection_args = connection_args or dict()
        self._build()
    
    def _build(self): pass
    
class IPConnector(ConnectorBase):
    hostname="localhost"
    port=22222
    def __init__(self,
            hostname=None,
            port=None,
            connection_args=None):
        
        if hostname: self.hostname = hostname
        if port: self.port = port
            
        super().__init__(connection_args=connection_args)
        
class MetaConnector(ConnectorBase):
    def __init__(self,connection_cls : type):
        self.connection_cls = connection_cls
        
        super().__init__(connection_args=connection_args)
        
    def _build(self):
        init = self.connection_cls.__init__
        init_argsnames = init.__code__.co_varnames[2:]
        
        arg_keys = tuple(filter(init_argsnames.__contains__,self.connection_args))
        arg_val = map(self.connection_args.__getitem__,arg_keys)
        
        arg_dict = dict(zip(arg_keys,arg_val))
        self._arg_dict = arg_dict
    
    def _connect(self):
        con_obj = self._get_connection_object()
        return self.connection_cls(con_obj,**self._arg_dict)
    
    def _get_connection_object(self,*args,**kwargs): raise NotImplementedError()