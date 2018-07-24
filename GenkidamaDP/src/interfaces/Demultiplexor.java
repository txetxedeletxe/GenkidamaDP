package interfaces;

import java.util.HashMap;
import java.util.Map;

public class Demultiplexor<T extends Discriminable> implements Handler<T> {

	private Map<Long,Handler<T>> demul;
	
	public Demultiplexor() {
	
		demul = new HashMap<Long,Handler<T>>();
	
	}
	
	public void addHandler(long discriminableId,Handler<T> handler) {
		
		demul.put(discriminableId, handler);
	}
	
	
	@Override
	public void handle(T t) {
		
		demul.get(t.getId()).handle(t);
		
		
	}

	
}
