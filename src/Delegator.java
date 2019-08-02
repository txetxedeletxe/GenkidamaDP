
public abstract class Delegator<T> {

	private Handler<T> handler;
	
	public Delegator() {}
	
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
