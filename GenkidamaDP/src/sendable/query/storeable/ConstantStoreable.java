package sendable.query.storeable;

import data.GenkidamaData;
import data.GenkidamaObject;

public class ConstantStoreable extends RemoteStoreable {

	public ConstantStoreable(String identifier, GenkidamaObject genkidamaData) {
		super(identifier, genkidamaData);
		
	}

	@Override
	public GenkidamaObject getGenkidamaData() {
		
		return (GenkidamaObject) super.getGenkidamaData();
	}
}
