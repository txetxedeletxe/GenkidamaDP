package data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.RandomAccess;

public class GenkidamaFile extends FileLikeData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047511502186044698L;

	public GenkidamaFile(String id, File f) throws IOException {
		super(id, f);
		
	}

	@Override
	public final GenkidamaDataType getGenkidamaStoreableType() {
		return GenkidamaDataType.FILE;
	}


	
	

	

}
