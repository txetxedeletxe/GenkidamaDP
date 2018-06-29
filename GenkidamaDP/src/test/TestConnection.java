package test;

import java.net.UnknownHostException;

import connect.ConnectionSpec;
import connect.GenkidamaClient;
import connect.GenkidamaConnection;
import connect.GenkidamaServer;
import connect.StandardConnectionHandler;
import packet.PrintHandler;
import packet.TextPacket;

public class TestConnection {

	public static void main(String[] args) {
		
		StandardConnectionHandler sch = new StandardConnectionHandler();
		ConnectionSpec cs = new ConnectionSpec(new PrintHandler());
		
		GenkidamaServer gs = new GenkidamaServer(41597, sch, cs);
		GenkidamaClient gc = new GenkidamaClient(cs);
		gs.open();
		
		try {
			GenkidamaConnection gcc = gc.connect("localhost", 41597);
			gcc.sendPacket(new TextPacket("Halo"));
			gs.close();
			gcc.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
