package command;

public abstract class ProtoCommand {

	public enum ProtoType{
		COMPUTATION_KICK,
		RESULT_RETRANSMISSION
	}
	
	private ProtoType protoType;
	
	public ProtoCommand(ProtoType protoType) {
		this.protoType = protoType;
	}
	
	public ProtoType getProtoType() {
		return protoType;
	}
	
}
