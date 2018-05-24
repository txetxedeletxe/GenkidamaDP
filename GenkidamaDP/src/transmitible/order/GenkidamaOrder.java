package transmitible.order;

import transmitible.EventType;
import transmitible.GenkidamaTransmitable;

public abstract class GenkidamaOrder implements GenkidamaTransmitable{

	@Override
	public EventType getGenkidamaEventType() {
		return EventType.ORDER;
	}
	
	public abstract OrderType getOrderType();
}
