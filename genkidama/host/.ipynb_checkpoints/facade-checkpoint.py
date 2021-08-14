from genkidama.coms import connect
from genkidama.device import RemoteDevice

def connect_to_device(connection_hint):
    connection = connect(connection_hint)
    device = RemoteDevice(connection)
    return device
