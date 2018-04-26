package network;

import interaction.GenkidamaInteraction;

public class GenkidamaPacket implements GenkidamaContent{

	private GenkidamaStaticHeader staticHeader;
	private byte[] innerPacket;
	
	public GenkidamaPacket(GenkidamaInteraction interaction) {
		
		this.staticHeader = new GenkidamaStaticHeader(interaction.getInteractionType(), interaction.getContentSize());
		this.innerPacket = interaction.getContent().toByte();
		
	}

	
}
