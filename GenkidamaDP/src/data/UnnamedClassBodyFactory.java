package data;

import interfaces.ObjectBuilder;

public class UnnamedClassBodyFactory implements ObjectBuilder<UnnamedClassBody> {

	
	private String[] importedLibraries = null;
	private String[] implementedInterfaces = null;
	private String extendedClass = null;
	private String classBody = null;
	private boolean isPublic = true;
	
	public UnnamedClassBodyFactory(String classBody) {
		this.classBody = classBody;
	}
	
	@Override
	public UnnamedClassBody construct() {
		return new UnnamedClassBody(importedLibraries, implementedInterfaces, extendedClass, classBody, isPublic);
	}

	@Override
	public void setProperty(String propertyName, Object value) {
		
		if (propertyName.equals("importedlib")) {
			String[] c = (String[]) value;
			if (c == null || c.length == 0)
				importedLibraries = null;
			else
				importedLibraries = c;
		}
		else if (propertyName.equals("implementedinterfaces")) {
			String[] c = (String[]) value;
			if (c == null || c.length == 0)
				implementedInterfaces = null;
			else
				implementedInterfaces = c;
		}
		else if (propertyName.equals("extendedclass")) {
			extendedClass = (String)value;
		}
		else if (propertyName.equals("classbody")) {
			classBody = (String) value;
		}
		else if (propertyName.equals("ispublic")) {
			isPublic = (Boolean) value;
		}
	}

	@Override
	public void reset() {
		
		importedLibraries = null;
		implementedInterfaces = null;
		extendedClass = null;
		isPublic = true;
	}

}
