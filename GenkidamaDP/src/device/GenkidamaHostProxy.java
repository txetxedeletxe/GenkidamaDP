package device;

import connection.ConnectionWrapper;
import connection.GenkidamaPacket;
import connection.PacketType;
import data.ComputationResult;
import data.ComputationSpec;
import data.UnnamedClassBody;
import interfaces.Demultiplexor;
import interfaces.Handler;

public class GenkidamaHostProxy extends ConnectionWrapper implements DeviceHostInterface{
 
	private HostDeviceInterface deviceInterface;
	private Demultiplexor<GenkidamaPacket> handler;
	private LocalDeviceView deviceView;

	private final Handler<GenkidamaPacket> codePacketHandler = new Handler<GenkidamaPacket>() {
		
		@Override
		public void handle(GenkidamaPacket sp) {
			
			String physicalName = deviceInterface.addSource(new DeviceHostOwnedObject<UnnamedClassBody>((DeviceHostInterface)GenkidamaHostProxy.this,sp.getCode()));
			deviceView.addCodeIdentifier(s,physicalName);
		}
	};
	
	
	private final Handler<GenkidamaPacket> computationPacketHandler = new Handler<GenkidamaPacket>() {

		@Override
		public void handle(GenkidamaPacket t) {
			
			ComputationSpec cs1 = (ComputationSpec)t.get();
			ComputationSpec cs = new ComputationSpec(deviceView.getPhysicalCodeName(cs1.get()), cs1.getId());
			DeviceHostOwnedObject<ComputationSpec> owcs = new DeviceHostOwnedObject<ComputationSpec>(GenkidamaHostProxy.this, cs);
			deviceInterface.kickComputation(owcs);
			
		}
		
	};
	public GenkidamaHostProxy(HostDeviceInterface hostDeviceInterface) {
		
		this.deviceInterface = hostDeviceInterface;
		handler = new Demultiplexor<GenkidamaPacket>();
		handler.addHandler(PacketType.ADD_SOURCE.ordinal(), codePacketHandler);
		handler.addHandler(PacketType.START_COMPUTATION.ordinal(), computationPacketHandler);
		this.deviceView = new LocalDeviceView();
		
	}
	
	@Override
	public void handle(GenkidamaPacket t) {
		
		handler.handle(t);
		
	}

	
	public void setHostDeviceInterface(HostDeviceInterface hostDeviceInterface) {
		
		this.deviceInterface = hostDeviceInterface;
		
	}
	

	@Override
	public void getComputationResult(ComputationResult cr) {
		
		GenkidamaPacket gp = new GenkidamaPacket(cr, PacketType.RETRANSMIT_RESULT);
		connection.sendPacket(gp);
	}

	
}
