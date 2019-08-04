package command;

public class ResultRetransmissionSuccessCommand extends ResultRetransmissionCommand{

	private DynamicType returnValue;
	
	public ResultRetransmissionSuccessCommand(DynamicType returnValue) {
		super(false);
		
		this.returnValue = returnValue;
	}

	public DynamicType getResult() {
		return returnValue;
	}
	
}
