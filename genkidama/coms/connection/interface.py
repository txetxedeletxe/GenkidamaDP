class Connection(object):
    def recv(self) -> object: return self._recv()
    def send(self,payload : object): return self._send(payload)
    def close(self): return self._close()

    # Implementation hooks
    def _recv(self,*args,**kwargs): raise NotImplementedError()
    def _send(self,*args,**kwargs): raise NotImplementedError()
    def _close(self,*args,**kwargs): raise NotImplementedError()

class ByteConnection(Connection):
    def recv(self) -> bytes: return self._recv()
    def send(self, payload : bytes): return self._send(payload)

class GenkiadamaConnection(Connection):
    def recv(self) -> "GenkidamaPacket": return self._recv()
    def send(self, payload : "GenkidamaPacket"): return self._send(payload)