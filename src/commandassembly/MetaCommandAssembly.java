package commandassembly;

import command.MetaCommand;
import types.Assembly;

@SuppressWarnings({"rawtypes","unchecked"})
public class MetaCommandAssembly implements Assembly<MetaCommand, RawCommand>{

	
	private Assembly[] assemblers;
	
	public MetaCommandAssembly(Assembly<? extends MetaCommand,RawCommand>[] assemblers) {
		this.assemblers = assemblers;
	}
	
	@Override
	public MetaCommand assemble(RawCommand rawCommand) {
	
		byte[] header = rawCommand.getHeader(1);
		rawCommand.removeHeader(1);
		
		byte metaTypeByte = header[0];
		
		MetaCommand mc = (MetaCommand)assemblers[metaTypeByte].assemble(rawCommand);
		
		return mc;
	}

	@Override
	public RawCommand disassemble(MetaCommand metaCommand) {
		
		byte metaTypeByte = (byte)metaCommand.getMetaType().ordinal();
		
		RawCommand rawCommand = (RawCommand)assemblers[metaTypeByte].disassemble(metaCommand);
		
		byte[] header = new byte[]{metaTypeByte};
		
		rawCommand.addHeader(header);
		
		return rawCommand;
		
	}

}
