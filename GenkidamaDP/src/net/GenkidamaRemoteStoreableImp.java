package net;

abstract class GenkidamaRemoteStoreableImp implements GenkidamaRemoteStoreable{

	private String identifier;
	
	public  GenkidamaRemoteStoreableImp(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String getIdentifier() {
		
		return identifier;
	}

}
