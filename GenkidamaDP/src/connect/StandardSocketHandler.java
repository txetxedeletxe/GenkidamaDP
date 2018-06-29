package connect;

import java.net.Socket;

import packet.PacketHandler;

public class StandardSocketHandler implements SocketHandler{

	private PacketHandler packetHandler;
	
	public StandardSocketHandler(PacketHandler packetHandler) {
		
		this.packetHandler = packetHandler;
	}

	@Override
	public GenkidamaConnection connect(Socket socket) {
		
		return new GenkidamaConnection(socket, packetHandler);
	}
	
}
