package test;

import java.net.UnknownHostException;

import connect.GenkidamaClient;
import connect.GenkidamaConnection;
import connect.GenkidamaServer;
import connect.StandardConnectionHandler;
import connect.StandardSocketHandler;
import packet.PrintHandler;
import packet.TextPacket;

public class TestConnection {

	public static void main(String[] args) {
		
		StandardConnectionHandler sch = new StandardConnectionHandler();
		StandardSocketHandler ssh = new StandardSocketHandler(new PrintHandler());
		
		GenkidamaServer gs = new GenkidamaServer(41597, sch, ssh);
		GenkidamaClient gc = new GenkidamaClient(ssh);
		gs.open();
		
		try {
			GenkidamaConnection gcc = gc.connect("localhost", 41597);
			gcc.sendPacket(new TextPacket("Halo"));
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
