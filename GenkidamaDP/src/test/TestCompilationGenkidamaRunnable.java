package test;

import compilation.CompilationUtils;
import data.GenkidamaBasicData;
import data.UnnamedClassBody;
import data.UnnamedClassBodyFactory;
import source.GenkidamaRunnable;

public class TestCompilationGenkidamaRunnable {

	static UnnamedClassBody  ucb;
	static CompilationUtils  cu;
	public static void main(String[] args) {
		
		cu = new CompilationUtils("temp");
		cu.setDebug(true);
		UnnamedClassBodyFactory ucbf = new UnnamedClassBodyFactory("public void run(){}\n"
				+ "public GenkidamaResultData getResult(){\n"
				+ "return new GenkidamaBasicData(4);}");
		
		ucbf.setProperty("importedlib", new String[] {"interfaces.GenkidamaRunnable",
				"data.GenkidamaBasicData","data.GenkidamaResultData"});
		ucbf.setProperty("implementedinterfaces", new String[] {"GenkidamaRunnable"});
		
		ucb = ucbf.construct();
		Class<?> clas = cu.compileAndGet(ucb.getSpecificClass("Genki0"), "Genki0");
		try {
			GenkidamaRunnable gr = (GenkidamaRunnable) clas.newInstance();
			gr.run();
			GenkidamaBasicData gbd =(GenkidamaBasicData)gr.getResult();
			System.out.println(gbd.get());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
