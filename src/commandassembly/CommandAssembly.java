package commandassembly;


import java.util.Arrays;

import command.Command;
import command.MetaCommand;
import command.ProtoCommand;
import types.Assembly;

public class CommandAssembly implements Assembly<Command, byte[]> {
	
	private Assembly<ProtoCommand,byte[]> protoAssembly;
	private Assembly<MetaCommand,byte[]> metaAssembly;
	
	public CommandAssembly(Assembly<ProtoCommand,byte[]> protoAssembly, Assembly<MetaCommand,byte[]> metaAssembly) {
		
		this.protoAssembly = protoAssembly;
		this.metaAssembly = metaAssembly;
	}
	
	public byte[] disassemble(Command command) {
		
		byte rootType = (byte)command.getRootType().ordinal();
		byte[] rest;
		
		if (rootType == 0)
			rest = protoAssembly.disassemble((ProtoCommand)command);
		else
			rest = metaAssembly.disassemble((MetaCommand)command);
		
		byte[] rest2 = new byte[rest.length + 1];
		
		rest2[0] = rootType;
		for (int i = 0; i < rest.length ; i++)
			rest2[i+1] = rest[i];
		
		return rest2;
	}

	@Override
	public Command assemble(byte[] b) {
		
		byte[] b2 = Arrays.copyOfRange(b, 1, b.length);
		
		if (b[0] == 0)
			return protoAssembly.assemble(b2);
		else
			return metaAssembly.assemble(b2);
	}

	
	
}
