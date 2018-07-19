package connector;

import java.net.Socket;

import connection.GenkidamaConnection;


public abstract class GenkidamaConnector {

	private ConnectionBuilder connectionFactory;
	
	public GenkidamaConnector(ConnectionBuilder connectionFactory) {
		
		this.connectionFactory = connectionFactory;
		
	}
	
	public GenkidamaConnection connect(Socket socket) {
		
		connectionFactory.setProperty("socket", socket);
		GenkidamaConnection  connection = connectionFactory.construct();
		connectionFactory.reset();
		
		return connection;
		
		
	}
}
