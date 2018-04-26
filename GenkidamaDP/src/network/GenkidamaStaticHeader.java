package network;

import java.nio.ByteBuffer;

import data.GenkidamaContent;
import interaction.GenkidamaInteractionType;

public class GenkidamaStaticHeader implements GenkidamaContent{
	private static final int HEADER_LENGTH_BYTES = 5;
	private GenkidamaInteractionType type;
	private int contentLength;
	
	public GenkidamaStaticHeader(interaction.GenkidamaInteractionType interactionType, int contentSize) {
		this.type = interactionType;
		this.contentLength = contentSize;
	}

	public GenkidamaInteractionType getType() {
		return type;
	}

	public int getContentLength() {
		return contentLength;
	}

	@Override
	public byte[] toByte() {
		ByteBuffer bb = ByteBuffer.allocate(HEADER_LENGTH_BYTES);
		bb.put((byte)type.ordinal());
		bb.putInt(contentLength);
		return bb.array();
				
	}

	public static int headerLength() {
		
		return HEADER_LENGTH_BYTES;
	}
	
	
	
}
