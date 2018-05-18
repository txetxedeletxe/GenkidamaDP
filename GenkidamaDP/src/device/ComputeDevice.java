package device;

import data.GenkidamaComputationSpec;
import data.GenkidamaConstants;
import data.GenkidamaFile;
import data.GenkidamaInstruction;
import data.GenkidamaLibrary;
import net.GenkidamaComputationSpecAbstract;
import net.GenkidamaConstantsAbstract;
import net.GenkidamaFileAbstract;
import net.GenkidamaInstructionAbstract;
import net.GenkidamaLibraryAbstract;
import net.GenkidamaPacket;
import net.GenkidamaPacketFactory;

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
	
	public void transmitConstants(GenkidamaConstantsAbstract constants) {
		
		remoteIdentifiers.addIdentifier("Constant",constants.getIdentifier());
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(constants);
		connection.sendPacket(packet);
		
		
	}
	
	public void transmitInstruction(GenkidamaInstructionAbstract instruction) {
	
		remoteIdentifiers.addIdentifier("Instruction", instruction.getIdentifier());
		
		GenkidamaPacket packet =  GenkidamaPacketFactory.packet(instruction);
		connection.sendPacket(packet);
		
	}
	
	
	
	public void transmitFile(GenkidamaFileAbstract file) {
		
		remoteIdentifiers.addIdentifier("File", file.getIdentifier());
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(file);
		connection.sendPacket(packet);

	}
	
	public void transmitLibrary(GenkidamaLibraryAbstract lib) {
		
		remoteIdentifiers.addIdentifier("Library", lib.getIdentifier());
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(lib);
		connection.sendPacket(packet);
		
		
	}
	
	
	public void kickComputation(GenkidamaComputationSpecAbstract genkidamaComputationSpec) {
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packet(genkidamaComputationSpec);
		connection.sendPacket(packet);
	}
	
}
