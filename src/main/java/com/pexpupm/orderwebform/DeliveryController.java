package com.pexpupm.orderwebform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeliveryController {
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private ElementRepository elementRepository;

	private List<Delivery> deliveries = new ArrayList<>();

	public DeliveryController() {
		// TODO: Quit comment to add non persisted Deliveries
//		Delivery delivery1 = new Delivery("NP_delivery1");
//		Delivery delivery2 = new Delivery("NP_delivery2");
//		Element cola = new Element("NP_Cola");
//		Element milk = new Element("NP_Milk");
//		Element sugar = new Element("NP_Sugar");
//		cola.setDelivery(delivery1);
//		milk.setDelivery(delivery1);
//		sugar.setDelivery(delivery2);
//		deliveries.add(delivery1);
//		deliveries.add(delivery2);
	}

	@RequestMapping("/")
	public String tablon(Model model) {
		for (Delivery delivery : deliveryRepository.findAll()) {
			System.out.println("- Delivery:" + delivery.getName());
			deliveries.add(delivery);
			for(Element elem: elementRepository.findByDeliveryName(delivery.getName())) {
				System.out.println("  * Elem:" + elem.getName());
			}
		}
		// Add to model
		model.addAttribute("deliveries", deliveries);
		return "all_deliveries";
	}

//	@RequestMapping("/delivery/new")
//	public String nuevoAnuncio(Model model, Delivery delivery) {
//		deliveries.add(delivery);
//		// TODO: persist
//		return "delivery_correctly_stored";
//
//	}
//
//	@RequestMapping("/delivery/{delivery_id}")
//	public String showDelivery(Model model, @PathVariable int delivery_id) {
//		Delivery delivery = deliveries.get(delivery_id - 1);
//		model.addAttribute("delivery", delivery);
//		return "show_delivery";
//	}
}

