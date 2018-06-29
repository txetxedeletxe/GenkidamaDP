package connect;

import java.net.Socket;

import packet.PacketHandler;

public abstract class ConnectionFactory {

	private ConnectionSpec connectionSpec;
	
	public ConnectionFactory(ConnectionSpec connectionSpec) {
		
		this.connectionSpec = connectionSpec;
		
	}
	
	public GenkidamaConnection connect(Socket socket) {
		
		return new GenkidamaConnection(socket, connectionSpec.getPacketHandler());
		
	}
}
