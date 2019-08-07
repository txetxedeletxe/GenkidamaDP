package command;

public class ResultRetransmissionSuccessCommand extends ResultRetransmissionCommand{

	private DynamicType returnValue;
	
	public ResultRetransmissionSuccessCommand() {
		super(false);
	}
	
	public ResultRetransmissionSuccessCommand(DynamicType returnValue) {
		super(false);
		
		this.returnValue = returnValue;
	}
	
	public ResultRetransmissionSuccessCommand(DynamicType returnValue, short commandId) {
		super(false,commandId);
		
		this.returnValue = returnValue;
	}

	public DynamicType getResult() {
		return returnValue;
	}
	
	public void setResult(DynamicType returnValue) {
		this.returnValue = returnValue;
	}
	
}
