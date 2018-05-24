package transmitible.order;


public class ComputationKick extends GenkidamaOrder{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883890172187707L;

	public ComputationKick() {
		
	}

	@Override
	public OrderType getOrderType() {
		return OrderType.COMPUTATION_KICK;
	}



}
