package device;

import interfaces.OwnedObject;

public class DeviceHostOwnedObject<T> extends OwnedObject<DeviceHostInterface,T> {

	
	public DeviceHostOwnedObject(DeviceHostInterface owner,T content) {
		super(owner,content);
		
	}

}
