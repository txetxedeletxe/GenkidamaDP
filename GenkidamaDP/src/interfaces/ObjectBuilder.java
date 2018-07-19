package interfaces;

public interface ObjectBuilder<T> extends Factory<T>{

	public void setProperty(String propertyName, Object value);
	public void reset();
	
}
