package connector;

import java.net.Socket;

import connection.GenkidamaConnection;
import connection.GenkidamaPacket;
import interfaces.Factory;
import interfaces.Handler;

public class ConnectionFactory implements Factory<GenkidamaConnection> {

	private Socket socket;
	private Factory<Handler<GenkidamaPacket>> packetHandlerFactory;
	
	
	public ConnectionFactory(Factory<Handler<GenkidamaPacket>> packetHandlerFactory) {
		
		this.packetHandlerFactory = packetHandlerFactory;
		
	}
	
	@Override
	public GenkidamaConnection construct() {
		Handler<GenkidamaPacket> ph = packetHandlerFactory.construct();
		return new GenkidamaConnection(socket,ph);
	}
	
	public void setPacketHandlerFactory(Factory<Handler<GenkidamaPacket>> packetHandlerFactory) {
		
		this.packetHandlerFactory = packetHandlerFactory;
		
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Factory<Handler<GenkidamaPacket>> getPacketHandlerFactory(){
		return packetHandlerFactory;
	}
}
