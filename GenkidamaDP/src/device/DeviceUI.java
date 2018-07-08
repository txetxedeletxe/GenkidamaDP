package device;

import java.net.UnknownHostException;

public interface DeviceUI {

	
	public void startServer(int port);
	public void stopServer();
	public void connectToHostServer(String hostName, int port) throws UnknownHostException;
}
