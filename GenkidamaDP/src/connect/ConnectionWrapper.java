package connect;

import packet.PacketHandler;
import utils.ControllerInterface;

public abstract class ConnectionWrapper implements PacketHandler,ControllerInterface{

	protected GenkidamaConnection connection;
	
	public ConnectionWrapper() {
		
	}
	
	public void setGenkidamaConnection(GenkidamaConnection connection) {
		
		this.connection = connection;
		
	}
	
}
