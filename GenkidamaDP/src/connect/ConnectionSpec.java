package connect;

import packet.PacketHandler;

public class ConnectionSpec {

	private PacketHandler packetHandler;
	
	public ConnectionSpec(PacketHandler packetHandler) {
		
		this.packetHandler = packetHandler;
	}

	public PacketHandler getPacketHandler() {
		return this.packetHandler;
	}
	
}
