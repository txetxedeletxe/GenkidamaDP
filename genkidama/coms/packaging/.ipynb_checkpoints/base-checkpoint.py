class BytePacketAnalyzer(object):
    PACKET_LENGTH_BYTES = 4
    PPID_BYTES = 2

    def __init__(self,
            pck_length_bytes : int = None,
            ppid_bytes : int = None):
        
        if pck_length_bytes: self.PACKET_LENGTH_BYTES = pck_length_bytes
        if ppid_bytes: self.PPID_BYTES = ppid_bytes

