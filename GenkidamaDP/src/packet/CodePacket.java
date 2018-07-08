package packet;

public class CodePacket implements GenkidamaPacket {

	public static final long serialVersionUID = -2492205666408292821L;

	private String code;
	private String identifier;
	
	public CodePacket(String code, String identifier) {
		this.code = code;
		this.identifier = identifier;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	@Override
	public long getId() {
		
		return CodePacket.serialVersionUID;
	}
}
