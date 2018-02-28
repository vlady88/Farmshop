package farmshop.animal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * class representing a sheep
 * we use @Xml* annotations for easy XML parsing with JAXB
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sheep extends Animal implements MilkAnimal, WoolAnimal {

	@XmlAttribute
	private int wool = 0;
	@XmlAttribute
	private int milk = 0;
	
	/**
	 * if it is female, then it can produce milk
	 */
	@Override
	public void init() {
		if(gender == Gender.FEMALE) {
			milk = 30;
		}
	}
	
	@Override
	public void setGender(Gender gender) {
		super.setGender(gender);
	}
	
	@Override
	public int getWool() {
		return wool;
	}

	@Override
	public void setWool(int wool) {
		this.wool = wool;
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
		json.put("type", "sheep");
		json.put("wool", wool);
		
		if(gender == Gender.FEMALE) {
			json.put("milk", milk);
		}
		
		return json;
	}

}
