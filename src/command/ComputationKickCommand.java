package command;

import java.util.List;

public class ComputationKickCommand extends ProtoCommand{
	
	private short programId;
	private List<DynamicType> parameters;
	
	public ComputationKickCommand(short programId,List<DynamicType> parameters) {
		super(ProtoType.COMPUTATION_KICK);
		
		this.programId = programId;
		this.parameters = parameters;
	}
	
	public short getProgamId() {
		return programId;
	}
	
	public List<DynamicType> getParameters(){
		return parameters;
	}

}
