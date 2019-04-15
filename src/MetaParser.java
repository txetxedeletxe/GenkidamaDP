import java.util.List;

public class MetaParser implements CommandHandler{

	private CommandHandler ackHandler;

	
	public MetaParser(CommandHandler ackHandler) {
		super();
		this.ackHandler = ackHandler;
	}


	public void handle(List<Byte> section) {
		
		if (section.isEmpty()) { //This is an error
		
			return;
		}
		
		byte firstByte =  section.get(0);
		
		List<Byte> newSection = section.subList(1, section.size());
		
		switch (firstByte) {
		
		case ProtocolSpec.ACK_COMMAND_META_ID:
			ackHandler.handle(newSection);
			break;
			
			
		default: //Error, no such command
			break;
				
		}
		
		
		
		
	}

}
