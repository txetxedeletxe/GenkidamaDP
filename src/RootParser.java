import java.util.List;

public class RootParser implements CommandHandler{

	private CommandHandler metaCommandHandler;
	private CommandHandler protocolCommandHandler;
	
	
	
	public RootParser(CommandHandler metaCommandHandler, CommandHandler protocolCommandHandler) {
		super();
		this.metaCommandHandler = metaCommandHandler;
		this.protocolCommandHandler = protocolCommandHandler;
	}


	public void handle(List<Byte> section) {
		
		if (section.isEmpty()) { //This is an error
		
			return;
		}
		
		byte firstByte =  section.get(0);
		
		List<Byte> newSection = section.subList(1, section.size());
		
		switch (firstByte) {
		
		case ProtocolSpec.META_NODE_ID:
			metaCommandHandler.handle(newSection);
			break;
			
		case ProtocolSpec.PROTOCOL_NODE_ID:
			protocolCommandHandler.handle(newSection);
			break;
			
		default: //Error, no such node
			break;
				
		}
		
		
		
		
	}
}
