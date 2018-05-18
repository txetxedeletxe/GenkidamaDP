package exception;

public class IdentifierDoesNotExistException extends RuntimeException {

	public IdentifierDoesNotExistException(String string) {
		super(string);
	}
	
	public IdentifierDoesNotExistException() {
		super();
	}
	
	

}
