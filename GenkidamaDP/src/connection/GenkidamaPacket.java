package connection;

import java.io.Serializable;

import interfaces.Container;
import interfaces.Discriminable;


public class GenkidamaPacket extends Container<Serializable> implements Serializable,Discriminable{

	private static final long serialVersionUID = 7949427941000130836L;
	
	private PacketType type;
	public GenkidamaPacket(Serializable content,PacketType type) {
		super(content);
		this.type = type;
	}

	@Override
	public long getId() {
		
		return type.ordinal();
	}

}
