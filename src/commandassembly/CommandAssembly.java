package commandassembly;

import java.nio.ByteBuffer;

import command.Command;
import command.MetaCommand;
import command.ProtoCommand;
import types.Assembly;

public class CommandAssembly extends DispatchingAssembly<Command,ByteBuffer> {
	
	public CommandAssembly(Assembly<ProtoCommand,byte[]> protoAssembly, Assembly<MetaCommand,byte[]> metaAssembly) {
		
		super(new Assembly[]{protoAssembly,metaAssembly});
		
	}
	
	public ByteBuffer disassemble(Command command) {
		ByteBuffer bb2 = super.disassemble(command);
		ByteBuffer bb = ByteBuffer.allocate(bb2.capacity());
		
		bb.put((byte)command.getRootType().ordinal());
		bb.put(bb2);
		
		return bb;
	}

	@Override
	public int dispatchB(ByteBuffer b) {
		return b.get();
	}

	@Override
	public int dispatchA(Command a) {
		return a.getRootType().ordinal();
	}
	
}
