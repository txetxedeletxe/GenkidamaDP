package net;

public abstract class GenkidamaConstantsAbstract extends GenkidamaRemoteStoreableImp {

	
	public GenkidamaConstantsAbstract(String identifier) {
		super(identifier);
		
	}

	public final GenkidamaObjectType getObjectType() {
		return GenkidamaObjectType.CONSTANT;
	}
}
