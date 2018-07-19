package data;

import java.io.Serializable;

import interfaces.Container;

public class GenkidamaBasicData extends Container<Serializable> implements GenkidamaResultData{

	private static final long serialVersionUID = 4482854223640440267L;

	public GenkidamaBasicData(Serializable content) {
		super(content);
		
	}

}
