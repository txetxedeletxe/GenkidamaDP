package connection;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import types.Handler;
import types.RequestDaemon;

/**
 * Objects of this class represent server daemons that listen for incoming connections and 
 * accept them to then dispatch objects that handle these connections.
 */
public class Server extends RequestDaemon<Socket>{
	
	public static final int DEFAULT_SERVER_PORT = 6765;
	
	private int port;

	/**
	 * Empty constructor for the Server, it will set the port to the default port 
	 * and there will be no handler set, so any incoming connection is dropped (unless one is specified afterwards)
	 */
	public Server() {
		this(DEFAULT_SERVER_PORT);
	}
	
	/**
	 * Constructor with Socket handler and no port. The port will be set to the default port and the handler will be set.
	 * 
	 * @param socketHandler object to handle the Sockets that are spawned in this class
	 */
	public Server(Handler<Socket> socketHandler) {
		this(socketHandler,DEFAULT_SERVER_PORT);
	}
	
	/**
	 * Constructor with port and no handler. The port will be set appropriately but there will be no
	 * handler unless specified afterwards. In the contrary case incoming connections will be dropped.
	 * 
	 * @param port the port number to bind the server
	 */
	public Server(int port) {
		super();
		
		this.port = port;
		
	}
	
	/**
	 * Constructor with both handler and port. The server will be bound to the given port and the
	 * created sockets delegated to the given handler.
	 * 
	 * @param socketHandler object to handle the Sockets that are spawned in this class
	 * @param port the port number to bind the server
	 */
	public Server(Handler<Socket> socketHandler, int port) {
		super(socketHandler);
		
		this.port = port;
	
	}
	
	/**
	 * Run the server
	 */
	@Override
	public void run() {
		super.run();
		
		try {
			
			ServerSocket sSocket = new ServerSocket(port);
			sSocket.setSoTimeout(getPollingGap());
			
			while (isRunning()) {
				try {
					Socket someConnection = sSocket.accept();
					
					try {
						getHandler().handle(someConnection);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}catch (SocketTimeoutException e) {}
			}
			
			sSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			stop();
		}
		
	}
	
	 
	
	
}
