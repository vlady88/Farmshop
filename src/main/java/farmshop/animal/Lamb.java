package farmshop.animal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * class representing a lamb
 * we use @Xml* annotations for easy XML parsing with JAXB
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Lamb extends Animal implements WoolAnimal {

	@XmlAttribute
	private int wool = 0;
	
	@Override
	public int getWool() {
		return wool;
	}

	@Override
	public void setWool(int wool) {
		this.wool = wool;
	}

	/**
	 * @return the JSON representation of the object
	 */
	@Override
	public ObjectNode toJson() {
		ObjectNode json = super.toJson();
		json.put("type", "lamb");
		json.put("wool", wool);
		return json;
	}
	
}
