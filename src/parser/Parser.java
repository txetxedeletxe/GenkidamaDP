package parser;

import java.util.List;

public interface Parser<T> {
	
	public void handle(List<T> sequence);
	
}
