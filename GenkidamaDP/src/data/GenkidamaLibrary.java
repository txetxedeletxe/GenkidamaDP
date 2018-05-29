package data;

import java.io.File;
import java.io.IOException;

import exception.FileNotAJarException;

public class GenkidamaLibrary extends FileLikeData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2657919850925489566L;

	public GenkidamaLibrary(String id, File jarFile) throws IOException {
		super(id,checkJar(jarFile));
	}

	

	@Override
	public final GenkidamaDataType getGenkidamaStoreableType() {
		return GenkidamaDataType.LIBRARY;
	}

	
	private static File checkJar(File jarFile) {
		
		String filename = jarFile.getName();
		
		//Exception prepared in case something goes wrong
		FileNotAJarException exception = new FileNotAJarException("The file \"" + jarFile.getName() + "\" is not a jar file");
		
		if (filename.length() < 5)
			throw exception;
		
		if (filename.substring(filename.length()-5, filename.length()-1).equals(".jar"))
			throw exception;
		
		return jarFile;
		
			
	}
	

}
