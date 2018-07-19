package packet;


import data.ComputationResult;
import interfaces.Container;


public class ResultPacket extends Container<ComputationResult> implements GenkidamaPacket{

	
	public static final long serialVersionUID = -3486762253018481680L;

	
	public ResultPacket(ComputationResult result) {
		super(result);
		
		
	}
	
	@Override
	public long getClassId() {
		// TODO Auto-generated method stub
		return ResultPacket.serialVersionUID;
	}

}