package interfaces;

import java.util.HashMap;
import java.util.Map;

public class Demultiplexor<T extends ClassDiscriminable> implements Handler<T> {

	private Map<Long,Handler<T>> demul;
	
	public Demultiplexor() {
	
		demul = new HashMap<Long,Handler<T>>();
	
	}
	
	public void addHandler(long discriminable,Handler<T> handler) {
		demul.put(discriminable, handler);
	}
	
	
	@Override
	public void handle(T t) {
		
		demul.get(t.getClassId()).handle(t);
		
	}

	
}
