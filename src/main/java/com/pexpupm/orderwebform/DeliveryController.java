package com.pexpupm.orderwebform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			for(Element elem: elementRepository.findByDeliveryId(delivery.get().getId())) {
				elem_list.add(elem);
			}
			model.addAttribute("elements", elem_list);
			return "show_delivery";
		}
		model.addAttribute("delivery_id", delivery_id);
		return "delivery_id_not_found";
	}

	@RequestMapping("/delivery/delete/{delivery_id}")
	public String deleteDelivery(Model model, @PathVariable Long delivery_id) {
		Optional<Delivery> delivery = deliveryRepository.findById(delivery_id);
		if (delivery.isPresent()) {
			model.addAttribute("delivery", delivery.get());
			deliveryRepository.delete(delivery.get());
			return "delivery_correctly_deleted";
		}
		model.addAttribute("delivery_id", delivery_id);
		return "delivery_id_not_found";
	}

	@RequestMapping("/delivery/modify/{delivery_id}")
	public String modifyDelivery(Model model, @PathVariable Long delivery_id) {
		Optional<Delivery> delivery = deliveryRepository.findById(delivery_id);
		if (delivery.isPresent()) {
			model.addAttribute("delivery", delivery.get());
			List<Element> elem_list = new ArrayList<Element>();
			for(Element elem: elementRepository.findByDeliveryId(delivery.get().getId())) {
				elem_list.add(elem);
			}
			model.addAttribute("elements", elem_list);
			model.addAttribute("elements_length", elem_list.size());
			return "show_modifyable_delivery";
		}
		model.addAttribute("delivery_id", delivery_id);
		return "delivery_id_not_found";
	}

	@RequestMapping(value = "/delivery/modify/commit/{delivery_id}", method = RequestMethod.POST)
	public String commitModifyDelivery(Model model, @PathVariable Long delivery_id,
			Delivery delivery, @RequestParam String[] elements, @RequestParam(value = "checkbox", required = false) List<String> checkbox) {
		Optional<Delivery> deliveryPersisted = deliveryRepository.findById(delivery_id);
		if (deliveryPersisted.isPresent()) {
			for(Element elem: elementRepository.findByDeliveryId(deliveryPersisted.get().getId())) {
				elementRepository.deleteById(elem.getId());
			}
			for (String elem : elements) {
				Element element = new Element(elem);
				element.setChecked(false);
				if(checkbox != null) {
					Set<String> set = new HashSet<String>(checkbox);
					if (set.contains(elem))	{
						element.setChecked(true);
					}
				}
				element.setDelivery(deliveryPersisted.get());
				deliveryPersisted.get().getElements().add(element);
			}
			deliveryPersisted.get().setName(delivery.getName());
			deliveryRepository.save(deliveryPersisted.get());
			model.addAttribute("delivery", deliveryPersisted.get());
			return "delivery_correctly_modified";
		}
		model.addAttribute("delivery_id", delivery_id);
		return "delivery_id_not_found";
	}
}

