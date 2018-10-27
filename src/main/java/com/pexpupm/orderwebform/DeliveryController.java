package com.pexpupm.orderwebform;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryController {
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private ElementRepository elementRepository;

	private List<Delivery> deliveries = new ArrayList<>();

	public DeliveryController() {
	}

	@RequestMapping("/")
	public String showDeliveries(Model model) {
		deliveries.clear();
		for (Delivery delivery : deliveryRepository.findAll()) {
			deliveries.add(delivery);
		}
		// Add to model
		model.addAttribute("deliveries", deliveries);
		return "all_deliveries";
	}

	@RequestMapping("/delivery/new")
	public String newDelivery(Model model, Delivery delivery, @RequestParam String[] elements) {
		for (String elem : elements) {
			Element element = new Element(elem);
			element.setDelivery(delivery);
			delivery.getElements().add(element);
		}
		deliveries.add(delivery);
		deliveryRepository.save(delivery);
		model.addAttribute("delivery", delivery);
		return "delivery_correctly_stored";
	}

	@RequestMapping("/delivery/{delivery_id}")
	public String showDelivery(Model model, @PathVariable Long delivery_id) {
		Optional<Delivery> delivery = deliveryRepository.findById(delivery_id);
		if (delivery.isPresent()) {
			model.addAttribute("delivery", delivery.get());
			List<Element> elem_list = new ArrayList<Element>();
			for(Element elem: elementRepository.findByDeliveryName(delivery.get().getName())) {
				elem_list.add(elem);
			}
			model.addAttribute("elements", elem_list);
			return "show_delivery";
		}
		model.addAttribute("delivery_id", delivery_id);
		return "delivery_id_not_found";
	}
}

