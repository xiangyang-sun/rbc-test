package com.johnsun.simplebasket.model;

//Fruits quantity in basket
public class FruitQuantity {
	private int fruitId;
	private int quantity;
	private Fruit fruit = null; // will be populated using Fruit after the read.

	public FruitQuantity(int fruitId, int quantity) {
		this.fruitId = fruitId;
		this.quantity = quantity;
	}

	public FruitQuantity() {
	}

	public int getFruitId() {
		return fruitId;
	}

	public void setFruitId(int fruitId) {
		this.fruitId = fruitId;
	}

	public Fruit getFruit() {
		return fruit;
	}

	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "FruitQuantity [fruitId=" + fruitId + ", quantity=" + quantity + "]";
	}

}
