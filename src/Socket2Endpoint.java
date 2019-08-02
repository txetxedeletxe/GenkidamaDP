import java.net.Socket;

public class Socket2Endpoint implements Handler<Socket> {

	private Handler<Endpoint> endpointHandler;
	
	public Socket2Endpoint(Handler<Endpoint> endpointHandler) {
		this.endpointHandler = endpointHandler;
	}
	
	@Override
	public void handle(Socket t) throws Exception {
		
		SocketEndpoint se = new SocketEndpoint(t);
		endpointHandler.handle(se);
		
	}

}
