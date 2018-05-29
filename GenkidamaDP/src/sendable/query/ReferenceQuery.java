package sendable.query;

@SuppressWarnings("serial")
public abstract class ReferenceQuery implements GenkidamaQuery {

private String identifier;
	
	public ReferenceQuery(String identifier) {
		
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return this.identifier;
	}

}
