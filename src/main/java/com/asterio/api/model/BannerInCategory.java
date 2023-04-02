package com.asterio.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "banner_in_category")
public class BannerInCategory {
	private long id_banner;
	private long id_category;
	
	public BannerInCategory() {
		
	}
	
	public BannerInCategory(long id_banner, long id_category) {
		this.id_banner = id_banner;
		this.id_category = id_category;
	}
	
	public long getBanner() {
		return id_banner;
	}
	
	public void setBanner(long id) {
		id_banner = id;
	}
	
	public long getCategory() {
		return id_category;
	}
	
	public void setCategory(long id) {
		id_category = id;
	}
}
