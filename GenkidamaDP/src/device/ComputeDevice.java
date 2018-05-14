package device;

import java.util.Set;

import data.GenkidamaConstants;
import data.GenkidamaFile;
import data.GenkidamaInstruction;
import data.GenkidamaLibrary;
import data.GenkidamaParameter;
import net.GenkidamaPacket;
import net.GenkidamaPacketFactory;

public class ComputeDevice {

	private ComputeDeviceConnection connection;
	private RemoteIdentifiers remoteIdentifiers;
	
	
	public void transmitConstants(GenkidamaConstants constants) {
		
		if (constantIdentifiers.contains(constants.getIdentifier()))
			throw new RuntimeException("Constant identifier already exists in compute device");
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packetConstants(constants);
		connection.sendPacket(packet);
		constantIdentifiers.add(constants.getIdentifier());
		
	}
	
	public void transmitInstruction(GenkidamaInstruction instruction) {
		
		if (instructionIdentifiers.contains(instruction.getIdentifier()))
			throw new RuntimeException("Instruction identifier already exists in compute device");
		
		GenkidamaPacket packet =  GenkidamaPacketFactory.packetInstruction(instruction);
		connection.sendPacket(packet);
		instructionIdentifiers.add(instruction.getIdentifier());
	}
	
	
	
	public void transmitFile(GenkidamaFile file) {
		
		if (fileIdentifiers.contains(file.getIdentifier()))
			throw new RuntimeException("File identifier already exists in compute device");
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packetFile(file);
		connection.sendPacket(packet);
		fileIdentifiers.add(file.getIdentifier());
	}
	
	public void transmitLibrary(GenkidamaLibrary lib) {
		
		if (libraryIdentifiers.contains(lib.getIdentifier()))
			throw new RuntimeException("Library identifier already exists in compute device");
		
		GenkidamaPacket packet = GenkidamaPacketFactory.packetLibrary(lib);
		connection.sendPacket(packet);
		libraryIdentifiers.add(lib.getIdentifier());
		
	}
	
	
	public void kickComputation(GenkidamaInstruction genkidamaInstruction, GenkidamaParameter parameter) {
		
	}
	
	public void 
}
