package network;

import java.nio.ByteBuffer;

import data.GenkidamaContent;
import interaction.GenkidamaInteraction;

public class GenkidamaPacket implements GenkidamaContent{

	private GenkidamaStaticHeader staticHeader;
	private GenkidamaContent innerPacket;
	
	public GenkidamaPacket(GenkidamaInteraction interaction) {
		
		this.staticHeader = new GenkidamaStaticHeader(interaction.getInteractionType(), interaction.getContentSize());
		this.innerPacket = interaction.getContent();
		
	}
	
	@Override
	public byte[] toByte() {
		ByteBuffer bb = ByteBuffer.allocate(this.staticHeader.getContentLength() + GenkidamaStaticHeader.headerLength());
		bb.put(this.staticHeader.toByte());
		bb.put(this.innerPacket.toByte());
		
		return bb.array();
	}

	
}
