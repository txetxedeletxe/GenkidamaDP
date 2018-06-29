package connect;

import java.util.ArrayList;

public class StandardConnectionHandler implements ConnectionHandler{

	private ArrayList<GenkidamaConnection> connectionList;
	
	public StandardConnectionHandler() {
	
		connectionList = new ArrayList<>();
	}
	
	@Override
	public void handle(GenkidamaConnection genkidamaConnection) {
		connectionList.add(genkidamaConnection);
		
	}



	
}
