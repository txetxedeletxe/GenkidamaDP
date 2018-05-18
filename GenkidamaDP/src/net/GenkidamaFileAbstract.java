package net;

public abstract class GenkidamaFileAbstract extends GenkidamaRemoteStoreableImp {

	public GenkidamaFileAbstract(String identifier) {
		super(identifier);
		
	}

	@Override
	public final GenkidamaObjectType getObjectType() {
		return GenkidamaObjectType.FILE;
	}

}
