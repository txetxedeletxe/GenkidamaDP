package types;
public abstract class Pipe<A, B> extends Delegator<B> implements Handler<A>{

	public Pipe() {
		super();
	}
	
	public Pipe(Handler<B> handler) {
		super(handler);
	}
	
	@Override
	public void handle(A a) throws Exception {
		
		B b = convert(a);
		delegate(b);
		
	}
	
	public B convert(A a) throws Exception {return null;}
	
	
}
