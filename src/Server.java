import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Objects of this class represent server daemons that listen for incoming connections and 
 * accept them to then dispatch objects that handle these connections.
 */
public class Server implements Runnable{
	
	public static final int DEFAULT_SERVER_PORT = 6765;
	
	private int port;
	private Handler<Socket> socketHandler;
	
	private boolean running = false;
	private int timeoutLength = 100;
	
	public Server() {
		this(null,DEFAULT_SERVER_PORT);
	}
	
	public Server(Handler<Socket> socketHandler) {
		this(socketHandler,DEFAULT_SERVER_PORT);
	}
	
	public Server(int port) {
		this(null,port);
	}
	
	public Server(Handler<Socket> socketHandler, int port) {
		
		this.socketHandler = socketHandler;
		this.port = port;
	
	}

	public void setHandler(Handler<Socket> socketHandler) {
		this.socketHandler = socketHandler;
	}
	
	@Override
	public void run() {
			
		running = true;
		
		try {
			
			ServerSocket sSocket = new ServerSocket(port);
			sSocket.setSoTimeout(timeoutLength);
			
			while (running) {
				try {
					Socket someConnection = sSocket.accept();
					
					if (socketHandler != null) {
						try {
							socketHandler.handle(someConnection);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}catch (SocketTimeoutException e) {}
			}
			
			sSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			running = false;
		}
		
	}
	
	 
	
	
}
