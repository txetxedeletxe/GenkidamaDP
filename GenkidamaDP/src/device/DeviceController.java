package device;

import java.net.UnknownHostException;

import connect.ConnectionHandler;
import connect.ConnectionSpec;
import connect.GenkidamaClient;
import connect.GenkidamaConnection;
import connect.GenkidamaServer;
import connect.PacketHandlerFactory;
import packet.PacketHandler;

public  class DeviceController implements ConnectionHandler, HostDeviceInterface, DeviceUI{

	private ProcessManager processManager;
	private CompilationManager compilationManager;
	private HostManager hostManager;
	
	private GenkidamaServer server;
	private GenkidamaClient client;
	
	private final PacketHandlerFactory HOST_FACTORY = new PacketHandlerFactory() {
		
		@Override
		public PacketHandler construct() {
		
			return new GenkidamaHostProxy(DeviceController.this);
			
		}
	};
	
	private final ConnectionSpec CONNECTION_SPEC = new ConnectionSpec(HOST_FACTORY);
	
	public DeviceController() {
		
		processManager = new ProcessManager(4);
		compilationManager = new CompilationManager();
		hostManager = new HostManager();
		client = new GenkidamaClient(CONNECTION_SPEC);
		
	}
	
	@Override
	public void handle(GenkidamaConnection t) {
		
		((GenkidamaHostProxy)t.getPacketHandler()).setGenkidamaConnection(t);
		DeviceHostInterface dhi = (DeviceHostInterface)t.getPacketHandler();
		hostManager.addHost(dhi);
	}

	@Override
	public String addCode(CompilableCode code) {
		
		return compilationManager.addCode(code);
	}

	@Override
	public void startServer(int port) {
		
		this.server = new GenkidamaServer(port, this, CONNECTION_SPEC);
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
	public void kickComputation(ComputationSpec computationSpec) {
		
		ComputationSpecFinal csf = compilationManager.getCompiledObject(computationSpec);
		processManager.addComputation(csf);
	}
}
