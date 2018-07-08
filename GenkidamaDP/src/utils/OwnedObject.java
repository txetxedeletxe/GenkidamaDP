package utils;

public abstract class OwnedObject<T1,T2> extends Container<T2>{

	private T1 owner;
	
	public OwnedObject(T1 owner,T2 content) {
		super(content);
		this.owner = owner;
	}
	
	public T1 getOwner() {
		return owner;
	}
}
