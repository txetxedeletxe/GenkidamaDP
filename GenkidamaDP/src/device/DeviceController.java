package device;

import java.net.UnknownHostException;

import connection.GenkidamaConnection;
import connection.GenkidamaPacket;
import connector.ConnectionFactory;
import connector.GenkidamaClient;
import connector.GenkidamaServer;
import data.ComputationSpec;
import data.FinalComputationSpec;
import data.UnnamedClassBody;
import interfaces.Factory;
import interfaces.Handler;

public  class DeviceController implements Handler<GenkidamaConnection>, HostDeviceInterface, DeviceUI{

	private ProcessManager processManager;
	private CompilationManager compilationManager;
	private HostManager hostManager;
	
	private GenkidamaServer server;
	private GenkidamaClient client;
	
	private final Factory<Handler<GenkidamaPacket>> PACKET_HANDLER_FACTORY = new Factory<Handler<GenkidamaPacket>>() {

		@Override
		public Handler<GenkidamaPacket> construct() {
			return new GenkidamaHostProxy(DeviceController.this);
		}
	
	
	};
	
	private final ConnectionFactory CONNECTION_FACTORY = new ConnectionFactory(PACKET_HANDLER_FACTORY) ;
	
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
	public String addSource(DeviceHostOwnedObject<UnnamedClassBody> code) {
		
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
	public void kickComputation(DeviceHostOwnedObject<ComputationSpec> computationSpec) {
		
		DeviceHostOwnedObject<FinalComputationSpec> csf = compilationManager.getCompiledObject(computationSpec);
		processManager.addComputation(csf);
	}
}
