package device;

import java.net.Socket;
import java.net.UnknownHostException;

import connection.GenkidamaConnection;
import connection.PacketHandler;
import connector.ConnectionBuilder;
import connector.ConnectionHandler;
import connector.GenkidamaClient;
import connector.GenkidamaServer;

public  class DeviceController implements ConnectionHandler, HostDeviceInterface, DeviceUI{

	private ProcessManager processManager;
	private CompilationManager compilationManager;
	private HostManager hostManager;
	
	private GenkidamaServer server;
	private GenkidamaClient client;
	
	private final ConnectionBuilder CONNECTION_FACTORY = new ConnectionBuilder() {
		
		private Socket s;
		
		@Override
		public GenkidamaConnection construct() {
			
			PacketHandler ph = new GenkidamaHostProxy(DeviceController.this);
			
			return new GenkidamaConnection(s, ph);
			
		}

		@Override
		public void setProperty(String propertyName, Object value) {
			
			if (propertyName.equals("socket")) {
				s = (Socket) value;
			}
			
		}

		@Override
		public void reset() {
			
			s = null;
			
		}
	};
	
	public DeviceController() {
		
		processManager = new ProcessManager(4);
		processManager.beginProcessing();
		compilationManager = new CompilationManager();
		hostManager = new HostManager();
		client = new GenkidamaClient(CONNECTION_FACTORY);
		
	}
	
	@Override
	public void handle(GenkidamaConnection t) {
		
		((GenkidamaHostProxy)t.getPacketHandler()).setGenkidamaConnection(t);
		DeviceHostInterface dhi = (DeviceHostInterface)t.getPacketHandler();
		hostManager.addHost(dhi);
	}

	@Override
	public String addSource(OwnedClassBody code) {
		
		return compilationManager.addCode(code);
	}

	@Override
	public void startServer(int port) {
		
		this.server = new GenkidamaServer(port, this, CONNECTION_FACTORY);
		this.server.open();
		
	}

	@Override
	public void stopServer() {
		
		this.server.close();
		
		
	}

	@Override
	public void connectToHostServer(String hostName, int port) throws UnknownHostException {
		
		GenkidamaConnection connection = client.connect(hostName, port);
		GenkidamaHostProxy ph = (GenkidamaHostProxy)connection.getPacketHandler();
		ph.setGenkidamaConnection(connection);
		DeviceHostInterface dhi = (DeviceHostInterface) ph;
		hostManager.addHost(dhi);
	}

	@Override
	public void kickComputation(OwnedComputationSpec computationSpec) {
		
		OwnedFinalComputationSpec csf = compilationManager.getCompiledObject(computationSpec);
		processManager.addComputation(csf);
	}
}
