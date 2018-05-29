package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class FileLikeData extends GeneralData {

	public FileLikeData(String identifier, File f) throws IOException {
		super(identifier, Files.readAllBytes(f.toPath()));
		// TODO Auto-generated constructor stub
	}

	

}
