package command;

public abstract class ProtoCommand extends Command{
	
	public enum ProtoType {
		COMPUTATION_KICK,
		RESULT_RETRANSMISSION
	}
	
	private short commandId;
	private ProtoType protoType;
	
	public ProtoCommand(ProtoType protoType) {
		super(RootType.PROTO);
		
		this.protoType = protoType;
	}
	
	public ProtoCommand(ProtoType protoType, short commandId) {
		super(RootType.PROTO);
		
		this.commandId = commandId;
		this.protoType = protoType;
	}
	
	public ProtoType getProtoType() {
		return protoType;
	}
	
	public short getCommandId() {
		return commandId;
	}
	
	public void setCommandId(short commandId) {
		this.commandId = commandId;
	}

}
