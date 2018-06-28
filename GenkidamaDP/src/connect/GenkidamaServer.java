package connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class GenkidamaServer extends ConnectionFactory{

	private ServerSocket serverSocket;
	private ConnectionHandler connectionHandler;
	
	private Thread serverDaemon;
	
	private Boolean running = false;
	
	private final int socketRefresh = 1000;
	private final Runnable serverRunnable = new Runnable(){

		@Override
		public void run() {
			
			while (running) {
				
				try {
					Socket acceptedSocket = serverSocket.accept();
					GenkidamaConnection genkidamaConnection = connect(acceptedSocket);
					connectionHandler.handleConnection(genkidamaConnection);
				} catch (SocketTimeoutException e) {}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
		}
		
	};
	
	public GenkidamaServer(int port, ConnectionHandler connectionHandler , SocketHandler socketHandler) {
		
		super(socketHandler);
		
		try {
			this.serverSocket = new ServerSocket(port);
			this.serverSocket.setSoTimeout(socketRefresh);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.connectionHandler = connectionHandler;
		
	
		this.serverDaemon = new Thread(serverRunnable);
		//serverDaemon.setDaemon(true);
		
	}
	
	public void open() {
		
		running = true;
		serverDaemon.start();
	}
	
	public void close() {
		running = false;
		try {
			serverDaemon.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
