import .core as core
import .coms as coms
import .interface as interface
import .asynchronous as asynchronous

class Client(object):
    def __init__(self, 
                 address,
                 **kwargs):
        # TODO add host_thread
        connection = coms.connect(address, **kwargs)
        device_interface = interface.RemoteDeviceInterface(connection)

        self.host = core.Host(device_interface)
        packet_handler = interface.PacketHandler(self.host)

        #self.host_thread = asynchronous.
        self.device_thread = asynchronous.ReceptionThread(connection, packet_handler)
        
    # Client options
    def start(self):
        self.device_thread.start()

    def stop(self):
        self.device_thread.stop()

    # API
    def exec(self,
             source_str):
        the_ppid = self.host.exec(source_str)
        return ExecutionRequest(self.host, the_ppid)


class ExecutionRequest(object):
    def __init__(self, host, ppid):
        self.host = host
        self.ppid = ppid

    # API
    """
    # TODO redefine this method
    def synchronize(self):
        return self.host.synchronize(self.ppid)
    """

    def get_input_stream(self):
        return self.host.get_input_stream(self.ppid)

    def get_output_stream(self):
        return self.host.get_output_stream(self.ppid)
