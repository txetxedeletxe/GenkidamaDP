package packet;


import data.ComputationSpec;
import interfaces.Container;

public class ComputationPacket extends Container<ComputationSpec> implements GenkidamaPacket{

	/**
	 * 
	 */
	public static final long serialVersionUID = 7662497415788427299L;
	
	public ComputationPacket(ComputationSpec computationSpec) {
		super(computationSpec);
		
	}
	
	@Override
	public long getClassId() {
		// TODO Auto-generated method stub
		return serialVersionUID;
	}

	

	
}
