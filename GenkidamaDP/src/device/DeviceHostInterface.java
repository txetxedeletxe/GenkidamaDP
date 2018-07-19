package device;

import data.ComputationResult;
import interfaces.ClassDiscriminable;
import interfaces.ControllerInterface;

public interface DeviceHostInterface extends ClassDiscriminable, ControllerInterface{

	public void getComputationResult(ComputationResult cr);
}
