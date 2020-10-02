import threading

class ReceptionThread(threading.Thread):
    def __init__(self, 
                    connection, 
                    packet_handler, 
                    max_unresponsive_period=1,
                    clean_stop=False):
        self.connection = connection
        self.packet_handler = packet_handler
        self.max_unresponsive_period = max_unresponsive_period
        self.clean_stop = clean_stop

        self.stop_signaled = False

    # Thread control
    def stop(self):
        self.stop_signaled = True
    
    # Thread method
    def run(self):
        while not self.stop_signaled:
            recv_pck = self.connection.recv(timeout=self.max_unresponsive_period) # Blocking call
            
            if self.stop_signaled and not self.clean_stop:
                break

            if recv_pck:
                self.packet_handler.handle(recv_pck)

    