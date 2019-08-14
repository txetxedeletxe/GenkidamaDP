package commandassembly;

public interface RawCommand {

	public byte[] getHeader(int headerSize);
	public byte[] getBytes();
	
	public void addHeader(byte[] headerBytes);
	
	public void removeHeader(int headerSize);
	
}
