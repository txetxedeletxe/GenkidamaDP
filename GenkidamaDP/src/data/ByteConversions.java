package data;

import java.nio.ByteBuffer;

public class ByteConversions {

	public static byte[] intToByte(int int1) {
		return ByteBuffer.allocate(4).putInt(int1).array();
	}
	
	public static byte[] longToByte(long long1) {
		return ByteBuffer.allocate(8).putLong(long1).array();
	}
	
	public static byte[] floatToByte(float fl1) {
		
		return ByteBuffer.allocate(4).putFloat(fl1).array();
	}
	
	public static byte[] doubleToByte(double db1) {
		
		return ByteBuffer.allocate(8).putDouble(db1).array();
	}
	
	public static byte[] charToByte(char ch1) {
		return ByteBuffer.allocate(2).putChar(ch1).array();
	}
	
	public static byte[] intArrayToByte(Integer[] intArray) {
		ByteBuffer bb = ByteBuffer.allocate(4*intArray.length);
		
		for(int i = 0 ; i < intArray.length ; i++) {
			bb.put(intToByte(intArray[i]));
		}
		return bb.array();
	}
	
	public static byte[] longArrayToByte(Long[] longArray) {
		ByteBuffer bb = ByteBuffer.allocate(8*longArray.length);
		for(int i = 0 ; i < longArray.length ; i++) {
			bb.put(longToByte(longArray[i]));
		}
		return bb.array();
	}
	
	public static byte[] floatArrayToByte(Float[] floatArray) {
		ByteBuffer bb = ByteBuffer.allocate(4*floatArray.length);
		for(int i = 0 ; i < floatArray.length ; i++) {
			bb.put(floatToByte(floatArray[i]));
		}
		return bb.array();
	}
	
	public static byte[] doubleArrayToByte(Double[] doubleArray) {
		ByteBuffer bb = ByteBuffer.allocate(8*doubleArray.length);
		for(int i = 0 ; i < doubleArray.length ; i++) {
			bb.put(doubleToByte(doubleArray[i]));
		}
		return bb.array();
	}
	
	public static byte[] charArrayToByte(Character[] charArray) {
		ByteBuffer bb = ByteBuffer.allocate(charArray.length*2);
		for(int i = 0 ; i < charArray.length ; i++) {
			bb.put(charToByte(charArray[i]));
		}
		return bb.array();
	}
	
}
