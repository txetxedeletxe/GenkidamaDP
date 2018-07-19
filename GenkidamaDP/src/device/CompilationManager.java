package device;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import compilation.CompilationUtils;
import data.FinalComputationSpec;
import interfaces.GenkidamaRunnable;

public class CompilationManager {

	private final String COMPILATION_TEMP_DIR = "temp"+ File.pathSeparator+ "compilation";
	private final File compilationFile;
	
	private Map<String,OwnedClass> compiledClasses;
	
	private final String classBaseName = "Genki";
	private int nextClassId;
	
	private CompilationUtils compilationUtils;
	
	public CompilationManager() {
		
		compiledClasses = new HashMap<String,OwnedClass>();
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


	public String addCode(OwnedClassBody code) {
		
		String thisClass = classBaseName + nextClassId++;
		String realCode = code.getContent().getSpecificClass(thisClass);
		Class<?> cclass =  compilationUtils.compileAndGet(realCode,thisClass);
		OwnedClass compiled = new OwnedClass(cclass, code.getOwner());
		compiledClasses.put(thisClass, compiled);
		return thisClass;
	}

	public OwnedFinalComputationSpec getCompiledObject(OwnedComputationSpec spec) {
		
		OwnedClass cc = compiledClasses.get(spec.getContent().getContent());
		
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
		FinalComputationSpec fcs = new FinalComputationSpec(gr, spec.getContent().getObjectId());
		OwnedFinalComputationSpec csf = new OwnedFinalComputationSpec(fcs, spec.getOwner());
		
		return csf;
	}

	
}
