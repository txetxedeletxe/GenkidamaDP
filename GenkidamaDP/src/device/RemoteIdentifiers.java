package device;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import exception.IdentifierAlreadyExistsException;

public class RemoteIdentifiers {

	private Map<String,Set<String>> remoteIdentifiers;
	
	public RemoteIdentifiers(String[] defaultIdentifierCategories) {
		
		if (defaultIdentifierCategories == null)
			throw new NullPointerException("The array of strings \"defaultIdentifierCategories\" cannot"
					+ " be null (it can be empty)");
		
		remoteIdentifiers = new HashMap<String, Set<String>>();
	
		for (String identifierCategory : defaultIdentifierCategories) {
			
			remoteIdentifiers.put(identifierCategory, new HashSet<String>());
		}
	}
	
	public RemoteIdentifiers() {
		new RemoteIdentifiers(new String[] {});
	}
	
	public void addIdentifier(String identifierCategory, String identifier) {
		
		if (!remoteIdentifiers.containsKey(identifierCategory))
			throw new RuntimeException("Remote identifier category does not exist");
		
		Set<String> set = remoteIdentifiers.get(identifierCategory);
		
		if (set.contains(identifier))
			throw new IdentifierAlreadyExistsException("The identifier \"" + identifier + " \" already exists in"
					+ " the category \"" + identifierCategory + "\".");
		set.add(identifier);
	}
	
	public void deleteIdentifier(String identifierCategory, String identifier) {
		
		remoteIdentifiers.get(identifierCategory).remove(identifier);
	}
	
	public boolean containsIdentifier(String identifierCategory, String identifier) {
		
		return remoteIdentifiers.get(identifierCategory).contains(identifier);
	}
	
	public void addIdentifierCategory(String identifierCategory) {
		remoteIdentifiers.put(identifierCategory, new HashSet<String>());
	}
	
	public void removeIdentifierCategory(String identifierCategory) {
		remoteIdentifiers.remove(identifierCategory);
	}
	
	public boolean identifierCategoryExists(String identifierCategory) {
		return remoteIdentifiers.containsKey(identifierCategory);
	}
}
