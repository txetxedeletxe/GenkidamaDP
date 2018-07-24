package device;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import compilation.CompilationUtils;
import data.ComputationSpec;
import data.FinalComputationSpec;
import data.UnnamedClassBody;
import source.GenkidamaRunnable;

public class CompilationManager {

	private final String COMPILATION_TEMP_DIR = "temp"+ File.pathSeparator+ "compilation";
	private final File compilationFile;
	
	private Map<String,DeviceHostOwnedObject<Class<?>>> compiledClasses;
	
	private final String classBaseName = "Genki";
	private int nextClassId;
	
	private CompilationUtils compilationUtils;
	
	public CompilationManager() {
		
		compiledClasses = new HashMap<String,DeviceHostOwnedObject<Class<?>>>();
		flushDir();
		nextClassId = 0;
		compilationFile= new File(COMPILATION_TEMP_DIR);
		compilationUtils = new CompilationUtils(compilationFile.getAbsolutePath());
	}
	
	
	
	private void flushDir() {
		
		File f = new File(COMPILATION_TEMP_DIR);
		if (!f.exists()) {
			f.mkdirs();
		}
		else {
			File[] files = f.listFiles();
			for (File ff : files) {
				ff.delete();
			}
		}
		
	}


	public String addCode(DeviceHostOwnedObject<UnnamedClassBody> code) {
		
		String thisClass = classBaseName + nextClassId++;
		String realCode = code.get().getSpecificClass(thisClass);
		Class<?> cclass =  compilationUtils.compileAndGet(realCode,thisClass);
		DeviceHostOwnedObject<Class<?>> compiled = new DeviceHostOwnedObject<Class<?>>(code.getOwner(),cclass);
		compiledClasses.put(thisClass, compiled);
		return thisClass;
	}

	public DeviceHostOwnedObject<FinalComputationSpec> getCompiledObject(DeviceHostOwnedObject<ComputationSpec> spec) {
		
		DeviceHostOwnedObject<Class<?>> cc = compiledClasses.get(spec.get().get());
		
		if (cc.getOwner() != spec.getOwner())
			throw new RuntimeException("nope");
		
		Class<?> c = cc.get();
		Object o = null;
		try {
			o = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenkidamaRunnable gr = (GenkidamaRunnable)o;
		FinalComputationSpec fcs = new FinalComputationSpec(gr, spec.get().getId());
		DeviceHostOwnedObject<FinalComputationSpec> csf = new DeviceHostOwnedObject<FinalComputationSpec>( spec.getOwner(),fcs);
		
		return csf;
	}

	
}
