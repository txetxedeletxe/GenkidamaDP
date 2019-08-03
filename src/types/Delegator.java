package types;

public abstract class Delegator<T> {

	private Handler<T> handler;
	
	public Delegator() {
		this(null);
	}
	
	public Delegator(Handler<T> handler) {
		this.handler = handler;
	}
	
	public void setHandler(Handler<T> handler) {
		this.handler = handler;
	}
	
	public Handler<T> getHandler() {
		return handler;
	}
	
}
