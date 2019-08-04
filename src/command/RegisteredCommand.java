package command;

public class RegisteredCommand extends Command{
	
	private short commandId;
	private ProtoCommand protoCommand;
	
	public RegisteredCommand(short commandId, ProtoCommand protoCommand) {
		super(RootType.PROTO);
		
		this.commandId = commandId;
		this.protoCommand = protoCommand;
	}
	
	public ProtoCommand getProtoCommand() {
		 return protoCommand;
	}
	
	public short getCommandId() {
		return commandId;
	}

}
