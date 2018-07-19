package data;

import interfaces.Container;
import interfaces.GenkidamaRunnable;
import interfaces.ObjectDiscriminable;

public class FinalComputationSpec extends Container<GenkidamaRunnable> implements GenkidamaData, ObjectDiscriminable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6920910899270535319L;
	
	private long computationId;
	
	
	public FinalComputationSpec(GenkidamaRunnable className,long computationId) {
		
		super(className);
		this.computationId = computationId;
	}


	@Override
	public long getObjectId() {
		// TODO Auto-generated method stub
		return computationId;
	}
}
