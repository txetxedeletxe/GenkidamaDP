package commandassembly;

import java.nio.ByteBuffer;
import command.MetaCommand;
import types.Assembly;

@SuppressWarnings({"rawtypes","unchecked"})
public class MetaCommandAssembly implements Assembly<MetaCommand, ByteBuffer>{

	
	private Assembly[] assemblers;
	
	public MetaCommandAssembly(Assembly<? extends MetaCommand,ByteBuffer>[] assemblers) {
		this.assemblers = assemblers;
	}
	
	@Override
	public MetaCommand assemble(ByteBuffer bbuffer) {
	
		byte metaTypeByte = bbuffer.get();
		
		MetaCommand mc = (MetaCommand)assemblers[metaTypeByte].assemble(bbuffer);
		
		return mc;
	}

	@Override
	public ByteBuffer disassemble(MetaCommand metaCommand) {
		
		byte metaTypeByte = (byte)metaCommand.getMetaType().ordinal();
		
		ByteBuffer bb = (ByteBuffer)assemblers[metaTypeByte].disassemble(metaCommand);
		
		ByteBuffer bb2 = ByteBuffer.allocate(bb.remaining() + 1);
		
		bb2.put(metaTypeByte);
		bb2.put(bb);
		bb2.flip();
		
		return bb2;
		
		
	}

}
