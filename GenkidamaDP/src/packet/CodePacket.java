package packet;

public class CodePacket implements GenkidamaPacket {

	private static final long serialVersionUID = -2492205666408292821L;

	private String str;
	
	public CodePacket(String str) {
		this.str = str;
	}
	
	public String getCode() {
		return str;
	}
}
