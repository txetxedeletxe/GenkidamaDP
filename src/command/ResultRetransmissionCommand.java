package command;

public abstract class ResultRetransmissionCommand extends ProtoCommand{

	private boolean error;
	
	public ResultRetransmissionCommand(boolean error) {
		super(ProtoType.RESULT_RETRANSMISSION);
		
		this.error = error;
	}
	
	public boolean isError() {
		return error;
	}

}
