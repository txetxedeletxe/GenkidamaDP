package data;

import interfaces.Container;
import interfaces.ObjectDiscriminable;

public class ComputationResult extends Container<GenkidamaResultData> implements GenkidamaSpecializedData, ObjectDiscriminable{

	private static final long serialVersionUID = 15039996969831098L;
	long computationId ;
	public ComputationResult(GenkidamaResultData content, long computationId) {
		super(content);
		this.computationId = computationId;
	}

	@Override
	public long getObjectId() {
		// TODO Auto-generated method stub
		return computationId;
	}

	

}
