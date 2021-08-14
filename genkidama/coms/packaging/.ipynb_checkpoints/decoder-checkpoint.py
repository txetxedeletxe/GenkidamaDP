from .base import BytePacketAnalyzer

class PacketDecoder(BytePacketAnalyzer):
    def decode_packet(self,byte_array : bytes) -> "GenkidamaPacket":
        header_delim = self.PACKET_LENGTH_BYTES+self.PPID_BYTES

        ppid = byte_array[self.PACKET_LENGTH_BYTES:header_delim]
        ppid = int.from_bytes(ppid, byteorder="big", signed=False)

        code_str = byte_array[header_delim:].decode()

        return ppid, code_str