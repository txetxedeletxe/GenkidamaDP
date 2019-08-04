package command;

public class DynamicType {

	private byte typeId;
	private Object value;
	
	public DynamicType(byte typeId, Object value){
		this.typeId = typeId;
		this.value = value;
	}
	
	public byte getTypeId() {
		return typeId;
	}
	
	public Object getValue() {
		return value;
	}
	
}
