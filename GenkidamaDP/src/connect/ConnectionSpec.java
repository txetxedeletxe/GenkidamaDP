package connect;

import packet.PacketHandler;

public class ConnectionSpec {

	private PacketHandlerFactory packetHandlerFactory;
	
	public ConnectionSpec(PacketHandlerFactory packetHandler) {
		
		this.packetHandlerFactory = packetHandler;
	}

	public PacketHandler getPacketHandler() {
		return this.packetHandlerFactory.construct();
	}
	
}
