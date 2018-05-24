package device;


import transmitible.order.ComputationKick;
import transmitible.uploadAndStore.GenkidamaConstants;
import transmitible.uploadAndStore.GenkidamaFile;
import transmitible.uploadAndStore.GenkidamaInstruction;
import transmitible.uploadAndStore.GenkidamaLibrary;

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
	
	public void transmitConstants(GenkidamaConstants constants) {
		
		remoteIdentifiers.addIdentifier("Constant",constants.getIdentifier());

		connection.sendPacket(packet);
		
		
	}
	
	public void transmitInstruction(GenkidamaInstruction instruction) {
	
		remoteIdentifiers.addIdentifier("Instruction", instruction.getIdentifier());
		
		GenkidamaPacket packet =  GenkidamaPacketFactory.packet(instruction);
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
