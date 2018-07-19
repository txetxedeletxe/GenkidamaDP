package device;

public class OwnedClass extends DeviceHostOwnedObject<Class<?>> {

	
	public OwnedClass(Class<?> classs,DeviceHostInterface ownerId) {
		
		super(ownerId,classs);
	}
	
	
}

