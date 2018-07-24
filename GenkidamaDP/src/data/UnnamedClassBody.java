package data;

public class UnnamedClassBody implements GenkidamaSpecializedData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 739071972107821549L;
	
	private String[] importedLibraries;
	private String[] implementedInterfaces;
	private String extendedClass;
	private String classBody;
	
	private boolean isPublic;
	
	public UnnamedClassBody(String[] importedLibraries, String[] implementedInterfaces, String extendedClass,
			String classBody,boolean isPublic  ) {
		
		this.importedLibraries = importedLibraries;
		this.implementedInterfaces = implementedInterfaces;
		this.extendedClass = extendedClass;
		this.classBody = classBody;
		this.isPublic = isPublic;
		
	}
	
	public String getSpecificClass(String className) {
		
		StringBuilder sb = new StringBuilder();
		
		if (importedLibraries != null) {
			for (String s : importedLibraries) {
				sb.append("import " + s + ";\n");
			}
			
		}
		
		if (isPublic)
			sb.append("public ");
		
		sb.append("class " + className + " ");
		
		if (extendedClass != null) {
			sb.append("extends " + extendedClass + " ");
		}
		
		if (implementedInterfaces != null) {
			sb.append("implements ");
			for (String s : implementedInterfaces) {
				sb.append(s + ", ");
			}
			sb.deleteCharAt(sb.length()-2);
		}
		
		sb.append("{\n");
		sb.append(classBody);
		sb.append("}\n");
		
		return sb.toString();
	}

	public String[] getImportedLibraries() {
		return importedLibraries;
	}

	public String[] getImplementedInterfaces() {
		return implementedInterfaces;
	}

	public String getExtendedClass() {
		return extendedClass;
	}

	public String getClassBody() {
		return classBody;
	}

	public boolean isPublic() {
		return isPublic;
	}

	
}

	

