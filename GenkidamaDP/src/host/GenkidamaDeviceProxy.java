package host;

import java.util.Random;

import connection.ConnectionWrapper;
import data.ComputationSpec;
import data.UnnamedClassBody;
import interfaces.Demultiplexor;
import interfaces.Handler;
import packet.ComputationPacket;
import packet.GenkidamaPacket;
import packet.ResultPacket;
import packet.SourcePacket;

public class GenkidamaDeviceProxy extends ConnectionWrapper implements HostDeviceInterface {

	
	private Demultiplexor<GenkidamaPacket> demul;
	private RemoteDeviceView remoteDeviceView;
	
	private final Random random = new Random();
	
	private final Handler<GenkidamaPacket> resultHandler = new Handler<GenkidamaPacket>(){

		@Override
		public void handle(GenkidamaPacket t) {
			
			ResultPacket rs = (ResultPacket) t;
			
			remoteDeviceView.addResult(rs.getContent().getContent(),rs.getContent().getObjectId());
		}
			
	};
	
	public GenkidamaDeviceProxy() {
		demul = new Demultiplexor<>();
		demul.addHandler(ResultPacket.serialVersionUID, resultHandler);
		remoteDeviceView = new RemoteDeviceView();
	}
	
	@Override
	public void handle(GenkidamaPacket t) {
		
		demul.handle(t);
		
	}

	@Override
	public void sendSource(UnnamedClassBody unnamedClassBody, String sourceIdentifier) {
		
		SourcePacket sp = new SourcePacket(unnamedClassBody, sourceIdentifier);
		connection.sendPacket(sp);
		remoteDeviceView.addSource(sourceIdentifier);
		
	}

	@Override
	public long kickComputation(String sourceIdentifier) {
		long computationId;
		
		while (remoteDeviceView.computationIdExists(computationId = random.nextLong()));
		
		ComputationSpec cs = new ComputationSpec(sourceIdentifier, computationId);
		ComputationPacket cp = new ComputationPacket(cs);
		remoteDeviceView.addPendingComputation(computationId);
		connection.sendPacket(cp);
		return computationId;
	}

	@Override
	public Object getResult(long computationIdentifier) {
		
		return remoteDeviceView.getComputationResult(computationIdentifier);
	}

	@Override
	public boolean isResultReady(long computataionIdentifier) {
		
		return (remoteDeviceView.getComputationResult(computataionIdentifier) != null);
	}

}
