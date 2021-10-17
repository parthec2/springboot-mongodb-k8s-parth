package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.mongo.api.models.Client;

public interface ClientRepo extends MongoRepository<Client, Long>{
	
	
}
