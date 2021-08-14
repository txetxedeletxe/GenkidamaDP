from .base import BytePacketAnalyzer

class ByteStreamSlicer(BytePacketAnalyzer): # TODO make interface
    def __init__(self,pck_length_bytes : int = None):
        super().__init__(pck_length_bytes)

        self.next_packet_length = -1
        self.byte_buffer = bytearray()
        self.slices = [] # Todo use a queue

    # TODO make interface
    def append_bytes(self, b):
        self.byte_buffer += b
        while True:
            if len(self.byte_buffer) < self.PACKET_LENGTH_BYTES:
                break
            
            if self.next_packet_length == -1:
                self.next_packet_length = int.from_bytes(
                                            self.byte_buffer[:self.PACKET_LENGTH_BYTES],
                                            byteorder="big",
                                            signed=False)
                self.next_packet_length += self.PACKET_LENGTH_BYTES

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