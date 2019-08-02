package command;

import java.nio.ByteBuffer;


public class CommandAssembly {

	public CommandAssembly() {}
	
	public Command assemble(byte[] byteArray) {
		
		ByteBuffer buffer = ByteBuffer.wrap(byteArray);
		return null;
		
	}
	public byte[] disassemble(Command command) {return null;}
	
}
