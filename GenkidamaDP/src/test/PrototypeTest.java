package test;

import java.net.UnknownHostException;

import connection.GenkidamaConnection;
import connection.GenkidamaPacket;
import connector.ConnectionFactory;
import connector.GenkidamaClient;
import data.GenkidamaBasicData;
import data.UnnamedClassBody;
import device.DeviceController;
import host.GenkidamaDeviceProxy;
import interfaces.Factory;
import interfaces.Handler;

public class PrototypeTest {

	private static final ConnectionFactory connectionFactory= new ConnectionFactory(new Factory<Handler<GenkidamaPacket>>() {
		
		@Override
		public Handler<GenkidamaPacket> construct() {
			// TODO Auto-generated method stub
			return new GenkidamaDeviceProxy();
		}
	});
	
	private static final String sourceCode = "public void run(){}\n"
			+ "public GenkidamaResultData getResult(){"
			+ "return new GenkidamaBasicData(4);"
			+ "}";
	private static final String sourceCode2 = "public void run(){}\n"
			+ "public GenkidamaResultData getResult(){"
			+ "return new GenkidamaBasicData(5);"
			+ "}";
	private static UnnamedClassBody unnamedClassBody;

	public static void main(String[] args) {
		
		unnamedClassBody = new UnnamedClassBody(new String[] {"source.GenkidamaRunnable","data.GenkidamaResultData","data.GenkidamaBasicData"},
				new String[] {"GenkidamaRunnable"}, null, sourceCode, true);
		
		UnnamedClassBody unnamedClassBody2 = new UnnamedClassBody(new String[] {"source.GenkidamaRunnable","data.GenkidamaResultData","data.GenkidamaBasicData"},
				new String[] {"GenkidamaRunnable"}, null, sourceCode2, true);
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
		gdp.sendSource(unnamedClassBody2, "Test2");
		long compId = gdp.kickComputation("Test1");
		while (!gdp.isResultReady(compId));
		GenkidamaBasicData fact = (GenkidamaBasicData)gdp.getResult(compId);
		System.out.println(fact.get());
		compId = gdp.kickComputation("Test2");
		while (!gdp.isResultReady(compId));
		 fact = (GenkidamaBasicData)gdp.getResult(compId);
		System.out.println(fact.get());
		
		
	}
}
