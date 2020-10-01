import threading

class ReceptionThread(threading.Thread):
    def __init__(self, connection, packet_handler):
        self.connection = connection
        self.packet_handler = packet_handler


    # Thread control
    
    
    # Thread method
    def run(self):
        recv_pck = self.connection.recv() # Blocking call
        self.packet_handler.handle(recv_pck)

    