package network;

import interaction.GenkidamaInteractionType;

public class GenkidamaStaticHeader {
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
	
	
	
}
