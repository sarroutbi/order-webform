package com.pexpupm.orderwebform;

import org.springframework.data.repository.CrudRepository;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
	Delivery findByName(String delivery);
}
