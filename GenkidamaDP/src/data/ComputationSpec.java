package data;

import interfaces.Container;
import interfaces.ObjectDiscriminable;

public class ComputationSpec extends Container<String> implements GenkidamaSpecializedData, ObjectDiscriminable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6920910899270535319L;
	
	private long computationId;
	
	
	public ComputationSpec(String className,long computationId) {
		
		super(className);
		this.computationId = computationId;
	}


	@Override
	public long getObjectId() {
		// TODO Auto-generated method stub
		return computationId;
	}
}
