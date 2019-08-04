package command;

public abstract class Command {

	public enum RootType{
		PROTO,META
	}
	
	private RootType type;
	
	public Command(RootType type) {
		this.type = type;
	}
	
	public RootType getRootType() {
		return this.type;
	}
}
