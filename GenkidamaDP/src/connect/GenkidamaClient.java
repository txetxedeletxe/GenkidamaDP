package connect;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GenkidamaClient extends ConnectionFactory{

	public GenkidamaClient(SocketHandler socketHandler) {
		super(socketHandler);
		
	}
	
	public GenkidamaConnection connect(String hostname, int port) throws UnknownHostException {
		Socket socket = null;
		try {
			socket = new Socket(hostname, port);
		} catch (UnknownHostException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect(socket);
	}

	
}
