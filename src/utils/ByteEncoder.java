package utils;

public class ByteEncoder {

	public byte[] intToByte(int l) {
		
		byte[] ba = new byte[4];
		
		for (int i = 0 ; i < 4 ; i++) { //Traverse the bytes
			ba[i] = 0;
			for (int j = 0 ; j < 8 ; j++) { //Traverse the bits
				
				ba[i] |= (l & (1 << (i*8 + j)));
				
			}
		}
		
		return ba;
		
	}
}
