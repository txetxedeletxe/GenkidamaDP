package test;

import java.io.File;

import utils.CompilationUtils;

public class TestCompilation {

	public static void main(String[] args) {
		File f = new File("temp");
		if (!f.exists())
			f.mkdirs();
		CompilationUtils.initialize();
		Class<?> c = CompilationUtils.compileAndGet("public class R implements Runnable{ public void run(){ System.out.println(\"h\");}}"
				, "R", f.getAbsolutePath());
		try {
			Object o = c.newInstance();
			Runnable r = (Runnable)o;
			r.run();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
