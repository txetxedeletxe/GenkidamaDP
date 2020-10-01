# Functions
def decode_packet(byte_array):
    ppid = int.from_bytes(byte_array[PACKET_LENGTH_BYTES:PACKET_LENGTH_BYTES+PPID_BYTES], byteorder="big", signed=False)
    code_str = byte_array[PACKET_LENGTH_BYTES+PPID_BYTES:].decode()

    return ppid, code_str

def encode_packet(packet):
    ppid, code_str = packet

    ppid_bytes = int.to_bytes(ppid, PPID_BYTES, byteorder="big", signed=False)
    code_str_bytes = code_str.encode()

    byte_length = len(ppid_bytes) + len(code_str_bytes)

    byte_array = bytearray()
    byte_array += int.to_bytes(byte_length, PPID_BYTES, byteorder="big", signed=False)
    byte_array += ppid_bytes
    byte_array += code_str_bytes

    return byte_array

# Classes
class ByteStreamSlicer(object):
    
    def __init__(self):
        self.next_packet_length = -1
        self.byte_buffer = bytearray()
        self.slices = []

    def append_bytes(self, b):
        self.byte_buffer += b
        while True:
            if len(self.byte_buffer) < PACKET_LENGTH_BYTES:
                break
            
            if self.next_packet_length == -1:
                self.next_packet_length = int.from_bytes(self.byte_buffer[:4], byteorder="big", signed=False)

            if len(self.byte_buffer) >= self.next_packet_length:
                next_packet = self.byte_buffer[:self.next_packet_length]
                self.byte_buffer = self.byte_buffer[self.next_packet_length:]

                self.slices.append(next_packet)

                self.next_packet_length = -1
            else:
                break

    def has_slice(self):
        return len(self.slices) > 0

    def next_slice(self):
        if len(self.slices) == 0:
            return None

        next_slice = self.slices[0]
        self.slices = self.slices[1:]
        return next_slice