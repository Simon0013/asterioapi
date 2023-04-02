package com.asterio.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "banner")
public class Banner {
	private long id;
	private String name;
	private String banner_text;
	private double price;
	private boolean deleted;
	
	public Banner() {
		
	}
	
	public Banner(long id, String name, String banner_text, double price, boolean deleted) {
		this.id = id;
		this.name = name;
		this.banner_text = banner_text;
		this.price = price;
		this.deleted = deleted;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getText() {
		return banner_text;
	}
	
	public void setText(String text) {
		banner_text = text;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
