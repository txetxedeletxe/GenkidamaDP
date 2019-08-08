package commandassembly;

import java.nio.ByteBuffer;

import command.Command;
import command.Command.RootType;
import command.MetaCommand;
import command.ProtoCommand;
import types.Assembly;

public class CommandAssembly implements Assembly<Command, ByteBuffer>{

	private Assembly<ProtoCommand,ByteBuffer> protoAssembly;
	private Assembly<MetaCommand,ByteBuffer> metaAssembly;
	
	public CommandAssembly(Assembly<ProtoCommand,ByteBuffer> protoAssembly, Assembly<MetaCommand,ByteBuffer> metaAssembly) {
		this.protoAssembly = protoAssembly;
		this.metaAssembly = metaAssembly;
	}
	
	@Override
	public Command assemble(ByteBuffer bbuffer) {
		
		
		byte rootTypeByte = bbuffer.get();
		
		if (rootTypeByte == RootType.PROTO.ordinal())
			return protoAssembly.assemble(bbuffer);
		else if (rootTypeByte == RootType.META.ordinal())
			return metaAssembly.assemble(bbuffer);
		else 
			throw new RuntimeException();
		
	}

	@Override
	public ByteBuffer disassemble(Command command) {
		
		RootType rootType = command.getRootType();
		ByteBuffer bb = null;
		
		if (rootType == RootType.PROTO)
			bb = protoAssembly.disassemble((ProtoCommand)command);
		else
			bb = metaAssembly.disassemble((MetaCommand)command);
		
		ByteBuffer bb2 = ByteBuffer.allocate(bb.remaining() + 1);
		
		bb2.put((byte)rootType.ordinal());
		bb2.put(bb);
		bb2.flip();
		
		return bb2;
			
	}

}
