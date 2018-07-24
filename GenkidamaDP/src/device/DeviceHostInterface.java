package device;

import data.ComputationResult;
import interfaces.ControllerInterface;
import interfaces.Discriminable;

public interface DeviceHostInterface extends  ControllerInterface{

	public void getComputationResult(ComputationResult cr);
}
