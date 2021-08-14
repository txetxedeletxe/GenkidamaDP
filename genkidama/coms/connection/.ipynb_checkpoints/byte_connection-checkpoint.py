from .interface import ByteConnection

import socket
class SocketConnection(ByteConnection):
    recv_buffersize = 1024
    def __init__(self, socket : socket.socket, recv_buffersize : int = None):
        self.socket = socket
        
        # Change defaults
        if recv_buffersize: self.recv_buffersize = recv_buffersize
        
    def _send(self,payload : bytes):
        self.socket.sendall(payload)

    def _recv(self):
        return self.socket.recv(self.recv_buffersize)

    def _close(self):
        self.socket.close()


