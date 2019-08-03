import java.io.IOException;
import java.net.Socket;

import types.Handler;
import types.Pipe;

/**
 * Objects of this class transform a socket into Endpoint and pipe it down
 * 
 */
public class Socket2Endpoint extends Pipe<Socket,Endpoint> {
	
	public Socket2Endpoint() {
		super();
	}
	
	public Socket2Endpoint(Handler<Endpoint> endpointHandler) {
		super(endpointHandler);
	}
	
	@Override
	public Endpoint convert(Socket a) throws IOException {
		return new SocketEndpoint(a);
	}

}
