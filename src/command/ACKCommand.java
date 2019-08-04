package command;

import command.ProtoCommand.ProtoType;

public class ACKCommand extends MetaCommand{

	// Command info
	private short commandId;
	private ProtoType commandType;
	
	// result code
	private byte resutlCode;
	
	public ACKCommand(short commandId, ProtoType commandType, byte resultCode) {
		super(MetaType.ACK);
		
		this.commandId = commandId;
		this.commandType = commandType;
		this.resutlCode = resultCode;
	}
	
	

}
