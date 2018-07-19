package test;

import java.net.Socket;
import java.net.UnknownHostException;

import connection.GenkidamaConnection;
import connector.ConnectionBuilder;
import connector.GenkidamaClient;
import data.GenkidamaBasicData;
import data.UnnamedClassBody;
import data.UnnamedClassBodyFactory;
import device.DeviceController;
import host.GenkidamaDeviceProxy;

public class PrototypeTest {

	private static final ConnectionBuilder connectionFactory= new ConnectionBuilder() {
		
		private Socket sc;
		@Override
		public GenkidamaConnection construct() {
			
			
			return new GenkidamaConnection(sc, new GenkidamaDeviceProxy());
		}
		
		@Override
		public void setProperty(String propertyName, Object value) {
			
			sc = (Socket)value;
			
		}
		
		@Override
		public void reset() {
			// TODO Auto-generated method stub
			
		}
	};
	private static UnnamedClassBody unnamedClassBody;

	public static void main(String[] args) {
		
		UnnamedClassBodyFactory ucbf = new UnnamedClassBodyFactory(
				"public void run(){}\n"
				+ "public GenkidamaResultData getResult(){"
				+ "return new GenkidamaBasicData(4);"
				+ "}"); 
		
		ucbf.setProperty("implementedinterfaces", new String[] {"GenkidamaRunnable"});
		ucbf.setProperty("importedlib", new String[] {"interfaces.GenkidamaRunnable", "data.GenkidamaResultData"
				,"data.GenkidamaBasicData"});
		unnamedClassBody = ucbf.construct();
		DeviceController dc = new DeviceController();
		dc.startServer(4889);
	
		GenkidamaClient gc = new GenkidamaClient(connectionFactory);
		GenkidamaConnection gcc = null;
		try {
			gcc = gc.connect("localhost",4889);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GenkidamaDeviceProxy gdp = ((GenkidamaDeviceProxy)gcc.getPacketHandler());
		gdp.setGenkidamaConnection(gcc);
		
		gdp.sendSource(unnamedClassBody, "Test1");
		long compId = gdp.kickComputation("Test1");
		while (!gdp.isResultReady(compId));
		GenkidamaBasicData fact = (GenkidamaBasicData)gdp.getResult(compId);
		System.out.println(fact.getContent());
		
		
	}
}
