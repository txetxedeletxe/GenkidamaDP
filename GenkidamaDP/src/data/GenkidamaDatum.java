package data;

import java.nio.ByteBuffer;

import exception.NotValidGenkidamaDataTypeException;

//Objects of this class represent datum that can be understood by all genkidama devices

public class GenkidamaDatum implements GenkidamaContent{

	private GenkidamaDataType dataType;
	private Object datum;
	
	public GenkidamaDatum(Object datum) throws NotValidGenkidamaDataTypeException{
		
		this.dataType = GenkidamaDataType.getGenkidamaDataType(datum);
		this.datum = datum;
	}
	
	public GenkidamaDataType getDataType() {
		return dataType;
	}

	public Object getDatum() {
		return datum;
	}

	
	public int getByteSize() {
	
		return 1 + (dataType.isArray() ? 4 : 0) + dataType.getByteSize(datum);
	}
	
	@Override
	public byte[] toByte() {
		byte[] dataBytes = dataType.dataToByte(this.datum);
		int arraySize = dataBytes.length + 1 + (dataType.isArray() ? 4 : 0);
		ByteBuffer bb = ByteBuffer.allocate(arraySize);
		bb.put(dataType.toByte());
		
		if (dataType.isArray()) {
			bb.put(ByteConversions.intToByte(((Object[])datum).length));
		}
		
			bb.put(dataBytes);
		return bb.array();
	}

	
	public static void main(String[] args) {
		
		try {
			GenkidamaDatum datum = new GenkidamaDatum(new Integer[]{5,16,255,256});
			byte[] buf = datum.toByte();
			
			for (byte b : buf) {
				System.out.println(b);
			}
			
		} catch (NotValidGenkidamaDataTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
