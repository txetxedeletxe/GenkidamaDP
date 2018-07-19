package compilation;

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

	
	private JavaCompiler jc;
	private URLClassLoader urlClassLoader;
	
	private List<String> options;
	private boolean debug;
	
	
	public CompilationUtils(String compilationPath) {
		File f = new File(compilationPath);
		compilationPath = f.getAbsolutePath();
		
		jc = ToolProvider.getSystemJavaCompiler();
		
		
		options = new ArrayList<String>();
		options.add("-d");
		options.add(compilationPath);
		options.add("-classpath");
		
		urlClassLoader = (URLClassLoader)Thread.currentThread().getContextClassLoader();
		
		StringBuilder sb = new StringBuilder();
		for (URL url : urlClassLoader.getURLs()) {
		    sb.append(url.getFile()).append(File.pathSeparator);
		}
		sb.append( compilationPath);
		options.add(sb.toString());
	
		try {
			urlClassLoader = URLClassLoader.newInstance(new URL[] { f.toURI().toURL() });
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public Class<?> compileAndGet(String classAsString, String className ) {
		
		
		StringWriter sw = new StringWriter();
		
		CompilableJavaObject cs = new CompilableJavaObject(className, classAsString);
		List<CompilableJavaObject> csl = Arrays.asList(cs);
		
		
		CompilationTask ct = jc.getTask(sw, null, null, options, null, csl);
		ct.call();
		
		if (debug)
			System.out.println(sw.toString());
		Class<?> cls = null;
		try {
						
			cls = Class.forName(className, true, urlClassLoader);
		} catch (ClassNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Should print "hello".
		
		return cls;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
