package device;

import connection.ConnectionWrapper;
import data.ComputationResult;
import data.ComputationSpec;
import interfaces.Demultiplexor;
import interfaces.Handler;
import packet.ComputationPacket;
import packet.GenkidamaPacket;
import packet.ResultPacket;
import packet.SourcePacket;

public class GenkidamaHostProxy extends ConnectionWrapper implements DeviceHostInterface{
 
	
	private HostDeviceInterface deviceInterface;
	
	private Demultiplexor<GenkidamaPacket> handler;
	
	private LocalDeviceView deviceView;
	
	private final Handler<GenkidamaPacket> codePacketHandler = new Handler<GenkidamaPacket>() {
		
		@Override
		public void handle(GenkidamaPacket t) {
			SourcePacket sp = (SourcePacket) t;
			String physicalName = deviceInterface.addSource(new OwnedClassBody(sp.getCode(), (DeviceHostInterface)GenkidamaHostProxy.this));
			deviceView.addCodeIdentifier(sp.getIdentifier(),physicalName);
		}
	};
	
	
	private final Handler<GenkidamaPacket> computationPacketHandler = new Handler<GenkidamaPacket>() {

		@Override
		public void handle(GenkidamaPacket t) {
			
			ComputationPacket cp = (ComputationPacket) t;
			ComputationSpec cs = new ComputationSpec(deviceView.getPhysicalCodeName(cp.getContent().getContent()), cp.getContent().getObjectId());
			OwnedComputationSpec owcs = new OwnedComputationSpec(GenkidamaHostProxy.this, cs);
			deviceInterface.kickComputation(owcs);
			
		}
		
	};
	public GenkidamaHostProxy(HostDeviceInterface hostDeviceInterface) {
		
		this.deviceInterface = hostDeviceInterface;
		handler = new Demultiplexor<GenkidamaPacket>();
		handler.addHandler(SourcePacket.serialVersionUID, codePacketHandler);
		handler.addHandler(ComputationPacket.serialVersionUID, computationPacketHandler);
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
	public long getClassId() {
		
		return connection.hashCode();
	} 

	@Override
	public void getComputationResult(ComputationResult cr) {
		ResultPacket rp = new ResultPacket(cr);
		connection.sendPacket(rp);
	}
}
