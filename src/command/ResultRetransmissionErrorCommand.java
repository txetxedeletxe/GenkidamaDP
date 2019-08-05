package command;

public class ResultRetransmissionErrorCommand extends ResultRetransmissionCommand{

	private String errorMessage;
	
	public ResultRetransmissionErrorCommand(String errorMessage) {
		super(true);
		
		this.errorMessage = errorMessage;
	}
	
	public ResultRetransmissionErrorCommand(String errorMessage, short commandId) {
		super(true,commandId);
		
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
