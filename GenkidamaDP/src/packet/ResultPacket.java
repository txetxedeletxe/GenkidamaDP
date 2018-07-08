package packet;

import utils.Container;

public class ResultPacket extends Container<Object> implements GenkidamaPacket {


	public static final long serialVersionUID = -3486762253018481680L;

	
	public ResultPacket(Object result) {
		super(result);
	}
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return ResultPacket.serialVersionUID;
	}

}
