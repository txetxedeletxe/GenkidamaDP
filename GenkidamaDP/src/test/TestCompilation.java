package test;

import java.io.File;

import compilation.CompilationUtils;

public class TestCompilation {

	public static void main(String[] args) {
		
		String classAsString = "public class R implements Runnable{ public void run(){"
				+ "for (int i = 0 ; i < 1000 ; i++)"
				+ "System.out.println(i);"
				+ "}"
				+ "}";
		String classAsString2 = "public class Q implements Runnable{ public void run(){ System.out.println(\"h\");}}";

		File f = new File("temp");
		if (!f.exists())
			f.mkdirs();
		CompilationUtils cu = new CompilationUtils(f.getAbsolutePath());
		Class<?> c = cu.compileAndGet(classAsString, "R");
		Class<?> c2 = cu.compileAndGet(classAsString, "R");
		try {
			Object o = c.newInstance();
			Runnable r = (Runnable)o;
			r.run();
			o = c2.newInstance();
			r = (Runnable)o;
			r.run();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
