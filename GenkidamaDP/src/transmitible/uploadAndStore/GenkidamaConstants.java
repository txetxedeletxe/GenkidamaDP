package transmitible.uploadAndStore;

public class GenkidamaConstants extends RemoteStoreable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2128879626358023275L;

	public GenkidamaConstants(String id) {
		super(id);
	}

	@Override
	public StoreableType getGenkidamaStoreableType() {
		return StoreableType.CONSTANT;
	}

	

	

}
