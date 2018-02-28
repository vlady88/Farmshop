The Farmshop
============

Requirements: Maven, Java 8


Implementation Remarks
======================

The project requires Java 8, as it makes use of default interfaces, which were introduced in Java 8. We use them in order to avoid code duplication in implementing classes. To be more precise, we define MilkAnimal interface, implemented by Sheep and Goat, and WoolAnimal, implemented by Lamb and Sheep. MilkAnimal defines the method orderMilk(), which "consumes" milk from an animal. We make this method default and implement it in MilkAnimal interface to avoid duplicating the code in the 2 subclasses. The same applies to the method orderWool() from WoolAnimal. Using abstract classes wouldn't be an option, since an animal can produce both milk and wool (like Sheep), but it cannot extend 2 classes.


Setting Up the Project
======================

Compile the project with "mvn compile"

Start the service with "mvn spring-boot:run"

Run unit tests with "mvn test"


Service Endpoints
================= 

1. http://localhost:8080/farmshop/init (GET) --> Initialize farmshop from a local XML file (data/flock.xml). Make sure you call this before calling other endpoints.

2. http://localhost:8080/farmshop/flock (GET) --> Get an overview of the flock.

3. http://localhost:8080/farmshop/stock (GET) --> Get an overview of the stock.

4. http://localhost:8080/farmshop/order (POST) --> Make an order by passing the required amounts of wool and milk in the body of the request using JSON format like below:

{
	"customer": "Milk and Wool Trading Ltd",
	"order": {
		"milk": 10,
		"wool": 3
	}
}