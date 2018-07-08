package device;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import utils.CompilationUtils;
import utils.GenkidamaRunnable;



public class CompilationManager {

	private final String COMPILATION_TEMP_DIR = "temp"+ File.pathSeparator+ "compilation";
	private final File compilationFile;
	
	private Map<String,CompiledClass> compiledClasses;
	
	private final String classBaseName = "Genki";
	private int nextClassId;
	
	public CompilationManager() {
		
		compiledClasses = new HashMap<String,CompiledClass>();
		flushDir();
		nextClassId = 0;
		CompilationUtils.initialize();
		compilationFile= new File(COMPILATION_TEMP_DIR);
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


	public String addCode(CompilableCode code) {
		
		String thisClass = classBaseName + nextClassId;
		String realCode = CompilationUtils.wrapCode(code.getContent(),thisClass,new String[] {"GenkidamaRunnable"},new String[]{"utils.GenkidamaRunnable"});
		nextClassId++;
		GenkidamaRunnable gr;
		Class<?> cclass =  CompilationUtils.compileAndGet(realCode, thisClass, compilationFile.getAbsolutePath());
		CompiledClass compiled = new CompiledClass(cclass, code.getOwner());
		compiledClasses.put(thisClass, compiled );
		
		return thisClass;
	}

	public ComputationSpecFinal getCompiledObject(ComputationSpec spec) {
		
		CompiledClass cc = compiledClasses.get(spec.getContent());
		
		if (cc.getOwner() != spec.getOwner())
			throw new RuntimeException("nope");
		
		Class<?> c = cc.getContent();
		Object o = null;
		try {
			o = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GenkidamaRunnable gr = (GenkidamaRunnable)o;
		ComputationSpecFinal csf = new ComputationSpecFinal(gr, spec.getOwner());
		
		return csf;
	}

	
}
