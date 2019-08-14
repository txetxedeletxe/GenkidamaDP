package commandassembly;

import java.nio.ByteBuffer;
import command.ProtoCommand;
import types.Assembly;

@SuppressWarnings({"rawtypes","unchecked"})
public class ProtoCommandAssembly implements Assembly<ProtoCommand, RawCommand>{

	
	private Assembly[] assemblers;
	
	public ProtoCommandAssembly(Assembly<? extends ProtoCommand,RawCommand>[] assemblers) {
		this.assemblers = assemblers;
	}
	
	@Override
	public ProtoCommand assemble(RawCommand rawCommand) {
		
		byte[] header = rawCommand.getHeader(3);
		rawCommand.removeHeader(3);
		
		ByteBuffer bbuffer = ByteBuffer.wrap(header); 
		bbuffer.flip();
		
		short commandId = bbuffer.getShort();
		byte protoTypeByte = bbuffer.get();
		
		
		ProtoCommand pc = (ProtoCommand)assemblers[protoTypeByte].assemble(rawCommand);
		pc.setCommandId(commandId);
		
		return pc;
	}

	@Override
	public RawCommand disassemble(ProtoCommand protoCommand) {
		
		short commandId = protoCommand.getCommandId();
		byte protoTypeByte = (byte)protoCommand.getProtoType().ordinal();
		
		RawCommand rawCommand = (RawCommand)assemblers[protoTypeByte].disassemble(protoCommand);
		
		ByteBuffer bbuffer = ByteBuffer.allocate(3);
		
		bbuffer.putShort(commandId);
		bbuffer.put(protoTypeByte);
		bbuffer.flip();
		
		byte[] header = bbuffer.array();
		
		rawCommand.addHeader(header);
		
		return rawCommand;
		
		
	}

}
