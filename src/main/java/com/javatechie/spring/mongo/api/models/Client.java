package com.javatechie.spring.mongo.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="client")
public class Client {

	@Id
	private Long id;
	private String clientName;
	private String clientType;
	private Integer clientID;
	private Integer gfcid;
	private String clientRegion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public Integer getClientID() {
		return clientID;
	}
	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}
	public Integer getGfcid() {
		return gfcid;
	}
	public void setGfcid(Integer gfcid) {
		this.gfcid = gfcid;
	}
	public String getClientRegion() {
		return clientRegion;
	}
	public void setClientRegion(String clientRegion) {
		this.clientRegion = clientRegion;
	}
	
	
	
}
