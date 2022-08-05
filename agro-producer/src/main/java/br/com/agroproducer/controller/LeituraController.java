package br.com.agroproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agrolib.dto.LeituraDTO;
import br.com.agroproducer.service.LeituraService;

@RestController
@RequestMapping(value = "leitura")
public class LeituraController {
	
	@Autowired
	private LeituraService leituraService;
	
	private ResponseEntity insereLeitura(@RequestBody LeituraDTO leituraDTO) {
		System.out.println(leituraDTO.droneId);
		
		if (this.leituraService.enviarMensagem(leituraDTO))
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
	}
}