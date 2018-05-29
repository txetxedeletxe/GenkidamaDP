package data;

public class GenkidamaObject implements GenkidamaData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5527250972507316872L;
	private Object obj;
	
	public GenkidamaObject(Object obj) {
		this.obj = obj;
	}
	
	public Object getObject() {
		return obj;
	}
	
}
