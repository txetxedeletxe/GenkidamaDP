package packet;

public interface Demultiplexor<T> {

	public void handle(T t);
}
