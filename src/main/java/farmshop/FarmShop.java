package farmshop;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import farmshop.animal.Goat;
import farmshop.animal.Lamb;
import farmshop.animal.Sheep;

/**
 * controller class where we implement the endpoints of our service
 */
@RestController
@EnableAutoConfiguration
public class FarmShop {

	private Flock flock = null;
	
	/**
	 * initialize the flock by reading data from a local xml file
	 * we use JAXB for XML parsing
	 */
    @RequestMapping("/farmshop/init")
    public String init() {
        try {
        	JAXBContext jc = JAXBContext.newInstance(Flock.class, Sheep.class, Goat.class, Lamb.class);
        	Unmarshaller unmarshaller = jc.createUnmarshaller();
			flock = (Flock) unmarshaller.unmarshal(new File("data/flock.xml"));
			flock.init();
			return "Flock successfully initialized";
		} catch (JAXBException e) {
			e.printStackTrace();
			return "Flock couldn't be initialized";
		}
    }
    
    /**
     * return the list of animals in JSON format
     */
    @RequestMapping(value = "/farmshop/flock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode flock() {
    	return flock.toJson();
    }

    /**
     * return the stock in JSON format
     */
    @RequestMapping(value = "/farmshop/stock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode stock() {
    	return flock.getStock();
    }

    /**
     * place an order from a customer and updates the flock
     */
    @RequestMapping(value = "/farmshop/order", method = RequestMethod.POST)
    public String order(@RequestBody ObjectNode orderNode) {
    	String customer = orderNode.findPath("customer").asText();
    	int milk = orderNode.findPath("milk").asInt();
    	int wool = orderNode.findPath("wool").asInt();
    	Order order = new Order(customer, milk, wool);
    	flock.handleOrder(order);
    	
    	if(order.isCompleted()) {
    		return "Order completed successfully";
    	} else {
    		return "Order couldn't be completed, insufficient stock";
    	}
    }
}
