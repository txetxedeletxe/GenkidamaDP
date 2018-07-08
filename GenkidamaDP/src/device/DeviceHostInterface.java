package device;

import utils.Discriminable;
import utils.GenkidamaRunnable;

public interface DeviceHostInterface extends Discriminable {

	public void getComputationResult(GenkidamaRunnable runnable);
}
