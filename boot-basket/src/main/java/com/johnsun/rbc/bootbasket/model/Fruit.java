package com.johnsun.rbc.bootbasket.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Fruit {
	@Id
	private Integer id; // fruit id, unique
	
	private String name; // fruit name
	private double price;

	public Fruit() {
	}

	public Fruit(Integer id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Fruit [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}