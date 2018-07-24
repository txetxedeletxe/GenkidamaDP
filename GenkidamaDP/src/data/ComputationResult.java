package data;

import interfaces.Container;
import interfaces.Discriminable;

public class ComputationResult extends Container<GenkidamaResultData> implements GenkidamaSpecializedData, Discriminable{

	private static final long serialVersionUID = 15039996969831098L;
	long computationId ;
	public ComputationResult(GenkidamaResultData content, long computationId) {
		super(content);
		this.computationId = computationId;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return computationId;
	}

	

}
