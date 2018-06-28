package connect;

import java.net.Socket;

public abstract class ConnectionFactory {

	private SocketHandler socketHandler;
	
	public ConnectionFactory(SocketHandler socketHandler) {
		
		this.socketHandler = socketHandler;
		
	}
	
	public GenkidamaConnection connect(Socket socket) {
		
		return socketHandler.connect(socket);
		
	}
}
