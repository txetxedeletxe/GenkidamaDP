package pipes;
import java.io.IOException;
import java.net.Socket;

import endpoint.StreamEndpoint;
import endpoint.SocketStreamEndpoint;
import types.Handler;
import types.Pipe;

/**
 * Objects of this class transform a socket into Endpoint and pipe it down
 * 
 */
public class Socket2Endpoint extends Pipe<Socket,StreamEndpoint> {
	
	public Socket2Endpoint() {
		super();
	}
	
	public Socket2Endpoint(Handler<StreamEndpoint> endpointHandler) {
		super(endpointHandler);
	}
	
	@Override
	public StreamEndpoint convert(Socket a) throws IOException {
		return new SocketStreamEndpoint(a);
	}

}
