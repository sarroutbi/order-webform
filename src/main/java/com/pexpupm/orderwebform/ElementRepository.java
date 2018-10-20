package com.pexpupm.orderwebform;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Long> {
	List<Element> findByDeliveryName(String delivery);
}