package net;

public abstract class GenkidamaInstructionAbstract extends GenkidamaRemoteStoreableImp {

	public GenkidamaInstructionAbstract(String identifier) {
		super(identifier);
	}

	@Override
	public final GenkidamaObjectType getObjectType() {
		
		return GenkidamaObjectType.INSTRUCTION;
	}

}
