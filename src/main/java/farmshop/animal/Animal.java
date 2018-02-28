package farmshop.animal;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import farmshop.Order;

import javax.xml.bind.annotation.XmlAccessType;

/**
 * parent class for all animal types
 * we use @Xml* annotations for easy XML parsing with JAXB
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Animal {

	public enum Gender {
		@XmlEnumValue("m") MALE("m"),
		@XmlEnumValue("f") FEMALE("f");
		
		private String symbol;
		
		Gender(String symbol) {
			this.symbol = symbol;
		}
		
		@Override
		public String toString() {
			return symbol;
		}
	}
	
	@XmlAttribute
	protected String name;
	@XmlAttribute(name="sex")
	protected Gender gender;
	
	public void init() {}
	
	/**
	 * invoke the appropriate method for each animal type 
	 */
	public void handleOrder(Order order) {
		if(this instanceof MilkAnimal) {
			((MilkAnimal)this).orderMilk(order);
		}
		if(this instanceof WoolAnimal) {
			((WoolAnimal)this).orderWool(order);
		}
	}
	
	/**
	 * @return the JSON representation of the object
	 */
	public ObjectNode toJson() {
		ObjectNode json = JsonNodeFactory.instance.objectNode();
		json.put("name", name);
		json.put("sex", gender.toString());
		return json;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
