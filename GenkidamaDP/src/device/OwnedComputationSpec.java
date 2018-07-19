package device;

import data.ComputationSpec;

public class OwnedComputationSpec extends DeviceHostOwnedObject<ComputationSpec>{
	
	public OwnedComputationSpec(DeviceHostInterface owner, ComputationSpec spec) {
		super(owner,spec);
	}

}
