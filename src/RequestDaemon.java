public abstract class RequestDaemon<T> implements Runnable{

	private int pollingGap = 100;
	private boolean running = false;
	
	private Handler<T> handler;
	
	public RequestDaemon() {}
	
	public RequestDaemon(Handler<T> handler) {
		this.handler = handler;
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
	
	public void setHandler(Handler<T> handler) {
		this.handler = handler;
	}
	
	public Handler<T> getHandler() {
		return handler;
	}

}
