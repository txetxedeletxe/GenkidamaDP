package utils;

import java.io.File;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.ToolProvider;

public class CompilationUtils {

	
	private static JavaCompiler jc;
	private static URLClassLoader urlClassLoader;
	private CompilationUtils(){}
	
	
	public static void initialize() {
		jc = ToolProvider.getSystemJavaCompiler();
		urlClassLoader = (URLClassLoader)Thread.currentThread().getContextClassLoader();
	}
	
	
	public static String wrapCode(String code, String className, String[] implementedInterfaces, String[] importedLibraries) {
		
		StringBuilder sb = new StringBuilder();
		
		for (String s: importedLibraries) {
			sb.append("import ");
			sb.append(s);
			sb.append("\n");
		}
		sb.append("public Class " + className + "implements ");
		for (String s : implementedInterfaces) {
			sb.append(s);
			sb.append(" ");
		}
		sb.append("{\n");
		sb.append(code);
		sb.append("}\n");
		return sb.toString();
	}
	
	public static Class<?> compileAndGet(String realCode, String className ,String compilationPath) {
		
		StringWriter sw = new StringWriter();
		List<String> options = new ArrayList<String>();
		options.add("-d");
		options.add(compilationPath);
		options.add("-classpath");
		
		
		StringBuilder sb = new StringBuilder();
		for (URL url : urlClassLoader.getURLs()) {
		    sb.append(url.getFile()).append(File.pathSeparator);
		}
		sb.append( compilationPath);
		options.add(sb.toString());
		
		CompilableSource cs = new CompilableSource(className, realCode);
		List<CompilableSource> csl = Arrays.asList(cs);
		
		
		CompilationTask ct = jc.getTask(sw, null, null, options, null, csl);
		ct.call();
		
		File cp = new File(compilationPath);
		Class<?> cls = null;
		try {
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { cp.toURI().toURL() });			
			cls = Class.forName(className, true, classLoader);
		} catch (ClassNotFoundException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Should print "hello".
		
		return cls;
	}

}
