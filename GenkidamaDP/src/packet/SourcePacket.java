package packet;

import data.UnnamedClassBody;

public class SourcePacket implements GenkidamaPacket {

	public static final long serialVersionUID = -2492205666408292821L;

	private UnnamedClassBody ucb;
	private String identifier;
	
	public SourcePacket(UnnamedClassBody ucb, String identifier) {
		this.ucb = ucb;
		this.identifier = identifier;
	}
	
	public UnnamedClassBody getCode() {
		return ucb;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public long getClassId() {
		
		return SourcePacket.serialVersionUID;
	}
}
