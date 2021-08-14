from .base import BytePacketAnalyzer

class PacketEncoder(BytePacketAnalyzer):
    def encode_packet(self,packet : "GenkidamaPacket") -> bytes:
        ppid, code_str = packet

        ppid_bytes = int.to_bytes(ppid, self.PPID_BYTES, byteorder="big", signed=False)
        code_str_bytes = code_str.encode()

        byte_length = len(ppid_bytes) + len(code_str_bytes)

        byte_array = bytearray()
        byte_array += int.to_bytes(
                        byte_length, 
                        self.PACKET_LENGTH_BYTES,
                        byteorder="big",
                        signed=False)
        byte_array += ppid_bytes
        byte_array += code_str_bytes

        return byte_array