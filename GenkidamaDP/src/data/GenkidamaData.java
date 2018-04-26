package data;

import java.nio.ByteBuffer;

import com.sun.tools.internal.xjc.model.SymbolSpace;

import exception.NotValidGenkidamaDataTypeException;

public class GenkidamaData implements GenkidamaContent {

	
	private GenkidamaDatum[] data;

	public GenkidamaData(GenkidamaDatum[] data) {
		this.data = data;
	}
	
	
	public GenkidamaDatum getDatum(int i) {
		return data[i];
	}
	
	public int getLength() {
		return data.length;
	}
	
	public int getByteLength() {
		return 4 + datumArrayLengthBytes();
	}
	
	@Override
	public byte[] toByte() {
		
		ByteBuffer bb = ByteBuffer.allocate(4 + datumArrayLengthBytes());
		
		bb.put(ByteConversions.intToByte(data.length));
		int j = 4;
		for (int i = 0 ; i < data.length ; i++) {
			bb.put(data[i].toByte());
		}
		
		return bb.array();
	}

	private int datumArrayLengthBytes() {
		
		int partial = 0;
		
		for (int i = 0 ; i < data.length ; i++) {
			partial += data[i].getByteSize();
		}
		
		return partial;
	}
	
	public static void main(String[] args) {
		try {
			GenkidamaData gd = new GenkidamaData(new
					GenkidamaDatum[] {new GenkidamaDatum(5),
							new GenkidamaDatum(new Character[] {'k','a','k','a'}),
							new GenkidamaDatum(new Double[] {1.0,0.0,1.5,7.2})});
			
			byte[] buf = gd.toByte();
			
			for (byte b: buf) {
				System.out.println(b);
			}
		} catch (NotValidGenkidamaDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
