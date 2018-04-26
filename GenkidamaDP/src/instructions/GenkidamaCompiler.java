package instructions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaFileObject;
import javax.tools.JavaCompiler.CompilationTask;

import exception.InvalidGenkidamaCodeException;


public class GenkidamaCompiler {

	public static void checkCompilation(String className, String classCode) throws InvalidGenkidamaCodeException {

		
	}

public static Class<?> stringToClass(String className, String code) throws FileAlreadyExistsException{
		
		int compilationSocket =  getCompilationSocket(className);
		String realCompilationPath = compilationPath +"/" +socketIdentifier + compilationSocket;
		JavaSourceFromString jsfs = new JavaSourceFromString(className, code);
		Iterable<? extends JavaFileObject> fileObjects = Arrays.asList( jsfs);
		
		List<String> options = new ArrayList<String>();
		options.add("-d");
		options.add(realCompilationPath);
		options.add( "-classpath");
		
		URLClassLoader urlClassLoader = (URLClassLoader)Thread.currentThread().getContextClassLoader();
		
		StringBuilder sb = new StringBuilder();
		for (URL url : urlClassLoader.getURLs()) {
		    sb.append(url.getFile()).append(File.pathSeparator);
		}
		sb.append( realCompilationPath);
		options.add(sb.toString());
			
		CompilationTask ct =jc.getTask(null, null, null, options, null, fileObjects);
		ct.call();
		
		File compiledClass = new File(realCompilationPath);
		
		try {
			ClassLoader loader = new URLClassLoader(new URL[] {compiledClass.toURL()});
			Class<?> c = loader.loadClass(className);
			((URLClassLoader)loader).close();
			return c;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}
	
}
