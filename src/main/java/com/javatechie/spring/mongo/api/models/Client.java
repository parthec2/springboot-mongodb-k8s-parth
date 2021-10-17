package com.javatechie.spring.mongo.api.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection="client")
public class Client {

	@Id
	private Long id;
	private String clientName;
	private String clientType;
	private Integer clientID;
	private Integer gfcid;
	private String clientRegion;
	
	
}
