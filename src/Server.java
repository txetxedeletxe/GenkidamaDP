import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Objects of this class represent server daemons that listen for incoming connections and 
 * accept them to then dispatch objects that handle these connections.
 */
public class Server extends RequestDaemon<Socket>{
	
	public static final int DEFAULT_SERVER_PORT = 6765;
	
	private int port;

	
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
		super(socketHandler);
		
		this.port = port;
	
	}
	
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
