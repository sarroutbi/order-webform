package com.pexpupm.orderwebform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseUsage implements CommandLineRunner {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private ElementRepository elementRepository;

	@Override
	public void run(String ...args) throws Exception {
		System.out.println("**** Starting ****");
		Delivery delivery1 = new Delivery("Delivery1");
		Delivery delivery2 = new Delivery("Delivery2");

		Element coffee = new Element("Coffee");
		Element milk = new Element("Milk");
		milk.setDelivery(delivery1);
		coffee.setDelivery(delivery1);
		delivery1.getElements().add(coffee);
		delivery1.getElements().add(milk);
		deliveryRepository.save(delivery1);

		Element water = new Element("Water");
		Element cola = new Element("Cola");
		cola.setDelivery(delivery2);
		water.setDelivery(delivery2);
		delivery2.getElements().add(water);
		delivery2.getElements().add(cola);
		deliveryRepository.save(delivery2);

		for (Delivery delivery : deliveryRepository.findAll()) {
			System.out.println("- Delivery:" + delivery.getName());
			for(Element elem: elementRepository.findByDeliveryName(delivery.getName())) {
				System.out.println("  * Elem:" + elem.getName());
			}
		}

		// This code shows how to delete complete deliveries or elements
//		deliveryRepository.delete(deliveryRepository.findByName("Delivery1"));
//		Element elemToDelete = elementRepository.findByNameAndDeliveryName("Water", "Delivery2");
//		elementRepository.deleteById(elemToDelete.getId());
//		for (Delivery delivery : deliveryRepository.findAll()) {
//			System.out.println("- Delivery:" + delivery.getName());
//			for(Element elem: elementRepository.findByDeliveryName(delivery.getName())) {
//				System.out.println("  * Elem:" + elem.getName());
//			}
//		}
	}

}
