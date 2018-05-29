package sendable.query;

import data.GenkidamaData;

@SuppressWarnings("serial")
public abstract class ConstructiveQuery implements GenkidamaQuery{

	private GenkidamaData remoteStoreable;
	
	public ConstructiveQuery(GenkidamaData remoteStoreable) {
		this.remoteStoreable = remoteStoreable;
	}
	
	public GenkidamaData getRemoteStoreable() {
		return remoteStoreable;
	}
}
