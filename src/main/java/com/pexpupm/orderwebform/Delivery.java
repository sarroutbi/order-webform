package com.pexpupm.orderwebform;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public long getId() {
		return id;
	}
	public Delivery() {
		super();
		this.elements = new ArrayList<Element>();
	}
	public Delivery(String name) {
		super();
		this.name = name;
		this.elements = new ArrayList<Element>();
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Element> getElements() {
		return elements;
	}

	@OneToMany(mappedBy="delivery", cascade=CascadeType.ALL)
	private List<Element> elements;
}