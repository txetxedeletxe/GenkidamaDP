import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * Objects of this class represent an end-point in a established session between instances that implement the Genkidama API.
 * This class handles input and output streams and leverages the container level in the Genkidama protocol stack.
 * 
 */
public class GenkidamaEndpoint implements Runnable{
	
	private InputStream is;
	private OutputStream os;
	
	private Handler<byte[]> byteArrayHandler;
	
	private int pollingGap = 100;
	private int pollingSize = 1024;
	private int bufferSize = 1000000;
	
	private boolean listenerRunning = false;
	
	/**
	 * Constructor of an endpoint without handler. Constructs an endpoint given the input and output streams to the other endpoint.
	 * Since there is no handler defined the endpoint won't be able to pass incoming messages unless a handler is defined later.
	 * 
	 * @param is The input stream to receive incoming data
	 * @param os The output stream to write outgoing data to
	 */
	public GenkidamaEndpoint(InputStream is, OutputStream os) {
		
		//Handler to null
		this(is,os,null);
		
	}
	
	/**
	 * Constructor of an endpoint without handler. Constructs an endpoint given the input and output streams to the other endpoint.
	 * Since there is no handler defined the endpoint won't be able to pass incoming messages unless a handler is defined later.
	 * 
	 * @param is The input stream to receive incoming data
	 * @param os The output stream to write outgoing data to
	 * @param byteArrayHandler Handler of byte arrays to send the incoming packets to 
	 */
	public GenkidamaEndpoint(InputStream is, OutputStream os, Handler<byte[]> byteArrayHandler) {
		
		this.is = is;
		this.os = os;
		
		this.byteArrayHandler = byteArrayHandler;
		
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
		
		byte[] totalLoad = new byte[payload.length + 4];
		bb.get(totalLoad);
		
		os.write(totalLoad);
		
	}
	
	/**
	 * Entry point for thread that listens to incoming packages and passes it down to the handler.
	 */
	@Override
	public void run() {
		
		byte[] barray = new byte[pollingSize];
		ByteBuffer bbuffer = ByteBuffer.allocate(bufferSize);
		
		int nextPacketSize = -1;
		int bufferedCount = 0;
		
		listenerRunning = true;
		
		try {
			
			while (listenerRunning) {
				
				//Is there anything to read
				if (is.available() == 0) {
					
						Thread.sleep(pollingGap);
						continue;
				}
				
				int readLen = is.read(barray);
				bbuffer.put(barray);
				bufferedCount += readLen;
				
				//Can we determine the length of the next packet
				if (nextPacketSize == -1 && bufferedCount >= 4) {
					nextPacketSize = bbuffer.getInt();
					bufferedCount -= 4;
					
					// If we need more buffer for the next packet
					if (nextPacketSize + pollingSize > bufferSize) {
						bufferSize = nextPacketSize + pollingSize;
						ByteBuffer newBuffer = ByteBuffer.allocate(bufferSize);
						newBuffer.put(bbuffer);
						bbuffer = newBuffer;
					}
				}
				
				//While there is a packet to parse
				while (nextPacketSize != -1 && bufferedCount >= nextPacketSize) {
					
					byte[] pack = new byte[nextPacketSize];
					bbuffer.get(pack);
					
					byteArrayHandler.handle(pack);
					
					bufferedCount -= nextPacketSize;
					nextPacketSize = -1;
					
					//Can we determine the length of the next packet
					if (bufferedCount >= 4) {
						nextPacketSize = bbuffer.getInt();
						bufferedCount -= 4;
						
						// If we need more buffer for the next packet
						if (nextPacketSize + pollingSize > bufferSize) {
							bufferSize = nextPacketSize + pollingSize;
							ByteBuffer newBuffer = ByteBuffer.allocate(bufferSize);
							newBuffer.put(bbuffer);
							bbuffer = newBuffer;
							
						}
					}
					
				}
				
			}
		
		} catch (InterruptedException | IOException e) {
			// Leave loop
		}
		
		
	}
	
	/**
	 * Stops the incoming packet listener if it is running
	 */
	public void stopListener() {
		listenerRunning = false;
	}
	
	
	
}
