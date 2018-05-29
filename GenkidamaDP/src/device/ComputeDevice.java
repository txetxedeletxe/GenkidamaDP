package device;


import data.GenkidamaFile;
import data.GenkidamaInstruction;
import data.GenkidamaLibrary;
import data.primitive.PrimitiveData;
import sendable.order.ComputationKick;

public class ComputeDevice {

	private ComputeDeviceConnection connection;
	private RemoteIdentifiers remoteIdentifiers;
	
	
	public ComputeDevice() {
		
		String[] defaultIdentifierCategories = new String[] {
			"Constant",
			"Instruction",
			"File",
			"Libary"
		};
		remoteIdentifiers = new RemoteIdentifiers(defaultIdentifierCategories);
	}
	
	public void transmitConstants(PrimitiveData constants) {
		
		remoteIdentifiers.addIdentifier("Constant",constants.getIdentifier());
		
		
		
	}
	
	public void transmitInstruction(GenkidamaInstruction instruction) {
	
		remoteIdentifiers.addIdentifier("Instruction", instruction.getIdentifier());
		connection.sendPacket(packet);
		
	}
	
	
	
	public void transmitFile(GenkidamaFile file) {
		
		remoteIdentifiers.addIdentifier("File", file.getIdentifier());
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(file);
		connection.sendPacket(packet);

	}
	
	public void transmitLibrary(GenkidamaLibrary lib) {
		
		remoteIdentifiers.addIdentifier("Library", lib.getIdentifier());
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(lib);
		connection.sendPacket(packet);
		
		
	}
	
	
	public void kickComputation(GenkidamaComputationSpecAbstract genkidamaComputationSpec) {
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(genkidamaComputationSpec);
		connection.sendPacket(packet);
	}
	
}
