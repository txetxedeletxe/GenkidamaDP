import command.Command;

public class CommandEndpoint extends Delegator<Command> implements Handler<byte[]>{

	private Endpoint endPoint;
	
	public CommandEndpoint(Endpoint endPoint) {
		this(endPoint,null);
	}
	
	public CommandEndpoint(Endpoint endPoint, Handler<Command> commandHandler) {
		super(commandHandler);
		
		this.endPoint = endPoint;
		this.endPoint.setHandler(this);
		
	}
	
	
	
	@Override
	public void handle(byte[] t) throws Exception {
		
	}

	
	
}
