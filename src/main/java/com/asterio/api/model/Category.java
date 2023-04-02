package com.asterio.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
	private long id;
	private String name;
	private long request_id;
	private boolean deleted;
	
	public Category() {
		
	}
	
	public Category(long id, String name, long request_id, boolean deleted) {
		this.id = id;
		this.name = name;
		this.request_id = request_id;
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
	
	public long getRequestId() {
		return request_id;
	}
	
	public void setRequestId(long request_id) {
		this.request_id = request_id;
	}
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
