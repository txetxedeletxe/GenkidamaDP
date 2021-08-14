from ..connection import Connection

class Connector:
    def connect(self) -> Connection: return self._connect()
    
    def _connect(self,*args,**kwargs): raise NotImplementedError()