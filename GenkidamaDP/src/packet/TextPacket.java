package packet;

public class TextPacket implements GenkidamaPacket{

	
	private static final long serialVersionUID = -4278673356794675224L;
	private String text;
	
	public TextPacket(String text) {
		
		this.text = text;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
}
