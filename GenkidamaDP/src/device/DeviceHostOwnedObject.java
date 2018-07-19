package device;

import interfaces.OwnedObject;

public abstract class DeviceHostOwnedObject<T> extends OwnedObject<DeviceHostInterface,T> {

	
	public DeviceHostOwnedObject(DeviceHostInterface owner,T content) {
		super(owner,content);
		
	}

}
