package commandassembly;

import java.nio.ByteBuffer;
import command.ProtoCommand;
import types.Assembly;

@SuppressWarnings({"rawtypes","unchecked"})
public class ProtoCommandAssembly implements Assembly<ProtoCommand, ByteBuffer>{

	
	private Assembly[] assemblers;
	
	public ProtoCommandAssembly(Assembly<? extends ProtoCommand,ByteBuffer>[] assemblers) {
		this.assemblers = assemblers;
	}
	
	@Override
	public ProtoCommand assemble(ByteBuffer bbuffer) {
	
		short commandId = bbuffer.getShort();
		byte protoTypeByte = bbuffer.get();
		
		
		ProtoCommand pc = (ProtoCommand)assemblers[protoTypeByte].assemble(bbuffer);
		pc.setCommandId(commandId);
		
		return pc;
	}

	@Override
	public ByteBuffer disassemble(ProtoCommand protoCommand) {
		
		short commandId = protoCommand.getCommandId();
		byte protoTypeByte = (byte)protoCommand.getProtoType().ordinal();
		
		ByteBuffer bb = (ByteBuffer)assemblers[protoTypeByte].disassemble(protoCommand);
		
		ByteBuffer bb2 = ByteBuffer.allocate(bb.remaining() + 3);
		
		bb2.putShort(commandId);
		bb2.put(protoTypeByte);
		bb2.put(bb);
		bb2.flip();
		
		return bb2;
		
		
	}

}
