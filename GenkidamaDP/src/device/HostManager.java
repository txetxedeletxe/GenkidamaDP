package device;

import java.util.HashMap;
import java.util.Map;

public class HostManager {

	private Map<Long,DeviceHostInterface> hostMap;
	
	public HostManager() {
		this.hostMap = new HashMap<Long,DeviceHostInterface>();
	}
	
	public void addHost(DeviceHostInterface deviceHostInt) {
		hostMap.put(deviceHostInt.getClassId(), deviceHostInt);
	}

	public DeviceHostInterface getHost(Long hostId) {
		return hostMap.get(hostId);
	}
}
