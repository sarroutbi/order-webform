package com.pexpupm.orderwebform;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Long> {
	Element findByName(String name);
	Element findByNameAndDeliveryName(String name, String deliveryName);
	List<Element> findByDeliveryName(String delivery);
}
