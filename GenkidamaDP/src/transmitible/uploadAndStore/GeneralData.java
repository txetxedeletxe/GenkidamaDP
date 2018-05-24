package transmitible.uploadAndStore;

public abstract class GeneralData extends RemoteStoreable {

	private byte[] primitiveData;
	
	public GeneralData(String identifier,byte[] primitiveData) {
		super(identifier);
	}
	
	public byte[] getByteArray() {
		return primitiveData;
	}
}
