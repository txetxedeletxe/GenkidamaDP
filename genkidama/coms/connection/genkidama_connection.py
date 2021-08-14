from .interface import GenkiadamaConnection, ByteConnection
from ..packaging import ByteStreamSlicer, PacketEncoderDecoder

class GenkidamaThroughByteConnection(GenkiadamaConnection):
    PACKET_ENCODER_DECODER = PacketEncoderDecoder()
    SLICER_CONSTRUCTOR = ByteStreamSlicer
    
    packet_encoder = PACKET_ENCODER_DECODER.encode_packet
    packet_decoder = PACKET_ENCODER_DECODER.decode_packet
    def __init__(
        self,
        byte_connection : ByteConnection,
        slicer=None,
        packet_encoder=None,
        packet_decoder=None):
        self.byte_connection = byte_connection

        self.slicer = slicer or self.SLICER_CONSTRUCTOR()

        # Change defaults
        if packet_encoder: self.packet_encoder = packet_encoder
        if packet_decoder: self.packet_decoder = packet_decoder

    def _send(self,payload : "GenkidamaPacket"):
        byte_payload = self.packet_encoder(payload)
        self.byte_connection.send(byte_payload)

    def _recv(self):
        while not self.slicer.has_slice():
            received_bytes = self.byte_connection.recv()
            if received_bytes is None: raise EOFError() # TODO rethink this
            self.slicer.append_bytes(received_bytes)
            
        slice_ = self.slicer.next_slice()
        return self.packet_decoder(slice_)

    def _close(self):
        self.byte_connection.close()


        