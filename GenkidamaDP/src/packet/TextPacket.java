package packet;

public class TextPacket implements GenkidamaPacket{

	
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
