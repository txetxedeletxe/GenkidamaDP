package net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;


import utils.ByteEncoder;
import utils.Handler;

public class NetInterface {

	private Socket socket;
	
	private BufferedOutputStream os;
	
	private Thread daemon;
		
	private ByteEncoder be;
	
	
	public NetInterface(Socket socket, Handler<byte[]> handler) throws IOException {
		
		this.socket = socket;
		
		this.os = new BufferedOutputStream(socket.getOutputStream());
		
		daemon = new Thread(new Runnable() {
			
			private Handler<byte[]> h = handler;
			private BufferedInputStream is = new BufferedInputStream(socket.getInputStream());
			@Override
			public void run() {
				
				while (true) {
					
					
					
				}
				
			}
			
		});
		
		this.be = new ByteEncoder();
		
		
	}
	
	public void sendPacket(byte[] byteArray) throws IOException {
		
		byte[] sendend = new byte[byteArray.length+6];
		
		sendend[0] = Byte.MIN_VALUE; //Start mark
		
		byte[] size = be.intToByte(byteArray.length); //Size
		
		sendend[1] = size[0];
		sendend[2] = size[1];
		sendend[3] = size[2];
		sendend[4] = size[3];
		
		
		for (int i = 0 ; i < byteArray.length ; i++) {
			sendend[i+5] = byteArray[i];
		}
		
		sendend[sendend.length-1] = Byte.MAX_VALUE; //End mark
		
		os.write(sendend);
	}
	
	
	private class NetDaemon implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	
}
