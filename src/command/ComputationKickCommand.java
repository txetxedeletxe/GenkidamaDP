package command;

import java.util.List;

public class ComputationKickCommand extends ProtoCommand{
	
	private short programId;
	private List<DynamicType> parameters;
	
	public ComputationKickCommand() {
		super(ProtoType.COMPUTATION_KICK);
	}
	
	public ComputationKickCommand(short programId) {
		super(ProtoType.COMPUTATION_KICK);
		
		this.programId = programId;
	}
	
	public ComputationKickCommand(short programId, List<DynamicType> parameters) {
		super(ProtoType.COMPUTATION_KICK);
		
		this.programId = programId;
		this.parameters = parameters;
	}
	
	public ComputationKickCommand(short programId, List<DynamicType> parameters, short commandId) {
		super(ProtoType.COMPUTATION_KICK,commandId);
		
		this.programId = programId;
		this.parameters = parameters;
	}
	
	public short getProgamId() {
		return programId;
	}
	
	public List<DynamicType> getParameters(){
		return parameters;
	}
	
	public void setProgamId(short programId) {
		this.programId = programId;
	}
	
	public void setParameters(List<DynamicType> parameters){
		this.parameters = parameters;
	}

}
