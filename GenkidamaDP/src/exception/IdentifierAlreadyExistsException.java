package exception;

public class IdentifierAlreadyExistsException extends RuntimeException {

	public IdentifierAlreadyExistsException(String string) {
		
		super(string);
	}

	
	public IdentifierAlreadyExistsException() {
		
		super();
	}
}
