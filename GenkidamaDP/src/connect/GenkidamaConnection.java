package connect;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import packet.GenkidamaPacket;
import packet.PacketHandler;

public class GenkidamaConnection {

	private PacketHandler handler;
	private Socket socket;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private final Runnable connectionRunnable = new Runnable() {
		
		@Override
		public void run() {
			
			while (true) {
				
				try {
					
					
					GenkidamaPacket readPacket = (GenkidamaPacket) ois.readObject();
					handler.handle(readPacket);
						
					
				}catch (SocketException | EOFException e) {
					return;
				}
				catch (IOException | ClassNotFoundException  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	};
	
	private Thread connectionDaemon;
	
	public GenkidamaConnection(Socket socket, PacketHandler packetHandler) {
		this.handler = packetHandler;
		this.socket = socket;
		
		
		
		try {
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			this.oos = new ObjectOutputStream(os);
			oos.flush();
			this.ois = new ObjectInputStream(is);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		connectionDaemon = new Thread(connectionRunnable);
		connectionDaemon.start();
		
	}
	
	
	public void close() {
		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendPacket(GenkidamaPacket packet) {
		
		try {
			oos.writeObject(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PacketHandler getPacketHandler() {
		return handler;
	}
	
	@Override
	public int hashCode() {
		
		return ((Integer)((socket.getInetAddress().getHostAddress().hashCode())
				+ ((Integer)socket.getPort()).hashCode())).hashCode();
	}
}
