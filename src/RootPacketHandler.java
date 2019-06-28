import java.util.Arrays;
import java.util.Map;

public class RootPacketHandler implements Handler<byte[]>{
	
	Map<Byte,Handler<byte[]>> handlerMap;
	
	public RootPacketHandler(Map<Byte,Handler<byte[]>> handlerMap) {
		
		this.handlerMap = handlerMap;
	}

	@Override
	public void handle(byte[] t) {
	
		Handler<byte[]> thisHandler = handlerMap.get(t[0]);
		
		if (thisHandler == null)
			return;
		
		byte[] slice = Arrays.copyOfRange(t, 1, t.length);
		thisHandler.handle(slice);
		
	}
	
	
	
}
