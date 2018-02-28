package farmshop.animal;

import farmshop.Order;

/**
 * represents an animal that can produce wool
 */
public interface WoolAnimal {

	public int getWool();
	
	public void setWool(int wool);
	
	/**
	 * we make this method default to avoid code duplication
	 */
	public default void orderWool(Order order) {
		int quantity = order.getWool();
		int woolConsumed = 0;
		int woolAvailable = getWool();
		
		if(quantity > woolAvailable) {
			setWool(0);
			woolConsumed = woolAvailable;
		} else {
			setWool(woolAvailable - quantity);
			woolConsumed = quantity;
		}
		
		order.setWool(quantity - woolConsumed);
	}
}
