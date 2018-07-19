package host;

import data.UnnamedClassBody;
import interfaces.ControllerInterface;

public interface HostDeviceInterface extends ControllerInterface{

	public void sendSource(UnnamedClassBody unnamedClassBody,String sourceIdentifier);
	public long kickComputation(String sourceIdentifier);
	public Object getResult(long computationIdentifier);
	public boolean isResultReady(long computataionIdentifier);
	
}
