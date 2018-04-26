package interaction;

import data.GenkidamaContent;

public interface GenkidamaInteraction {

	public GenkidamaInteractionType getInteractionType();

	public int getContentSize();

	public GenkidamaContent getContent();

}
