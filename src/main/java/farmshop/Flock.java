package farmshop;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import farmshop.animal.Animal;
import farmshop.animal.MilkAnimal;
import farmshop.animal.WoolAnimal;

/**
 * class representing the flock
 * we use @Xml* annotations for easy XML parsing with JAXB
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Flock {

	@XmlElementRef
	private List<Animal> animalList = new ArrayList<Animal>();
	
	/**
	 * we need this to set the initial value of milk for females
	 */
	public void init() {
		for(Animal animal : animalList) {
			animal.init();
		}
	}
	
	/**
	 * @return the available stock of the flock
	 */
	public ObjectNode getStock() {
		int woolTotal = 0;
		int milkTotal = 0;
		
		for(Animal animal : animalList) {
			if(animal instanceof WoolAnimal) {
				woolTotal += ((WoolAnimal)animal).getWool(); 
			}
			if(animal instanceof MilkAnimal) {
				milkTotal += ((MilkAnimal)animal).getMilk(); 
			}
		}
		
		ObjectNode totalJson = JsonNodeFactory.instance.objectNode();
		totalJson.put("milk", milkTotal);
		totalJson.put("wool", woolTotal);
		return totalJson;
	}
	
	/**
	 * handles an order from a customer
	 * products are obtain iteratively from each animal, until the order is fulfilled
	 * @return true if the order was fulfilled, false otherwise
	 */
	public boolean handleOrder(Order order) {
		for(Animal animal : animalList) {
			animal.handleOrder(order);
			
			if(order.isCompleted()) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<Animal> getAnimalList() {
		return animalList;
	}
	
	public void setAnimalList(List<Animal> animalList) {
		this.animalList = animalList;
	}
	
	/**
	 * @return the JSON representation of the object
	 */
	public ObjectNode toJson() {
		ObjectNode flockNode = JsonNodeFactory.instance.objectNode();
    	ArrayNode animals = flockNode.putArray("flock");
    	
    	for(Animal animal : animalList) {
    		animals.add(animal.toJson());
    	}
    	
    	return flockNode;
	}
}
