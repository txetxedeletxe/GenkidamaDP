package connection;

import interfaces.ControllerInterface;
import interfaces.Handler;

public abstract class ConnectionWrapper implements Handler<GenkidamaPacket>,ControllerInterface{

	protected GenkidamaConnection connection;
	
	public ConnectionWrapper() {}
	
	public void setGenkidamaConnection(GenkidamaConnection connection) {
		
		this.connection = connection;
		
	}
	
	@Override
	public long getId() {
		
		return connection.hashCode();
	}
	
}
