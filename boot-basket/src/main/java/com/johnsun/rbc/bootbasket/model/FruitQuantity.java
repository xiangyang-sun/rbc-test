package com.johnsun.rbc.bootbasket.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Fruits quantity in basket
@Entity
@Table(name = "fruit_quantity")
public class FruitQuantity {
	
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO ) 	
	Integer qid;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fruit_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Fruit fruit; // will be populated using Fruit after the read.

	private int quantity;


	public FruitQuantity() {
	}

	public FruitQuantity(Integer qid, int quantity) {
		this.qid = qid;
		this.quantity = quantity;
	}

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
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
		return "FruitQuantity [qid=" + qid + ", fruit=" + fruit + ", quantity=" + quantity + "]";
	}

}
