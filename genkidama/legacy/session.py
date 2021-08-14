import genkidamapy.core as core
import genkidamapy.interface as interface
import genkidamapy.asynchronous as asynchronous
"""
class DeviceInterface(object):
    def __init__(self, connection):
        self.connection = connection
        self.running_processes = {}

    def _handle_process_signal(self, signal_id, stack_frame):
        pass
"""  

class Session(object):
    def __init__(self, connection):
        host_interface = interface.RemoteHostInterface(connection)

        self.device = core.Device(host_interface)
        self.host_thread = asynchronous.HostThread(connection, self.device)

        #self.device_interface = DeviceInterface(connection)

    def enter(self):
        self.device.enter()
                

class SessionManager(object):
    def __init__(self, connector):
        self.connector = connector
        self.session_pids = []

    def new_session(self, connection):
        pid = os.fork()
        if pid != 0:
            self.session_pids.append(pid)
            connection.close()
            return

        self.connector.close()
        session = Session(connection)
        session.enter()
        sys.exit(0)
        
            
            


