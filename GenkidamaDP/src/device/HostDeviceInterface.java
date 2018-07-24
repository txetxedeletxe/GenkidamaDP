package device;

import data.ComputationSpec;
import data.UnnamedClassBody;

public interface HostDeviceInterface {

	public String addSource(DeviceHostOwnedObject<UnnamedClassBody> source);
	public void kickComputation(DeviceHostOwnedObject<ComputationSpec> computationSpec);
}
