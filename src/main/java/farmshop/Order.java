package farmshop;

/**
 * represents an order placed by a customer
 */
public class Order {

	private String customer;
	private int milk;
	private int wool;

	public Order(String customer, int milk, int wool) {
		this.customer = customer;
		this.milk = milk;
		this.wool = wool;
	}

	public int getMilk() {
		return milk;
	}
	
	public void setMilk(int milk) {
		this.milk = milk;
	}
	
	public int getWool() {
		return wool;
	}
	
	public void setWool(int wool) {
		this.wool = wool;
	}
	
	boolean isCompleted() {
		return milk == 0 && wool == 0;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
