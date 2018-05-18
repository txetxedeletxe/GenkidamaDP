package device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import net.GenkidamaPacket;

public class ComputeDeviceConnection {

	private Socket socket;
	private OutputStream os;
	private InputStream is;
	private TransmissionRegister transmissionRegister;
	private Thread connectionDaemon;
	
	private final Runnable connectionRunnable = new Runnable() {
		
		@Override
		public void run() {
			
			while (true) {
				packetizer();
			}
			
		}
		
		private void packetizer() {
			
		}
	};
	
	public ComputeDeviceConnection(String hostname, int port) throws UnknownHostException {
		
		try {
			socket = new Socket(hostname, port);
			os = socket.getOutputStream();
			is = socket.getInputStream();
			genkidamaHandshake();
			connectionDaemon = new Thread(connectionRunnable);
			connectionDaemon.setDaemon(true);
			connectionDaemon.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public ComputeDeviceConnection(String hostname) throws UnknownHostException {
		
		new ComputeDeviceConnection(hostname,7788);
	}
	
	public void sendPacket(GenkidamaPacket packet) {
		
		try {
			os.write(packet.toByteArray());
			transmissionRegister.registerOutPacket(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void genkidamaHandshake() {
		
	}
}
