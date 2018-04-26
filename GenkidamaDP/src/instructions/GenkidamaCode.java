package instructions;

import data.GenkidamaContent;
import exception.InvalidGenkidamaCodeException;

public class GenkidamaCode implements GenkidamaContent{

	private String classCode;
	private String className;
	
	public GenkidamaCode(String className, String classCode) throws InvalidGenkidamaCodeException {
		this.classCode = classCode;
		this.className = className;
		
		GenkidamaCompiler.checkCompilation(className,classCode);
	
	}
	
	@Override
	public byte[] toByte() {
		
		return classCode.getBytes();
	}
}
