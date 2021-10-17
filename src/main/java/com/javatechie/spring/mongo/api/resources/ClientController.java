package com.javatechie.spring.mongo.api.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.mongo.api.models.Client;
import com.javatechie.spring.mongo.api.repository.ClientRepo;

@RestController
public class ClientController {

	
	@Autowired
	private ClientRepo clientRepo;
	
	@GetMapping("/getAllClients")
	public List<Client> getAllClients() {
		return clientRepo.findAll();
	}
	
	@GetMapping("/getClient/{id}")
	public Optional<Client> getClient(@PathVariable Long id) {
		return clientRepo.findById(id);
	}
	
	
	@PostMapping("/addClient")
	public String addClient (@RequestBody Client clnt) {
	    Client temp=clnt;	
		clientRepo.save(clnt);
		return "Added Client with name :-" + clnt.getClientName();
	}
	
	@DeleteMapping("/deleteClient/{id}")
	public String deleteClient (@PathVariable Long id) {
		clientRepo.deleteById(id);
		return "Client Deleted";
	}
}