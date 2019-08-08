package endpoint;
import java.io.IOException;
import java.net.Socket;

import types.Handler;

public class SocketStreamEndpoint extends StreamEndpoint{

	private Socket s;
	
	public SocketStreamEndpoint(Socket s) throws IOException {
		super(s.getInputStream(),s.getOutputStream());
		
		this.s = s;
	}
	
	public SocketStreamEndpoint(Socket s, Handler<byte[]> byteArrayHandler) throws IOException {
		super(s.getInputStream(),s.getOutputStream(),byteArrayHandler);
		
		this.s = s;
	}
	
	public void close() throws IOException {

		super.close();
		s.close();
	}

}
