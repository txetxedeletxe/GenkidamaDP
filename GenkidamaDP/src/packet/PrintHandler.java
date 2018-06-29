package packet;

public  class PrintHandler implements PacketHandler{

	@Override
	public void handle(GenkidamaPacket packet) {
		
		System.out.println(packet);
		
	}

	
}
