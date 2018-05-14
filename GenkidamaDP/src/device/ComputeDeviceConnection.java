package device;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import net.GenkidamaPacket;

public class ComputeDeviceConnection {

	private Socket socket;
	private OutputStream os;
	public void sendPacket(GenkidamaPacket packet) {
		
		try {
			os.write(packet.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
