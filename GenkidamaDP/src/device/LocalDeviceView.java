package device;

import java.util.HashMap;
import java.util.Map;

public class LocalDeviceView {

	private Map<String,String> codeIdentifierPhysicalMapping;
	
	public LocalDeviceView(){
		
		codeIdentifierPhysicalMapping = new HashMap<String,String>();
	}
	
	public void addCodeIdentifier(String identifier, String physicalName) {
		codeIdentifierPhysicalMapping.put(identifier, physicalName);
		
	}
	
	public String getPhysicalCodeName(String codeIdentifier) {
		return codeIdentifierPhysicalMapping.get(codeIdentifier);
	}

}
