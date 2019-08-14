package commandassembly;

import command.Command;
import command.Command.RootType;
import command.MetaCommand;
import command.ProtoCommand;
import types.Assembly;

public class CommandAssembly implements Assembly<Command, RawCommand>{

	private Assembly<ProtoCommand,RawCommand> protoAssembly;
	private Assembly<MetaCommand,RawCommand> metaAssembly;
	
	public CommandAssembly(Assembly<ProtoCommand,RawCommand> protoAssembly, Assembly<MetaCommand,RawCommand> metaAssembly) {
		this.protoAssembly = protoAssembly;
		this.metaAssembly = metaAssembly;
	}
	
	@Override
	public Command assemble(RawCommand rawCommand) {
		
		
		byte[] header = rawCommand.getHeader(1);
		rawCommand.removeHeader(1);
		
		byte rootTypeByte = header[0];
				
		if (rootTypeByte == RootType.PROTO.ordinal())
			return protoAssembly.assemble(rawCommand);
		else if (rootTypeByte == RootType.META.ordinal())
			return metaAssembly.assemble(rawCommand);
		else 
			throw new RuntimeException();
		
	}

	@Override
	public RawCommand disassemble(Command command) {
		
		RootType rootType = command.getRootType();
		RawCommand rawCommand = null;
		
		if (rootType == RootType.PROTO)
			rawCommand = protoAssembly.disassemble((ProtoCommand)command);
		else
			rawCommand = metaAssembly.disassemble((MetaCommand)command);
		
		byte[] header = new byte[] {(byte)rootType.ordinal()};
		
		rawCommand.addHeader(header);
		
		return rawCommand;
		
	}

}
