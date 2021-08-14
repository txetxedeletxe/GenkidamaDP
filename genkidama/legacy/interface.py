class RemoteDeviceInterface(object):
    def __init__(self, connection):
        self.connection = connection

    def exec(self, ppid, script_str):
        # Pack the parameters somewhere else
        self.connection.send((ppid, script_str))

class RemoteHostInterface(object):
    def __init__(self, connection):
        self.connection = connection

    def return_result(self, ppid, result):
        # Pack the parameters somewhere else
        self.connection.send((ppid,result))

class PacketHandler(object):
    def __init__(self, interface):
        self.interface = interface

    def handle(self, packet):
        self.interface.return_result(*packet)