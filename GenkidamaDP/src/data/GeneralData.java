package data;

public abstract class GeneralData extends GenkidamaData {

	private byte[] primitiveData;
	
	public GeneralData(String identifier,byte[] primitiveData) {
		super(identifier);
	}
	
	public byte[] getByteArray() {
		return primitiveData;
	}
}
