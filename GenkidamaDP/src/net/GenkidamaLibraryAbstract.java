package net;

public abstract class GenkidamaLibraryAbstract extends GenkidamaRemoteStoreableImp{

	public GenkidamaLibraryAbstract(String identifier) {
		super(identifier);
	}

	@Override
	public final GenkidamaObjectType getObjectType() {
		
		return GenkidamaObjectType.INSTRUCTION;
	}
	
}
