import genkidamapy.core as core
import genkidamapy.coms as coms
import genkidamapy.interface as interface
import genkidamapy.asynchronous as asynchronous

class Client(object):
    def __init__(self, 
                 address,
                 **kwargs):
        connection = coms.connect(address, **kwargs)
        device_interface = interface.RemoteDeviceInterface(connection)

        self.host = core.Host(device_interface)
        packet_handler = interface.PacketHandler(self.host)

        self.device_thread = asynchronous.ReceptionThread(connection, packet_handler)

    # Start listening
    def start(self):
        self.device_thread.start()

    # API
    def exec(self,
             source_str):
        the_ppid = self.host.exec(source_str)
        return Request(self.host, the_ppid)

    def close():

class Request(object):
    def __init__(self, host, ppid):
        self.host = host
        self.ppid = ppid

    # API
    def synchronize(self):
        return self.host.synchronize(self.ppid)

