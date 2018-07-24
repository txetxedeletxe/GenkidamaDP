package data;

import interfaces.Container;
import interfaces.Discriminable;
import interfaces.Identifiable;

public class CompilableSource extends Container<UnnamedClassBody> implements GenkidamaData,Identifiable,Discriminable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8268820776820220187L;
	
	public CompilableSource(UnnamedClassBody ucb,String identifier) {
		
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
