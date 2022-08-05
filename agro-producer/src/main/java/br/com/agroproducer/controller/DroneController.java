package br.com.agroproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agroproducer.service.DroneService;

@RestController
@RequestMapping(value = "drone")
public class DroneController {
	
	@Autowired
	private DroneService droneService;
	
	@PostMapping
	private ResponseEntity criarDroneSimulacao() {
		for (int count = 1; count <= 10; count++) {
			droneService.criarDroneSimulacao(count);
		}
		
		return new ResponseEntity(HttpStatus.OK);
	}
}