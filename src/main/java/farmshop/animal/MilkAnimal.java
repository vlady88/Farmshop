package farmshop.animal;

import farmshop.Order;

/**
 * represents an animal that can produce milk
 */
public interface MilkAnimal {

	public int getMilk();
	
	public void setMilk(int milk);
	
	/**
	 * we make this method default to avoid code duplication
	 */
	public default void orderMilk(Order order) {
		int quantity = order.getMilk();
		int milkConsumed = 0;
		int milkAvailable = getMilk();
		
		if(quantity > milkAvailable) {
			milkConsumed = milkAvailable;
			setMilk(0);
		} else {
			milkConsumed = quantity;
			setMilk(milkAvailable - quantity);
		}
		
		order.setMilk(quantity - milkConsumed);
	}
}
