import threading
import os
import sys
import io

# HOST
class HostState(object):
    pass

class Host(object):
    def __init__(self, device_interface):
        self.device_interface = device_interface

        # Abstract this to HostState
        self.next_ppid = 0
        self.pipes = {}


    # Device Interface methods
    def exec(self, source_str):
        this_ppid = self.next_ppid
        self.next_ppid += 1

        out_pipe = os.pipe()
        in_pipe = os.pipe()

        self.pipes[this_ppid] = dict(out_pipe=out_pipe, in_pipe=in_pipe)
        self.device_interface.exec(this_ppid, source_str)

        return this_ppid

    # Host Interface methods
    def return_result(self, ppid, result):
        self.results[ppid] = result
        self.return_events[ppid].set()

    # Local host methods
    def synchronize(self, ppid):
        self.return_events[ppid].wait()
        return self.return_events[ppid]

# DEVICE
class Device(object):
    def __init__(self, host_interface):
        self.host_interface = host_interface

        # Abstract these

    def exec(self, ppid, script_str):
        
        stdout = sys.stdout
        sys.stdout = io.StringIO()
        pid = os.fork()

        if pid == 0:
            exec(script_str)