package device;

import connect.ConnectionWrapper;
import connect.GenkidamaConnection;
import packet.CodePacket;
import packet.GenkidamaPacket;
import packet.PacketHandler;
import packet.ResultPacket;
import utils.Demultiplexor;
import utils.GenkidamaRunnable;
import utils.Handler;

public class GenkidamaHostProxy extends ConnectionWrapper implements DeviceHostInterface{
 
	
	private HostDeviceInterface deviceInterface;
	private GenkidamaConnection connection;
	
	private Demultiplexor<GenkidamaPacket> handler;
	
	private DeviceView deviceView;
	
	private final Handler<GenkidamaPacket> codePacketHandler = new Handler<GenkidamaPacket>() {
		
		@Override
		public void handle(GenkidamaPacket t) {
			
			String physicalName = deviceInterface.addCode(new CompilableCode(((CodePacket)t).getCode(), (DeviceHostInterface)GenkidamaHostProxy.this));
			deviceView.addCodeIdentifier(((CodePacket)t).getIdentifier(),physicalName);
		}
	};
	
	public GenkidamaHostProxy(HostDeviceInterface hostDeviceInterface) {
		
		this.deviceInterface = hostDeviceInterface;
		handler = new Demultiplexor<GenkidamaPacket>();
		handler.addHandler(CodePacket.serialVersionUID, codePacketHandler);
		this.deviceView = new DeviceView();
		
	}
	
	@Override
	public void handle(GenkidamaPacket t) {
		
		handler.handle(t);
		
	}

	
	public void setHostDeviceInterface(HostDeviceInterface hostDeviceInterface) {
		
		this.deviceInterface = hostDeviceInterface;
		
	}
	

	@Override
	public long getId() {
		
		return connection.hashCode();
	}

	@Override
	public void getComputationResult(GenkidamaRunnable runnable) {
		
		connection.sendPacket(new ResultPacket(runnable.getResult()));
	}
}
