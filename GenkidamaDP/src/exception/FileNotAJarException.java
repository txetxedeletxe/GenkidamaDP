package exception;

public class FileNotAJarException extends RuntimeException {

	
	public FileNotAJarException() {
		super();
	}
	
	public FileNotAJarException(String str) {
		super(str);
	}
}
