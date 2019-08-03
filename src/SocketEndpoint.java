import java.io.IOException;
import java.net.Socket;

import types.Handler;

public class SocketEndpoint extends Endpoint{

	private Socket s;
	
	public SocketEndpoint(Socket s) throws IOException {
		this(s,null);
	}
	
	public SocketEndpoint(Socket s, Handler<byte[]> byteArrayHandler) throws IOException {
		super(s.getInputStream(),s.getOutputStream(),byteArrayHandler);
		
		this.s = s;
	}
	
	public void close() throws IOException {

		super.close();
		s.close();
	}

}
