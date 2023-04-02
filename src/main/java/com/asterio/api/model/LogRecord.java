package com.asterio.api.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "log_record")
public class LogRecord {
	private long id;
	private String ip_address;
	private String user_agent;
	private Date request_time;
	private long id_banner;
	private String no_content_reason;
	
	public LogRecord() {
		
	}
	
	public LogRecord(long id, String ip_address, String user_agent, Date request_time, long id_banner, String no_content_reason) {
		this.id = id;
		this.ip_address = ip_address;
		this.user_agent = user_agent;
		this.request_time = request_time;
		this.id_banner = id_banner;
		this.no_content_reason = no_content_reason;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getIp() {
		return ip_address;
	}
	
	public void setIp(String ip) {
		ip_address = ip;
	}
	
	public String getUserAgent() {
		return user_agent;
	}
	
	public void setUserAgent(String userAgent) {
		user_agent = userAgent;
	}
	
	public Date getRequestTime() {
		return request_time;
	}
	
	public void setRequestTime(Date requestTime) {
		request_time = requestTime;
	}
	
	public long getIdBanner() {
		return id_banner;
	}
	
	public void setIdBanner(long idBanner) {
		id_banner = idBanner;
	}
	
	public String getNoContentReason() {
		return no_content_reason;
	}
	
	public void setNoContentReason(String reason) {
		no_content_reason = reason;
	}
}
