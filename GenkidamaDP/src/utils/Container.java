package utils;

public abstract class Container<T> {

	private T content;
	
	public Container(T content) {
		this.content = content;
	}
	
	public T getContent() {
		return content;
	}
}
