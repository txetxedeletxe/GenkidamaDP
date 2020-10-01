import socket
import time
import threading
from select import select

import genkidamapy.packet as packet
# Constants
PPID_BYTES = 2
PACKET_LENGTH_BYTES = 4

# Functions
def connect(address, connection_type="TCP", **kwargs):
    connection = None
    if connection_type == "TCP":
        sc =  socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sc.connect(address)
        connection = Connection(sc)
    elif connection_type == "dummy":
        connection = DummyConnection()
    else:
        raise ValueError("No such \"connection_type\" as " + connection_type)
    return connection


# Classes
## Connection types
class Connection(object):
    DEFAULT_RECV_BUFFERSIZE = 1024
    def __init__(self, socket):
        self.socket = socket
        self.slicer = packet.ByteStreamSlicer()        

    def recv(self):
        while not self.slicer.has_slice():
            r, _, _ = select([self.socket],[],[])
            if not r:
                return None

            received = self.socket.recv(Connection.DEFAULT_RECV_BUFFERSIZE)
            if received is None:
                return None
            self.slicer.append_bytes(received)


        next_slice = self.slicer.next_slice()
        return packet.decode_packet(next_slice)
    
    def send(self, packet):
        packet_encoded = packet.encode_packet(packet)
        self.socket.sendall(packet_encoded)

    def close(self):
        self.socket.close()

class DummyConnection(object):
    def __init__(self):
        self.result_queue = []
        self.queue_lock = threading.Lock()

    def recv(self):
        while True:
            time.sleep(3)
            if len(result_queue) != 0:
                res = self.result_queue[0]
                self.queue_lock.acquire()
                self.result_queue = self.result_queue[1:]
                self.queue_lock.release()
                return res
            

    def send(self,packet):
        ppid, exec_str = packet
        exec_res = exec(exec_str)
        self.queue_lock.acquire()
        self.result_queue.append((ppid, exec_res))
        self.queue_lock.release()

# Connector
class Connector(object):
    def __init__(self, port):
        self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.socket.bind(("", port))
        
    def listen(self):
        self.socket.listen()

    def accept(self):
        session_socket, _ = self.socket.accept()
        return Connection(session_socket)

    def close(self):
        self.socket.close()