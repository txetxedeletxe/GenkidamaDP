import java.net.Socket;

public class Socket2Endpoint extends Delegator<Endpoint> implements Handler<Socket> {
	
	public Socket2Endpoint(Handler<Endpoint> endpointHandler) {
		super(endpointHandler);
	}
	
	@Override
	public void handle(Socket t) throws Exception {
		
		SocketEndpoint se = new SocketEndpoint(t);
		getHandler().handle(se);
		
	}

}
