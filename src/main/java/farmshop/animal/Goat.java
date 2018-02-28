package farmshop.animal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * class representing a goat
 * we use @Xml* annotations for easy XML parsing with JAXB
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Goat extends Animal implements MilkAnimal {

	@XmlAttribute
	private int milk = 0;
	
	/**
	 * if we have a female, then it can produce milk
	 */
	@Override
	public void init() {
		if(gender == Gender.FEMALE) {
			milk = 50;
		}
	}
	
	@Override
	public void setGender(Gender gender) {
		super.setGender(gender);
	}
	
	@Override
	public int getMilk() {
		return milk;
	}

	@Override
	public void setMilk(int milk) {
		this.milk = milk;
	}

	/**
	 * @return the JSON representation of the object
	 */
	@Override
	public ObjectNode toJson() {
		ObjectNode json = super.toJson();
		json.put("type", "goat");
		
		if(gender == Gender.FEMALE) {
			json.put("milk", milk);
		}
		
		return json;
	}
}
