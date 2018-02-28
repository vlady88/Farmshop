package farmshop.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import farmshop.FarmShop;
import farmshop.Order;
import farmshop.animal.Lamb;
import farmshop.animal.MilkAnimal;
import farmshop.animal.Sheep;
import farmshop.animal.WoolAnimal;

/**
 * unit tests
 */
public class FarmShopTest {

	/**
	 * test handling a milk order by an animal
	 */
	@Test
	public void testMilkAnimalOrder() {
		MilkAnimal animal = new Sheep();
		animal.setMilk(50);
		Order order = new Order("", 40, 0);
		animal.orderMilk(order);
		assertTrue(animal.getMilk() == 10);
	}

	/**
	 * test handling a wool order by an animal
	 */
	@Test
	public void testWoolAnimalOrder() {
		WoolAnimal animal = new Lamb();
		animal.setWool(50);
		Order order = new Order("", 0, 40);
		animal.orderWool(order);
		assertTrue(animal.getWool() == 10);
	}
	
	/**
	 * test the initialization of the flock
	 */
	@Test
	public void testFarmshopInit() {
		FarmShop farmShop = new FarmShop();
		assertTrue(farmShop.init().equals("Flock successfully initialized"));
	}

	/**
	 * test querying the flock
	 */
	@Test
	public void testFarmshopFlock() {
		FarmShop farmShop = new FarmShop();
		farmShop.init();
		ObjectNode flock = farmShop.flock();
		assertTrue(flock.findPath("flock").size() == 5);
	}
	
	/**
	 * test querying the stock
	 */
	@Test
	public void testFarmshopStock() {
		FarmShop farmShop = new FarmShop();
		farmShop.init();
		ObjectNode stock = farmShop.stock();
		assertTrue(stock.findPath("wool").asInt() == 67 && 
				stock.findPath("milk").asInt() == 80);
	}
	
	/**
	 * test an order that can be fulfilled
	 */
	@Test
	public void testFarmshopOrderSuccess() {
		FarmShop farmShop = new FarmShop();
		farmShop.init();
		
		ObjectNode orderNode = JsonNodeFactory.instance.objectNode();
		orderNode.put("customer", "Milk and Wool Trading Ltd");
		ObjectNode quantityNode = orderNode.putObject("order");
		quantityNode.put("milk", 10);
		quantityNode.put("wool", 3);
		
		String result = farmShop.order(orderNode);
		
		assertTrue(result.equals("Order completed successfully"));
	}

	/**
	 * test an order that cannot be fulfilled
	 */
	@Test
	public void testFarmshopOrderFail() {
		FarmShop farmShop = new FarmShop();
		farmShop.init();
		
		ObjectNode orderNode = JsonNodeFactory.instance.objectNode();
		orderNode.put("customer", "Milk and Wool Trading Ltd");
		ObjectNode quantityNode = orderNode.putObject("order");
		quantityNode.put("milk", 80);
		quantityNode.put("wool", 80);
		
		String result = farmShop.order(orderNode);
		
		assertTrue(result.equals("Order couldn't be completed, insufficient stock"));
	}
}
