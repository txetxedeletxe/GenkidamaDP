package data;

public class GenkidamaInstruction extends GenkidamaData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8667602491757401669L;

	public GenkidamaInstruction(String id) {
		super(id);
	}

	@Override
	public final GenkidamaDataType getGenkidamaStoreableType() {
		return GenkidamaDataType.INSTRUCTION;
	}

	

	

}
