package sendable.query.storeable;

import java.io.Serializable;

import data.GenkidamaData;

@SuppressWarnings("serial")
public abstract class RemoteStoreable implements Serializable{

	
	private String identifier;
	private GenkidamaData genkidamaData;
	
	public RemoteStoreable(String identifier, GenkidamaData genkidamaData) {
		
		this.identifier = identifier;
		this.genkidamaData = genkidamaData;
	}
	
	public GenkidamaData getGenkidamaData() {
	
		return genkidamaData;
	}
	
	public String getIdentifier(){
		
		return identifier;
	}

	
}
