package data;

import interfaces.Container;
import interfaces.Discriminable;
import source.GenkidamaRunnable;

public class FinalComputationSpec extends Container<GenkidamaRunnable> implements GenkidamaData, Discriminable{

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
	public long getId() {
		// TODO Auto-generated method stub
		return computationId;
	}
}
