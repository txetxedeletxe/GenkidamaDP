package transmitible.uploadAndStore;

import transmitible.EventType;
import transmitible.GenkidamaTransmitable;

public abstract class RemoteStoreable implements GenkidamaTransmitable{

	private String identifier;

	public RemoteStoreable(String identifier) {
		this.identifier = identifier;
	}

	
	public String getIdentifier() {
		return identifier;
	}
	
	
	@Override
	public final EventType getGenkidamaEventType() {
		
		return EventType.UPLOAD_AND_STORE;
	}
	
	public abstract StoreableType getGenkidamaStoreableType();
	
	
}
