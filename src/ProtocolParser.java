import java.util.List;

public class ProtocolParser implements CommandHandler{

	private CommandHandler computationKickHandler;
	private CommandHandler resultRetransmissionHandler;
	
	public ProtocolParser(CommandHandler computationKickHandler, CommandHandler resultRetransmissionHandler) {
		super();
		this.computationKickHandler = computationKickHandler;
		this.resultRetransmissionHandler = resultRetransmissionHandler;
	}


	public void handle(List<Byte> section) {
		
		if (section.isEmpty()) { //This is an error
		
			return;
		}
		
		byte firstByte =  section.get(0);
		
		List<Byte> newSection = section.subList(1, section.size());
		
		switch (firstByte) {
		
		case ProtocolSpec.CKCK_COMMAND_PROTOCOL_ID:
			computationKickHandler.handle(newSection);
			break;
			
		case ProtocolSpec.RESTRANSMISSION_COMMAND_PROTOCOL_ID:
			resultRetransmissionHandler.handle(newSection);
			break;
			
		default: //Error, no such command
			break;
				
		}
		
	}
}
