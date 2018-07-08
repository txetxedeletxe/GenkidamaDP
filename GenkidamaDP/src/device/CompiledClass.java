package device;

public class CompiledClass extends DeviceHostOwnedObject<Class<?>> {

	
	public CompiledClass(Class<?> classs,DeviceHostInterface ownerId) {
		
		super(ownerId,classs);
	}
	
	
}

