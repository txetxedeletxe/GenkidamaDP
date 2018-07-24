package connector;

import java.net.Socket;

import connection.GenkidamaConnection;


public abstract class GenkidamaConnector {

	private ConnectionFactory connectionFactory;
	
	public GenkidamaConnector(ConnectionFactory connectionFactory) {
		
		this.connectionFactory = connectionFactory;
		
	}
	
	public GenkidamaConnection connect(Socket socket) {
		
		connectionFactory.setSocket(socket);
		GenkidamaConnection  connection = connectionFactory.construct();		
		return connection;
		
		
	}
}
