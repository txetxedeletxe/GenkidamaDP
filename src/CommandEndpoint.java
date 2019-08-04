import command.Command;
import types.Handler;
import types.Pipe;

/**
 * Objects of this class wrap end-points so that they can be addressed at
 * command level rather than binary
 *
 */
public class CommandEndpoint extends Pipe<byte[],Command>{

	private Endpoint endPoint;
	
	public CommandEndpoint(Endpoint endPoint) {
		super();
		
		this.endPoint = endPoint;
	}
	
	public CommandEndpoint(Endpoint endPoint, Handler<Command> commandHandler) {
		super(commandHandler);
		
		this.endPoint = endPoint;
		this.endPoint.setHandler(this);
		
	}
	
	@Override
	public Command convert(byte[] a) throws Exception {
		return super.convert(a);
	}

	
	
}
