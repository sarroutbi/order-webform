package com.pexpupm.orderwebform;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Element {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  public long getId() {
	  return id;
  }

  public Element() {
	  super();
  }

  public Element(String name) {
	super();
	this.name = name;
  }

  private String name;
  public String getName() {
	return name;
  }

  public void setName(String name) {
	this.name = name;
  }

  public void setDelivery(Delivery delivery) {
	 this.delivery = delivery;
  }

  @ManyToOne
  private Delivery delivery;

}
