package types;
public abstract class RequestDaemon<T> extends Delegator<T> implements Runnable{

	private int pollingGap = 100;
	private boolean running = false;
	
	
	public RequestDaemon() {
		this(null);
	}
	
	public RequestDaemon(Handler<T> handler) {
		super(handler);
	}
	
	public int getPollingGap() {
		return pollingGap;
	}
	
	public void setPollingGap(int pollingGap) {
		if (pollingGap > 0)
			this.pollingGap = pollingGap;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
	}
	


}
