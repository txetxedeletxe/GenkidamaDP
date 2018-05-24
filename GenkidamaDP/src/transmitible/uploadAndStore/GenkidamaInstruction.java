package transmitible.uploadAndStore;

public class GenkidamaInstruction extends RemoteStoreable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8667602491757401669L;

	public GenkidamaInstruction(String id) {
		super(id);
	}

	@Override
	public StoreableType getGenkidamaStoreableType() {
		return StoreableType.INSTRUCTION;
	}

	

	

}
