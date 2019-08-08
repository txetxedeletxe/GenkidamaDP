package endpoint;
import command.Command;
import types.Handler;
import types.Pipe;

/**
 * Objects of this class wrap end-points so that they can be addressed at
 * command level rather than binary
 *
 */
public class CommandEndpoint extends Pipe<byte[],Command>{

	private StreamEndpoint endPoint;
	
	public CommandEndpoint(StreamEndpoint endPoint) {
		super();
		
		this.endPoint = endPoint;
	}
	
	public CommandEndpoint(StreamEndpoint endPoint, Handler<Command> commandHandler) {
		super(commandHandler);
		
		this.endPoint = endPoint;
		this.endPoint.setHandler(this);
		
	}
	
	@Override
	public Command convert(byte[] a) throws Exception {
		return super.convert(a);
	}

	
	
}
