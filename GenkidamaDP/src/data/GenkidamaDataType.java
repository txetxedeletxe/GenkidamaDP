package data;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import exception.NotValidGenkidamaDataTypeException;

//This enum enumerates the types of data types that can be understood by all genkidama devices

public enum GenkidamaDataType implements GenkidamaContent{
	INT,LONG,FLOAT,DOUBLE,CHAR,
	INT_ARRAY,LONG_ARRAY,FLOAT_ARRAY,DOUBLE_ARRAY,CHAR_ARRAY;

	
	private static Map<Class<?>,GenkidamaDataType> typeMap = initializeTypeMap();

	
	public static GenkidamaDataType getGenkidamaDataType(Object data) throws NotValidGenkidamaDataTypeException {
		
			GenkidamaDataType returnend = typeMap.get(data.getClass());
			if (returnend != null)
				return returnend;
			else
				throw new NotValidGenkidamaDataTypeException("The class " + data.getClass() + " of object " + data + " is not a valid genkidama data type.");
		
	}

	private static Map<Class<?>, GenkidamaDataType> initializeTypeMap() {
		
		Map<Class<?>, GenkidamaDataType> returnend = new HashMap<Class<?>, GenkidamaDataType>();
	
	
		returnend.put(Integer.class, INT);
		returnend.put(Long.class, LONG);
		returnend.put(Float.class, FLOAT);
		returnend.put(Double.class, DOUBLE);
		returnend.put(Character.class, CHAR);
		

		returnend.put(Integer[].class,INT_ARRAY);
		returnend.put(Long[].class,LONG_ARRAY);
		returnend.put(Float[].class,FLOAT_ARRAY);
		returnend.put(Double[].class, DOUBLE_ARRAY);
		returnend.put(Character[].class, CHAR_ARRAY);
		
		return returnend;
		
		
	}

	public byte[] dataToByte(Object datum) {
		
		switch (this) {
			
			case INT:
				return ByteConversions.intToByte((int)datum);
			case LONG:
				return ByteConversions.longToByte((long)datum);
			
			case FLOAT:
				return ByteConversions.floatToByte((float)datum);
				
			case DOUBLE:
				return ByteConversions.doubleToByte((double)datum);
				
			case CHAR:
				return ByteConversions.charToByte((char)datum);
				
			case INT_ARRAY:
				return ByteConversions.intArrayToByte((Integer[])datum);
				
			case LONG_ARRAY:
				return ByteConversions.longArrayToByte((Long[])datum);
				
			case FLOAT_ARRAY:
				return ByteConversions.floatArrayToByte((Float[])datum);
				
			case DOUBLE_ARRAY:
				return ByteConversions.doubleArrayToByte((Double[])datum);
				
			case CHAR_ARRAY:
				return ByteConversions.charArrayToByte((Character[])datum);
		
			default:
				return null;
				
		}
		
	}
	
	@Override
	public byte[] toByte() {
		
		return new byte[] {(byte)this.ordinal()};
	}

	public boolean isArray() {
		return this.ordinal() >= INT_ARRAY.ordinal();
	}

	public int getByteSize(Object datum) {
	
		switch (this) {
		
		case INT:
			return 4;
			
		case LONG:
			return 8;
			
		case FLOAT:
			return 4;
			
		case DOUBLE:
			return 8;
			
		case CHAR:
			return 2;
			
		case INT_ARRAY:
			return 4*((Object[]) datum).length;
			
		case LONG_ARRAY:
			return 8*((Object[]) datum).length;
		
		case FLOAT_ARRAY:
			return 4*((Object[]) datum).length;
		
		case DOUBLE_ARRAY:
			return 8*((Object[]) datum).length;
			
		case CHAR_ARRAY:
			return ((Object[]) datum).length * 2;
			
		default:
			return 0;
		}
	}
	
	


	
}
