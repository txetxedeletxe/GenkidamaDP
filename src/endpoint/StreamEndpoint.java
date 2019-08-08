package endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import types.Handler;
import types.RequestDaemon;

/**
 * Objects of this class represent an end-point in a established session between instances that implement the Genkidama API.
 * This class handles input and output streams and leverages the container level in the Genkidama protocol stack.
 * 
 */
public class StreamEndpoint extends RequestDaemon<byte[]>{
	
	private InputStream is;
	private OutputStream os;
	
	// Polling preferences
	private int pollingSize = 1024;
	private int bufferSize = 1000000;
	
	// Running flag
	private boolean listenerRunning = false;
	
	/**
	 * Constructor of an end-point without handler. Constructs an end-point given the input and output streams to the other end-point.
	 * Since there is no handler defined the end-point won't be able to pass incoming messages unless a handler is defined later.
	 * 
	 * @param is The input stream to receive incoming data
	 * @param os The output stream to write outgoing data to
	 */
	public StreamEndpoint(InputStream is, OutputStream os) {
		super();
		
		this.is = is;
		this.os = os;
		
	}
	
	/**
	 * Constructor of an end-point without handler. Constructs an end-point given the input and output streams to the other end-point.
	 * Since there is no handler defined the end-point won't be able to pass incoming messages unless a handler is defined later.
	 * 
	 * @param is The input stream to receive incoming data
	 * @param os The output stream to write outgoing data to
	 * @param byteArrayHandler Handler of byte arrays to send the incoming packets to 
	 */
	public StreamEndpoint(InputStream is, OutputStream os, Handler<byte[]> byteArrayHandler) {
		super(byteArrayHandler);
		
		this.is = is;
		this.os = os;
		
	}
	
	/**
	 * Write a byte array payload to the output stream wrapped in a container header
	 * 
	 * @param payload array of bytes to write in the output stream
	 * @throws IOException thrown if the output stream is closed
	 */
	public void sendPayload(byte[] payload) throws IOException {
		
		ByteBuffer bb = ByteBuffer.allocate(payload.length + 4);
		
		bb.putInt(payload.length);
		bb.put(payload);
		bb.flip();
		
		byte[] totalLoad = new byte[payload.length + 4];
		bb.get(totalLoad);
		
		os.write(totalLoad);
		
	}
	
	/**
	 * Entry point for thread that listens to incoming packages and passes it down to the handler.
	 */
	@Override
	public void run() {
		super.run();
		byte[] barray = new byte[pollingSize];
		ByteBuffer bbuffer = ByteBuffer.allocate(bufferSize);
		
		int nextPacketSize = -1;
		
		listenerRunning = true;
		try {
			
			while (super.isRunning()) {
				
				//Is there anything to read
				if (is.available() == 0) {
					
						Thread.sleep(getPollingGap());
						continue;
				}
				
				//Read from stream and put into buffer
				is.read(barray);
				bbuffer.put(barray);
				
				//Prepare buffer to read
				bbuffer.flip();
				
				//Can we determine the length of the next packet
				if (nextPacketSize == -1 && bbuffer.remaining() >= 4) {
					
					nextPacketSize = bbuffer.getInt();
					
					// If we need more buffer for the next packet
					if (nextPacketSize + pollingSize > bufferSize) {
						bufferSize = nextPacketSize + pollingSize;
						ByteBuffer newBuffer = ByteBuffer.allocate(bufferSize);
						newBuffer.put(bbuffer);
						bbuffer = newBuffer;
						bbuffer.flip();
					}
				}
				
				
				
				//While there is a packet to parse
				while (nextPacketSize != -1 && bbuffer.remaining() >= nextPacketSize) {
					
					byte[] pack = new byte[nextPacketSize];
					
					bbuffer.get(pack);
					
					try {
						delegate(pack);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					nextPacketSize = -1;
					
					//Can we determine the length of the next packet
					if (bbuffer.remaining() >= 4) {
						nextPacketSize = bbuffer.getInt();
						
						// If we need more buffer for the next packet
						if (nextPacketSize + pollingSize > bufferSize) {
							bufferSize = nextPacketSize + pollingSize;
							ByteBuffer newBuffer = ByteBuffer.allocate(bufferSize);
							newBuffer.put(bbuffer);
							bbuffer = newBuffer;
							bbuffer.flip();
						}
					}
					
				}
				
				bbuffer.compact();
				
			}
		
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			super.stop();
			
		}
		
		listenerRunning = false;
		
	}
	
	/**
	 * returns true if the listener is currently running, false if not
	 */
	@Override
	public boolean isRunning() {
		return listenerRunning;
	}
	
	/**
	 * Stops the incoming packet listener if it is running and blocks till completely stopped
	 */
	@Override
	public void stop() {
		super.stop();
		// Block until stops running
		while (listenerRunning);
		
	}
	
	/**
	 * Stops the end-point if it is running and closes it out. Closing the end-point releases the attached resources
	 * and invalidates the object.
	 * 
	 * @throws IOException is thrown if there is an IOException when closing the InputStream or the OutputStream
	 */
	public void close() throws IOException {
		
		stop();
		
		is.close();
		os.close();
	}
	
	
	
}
