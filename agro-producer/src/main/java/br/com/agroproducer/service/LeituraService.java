package br.com.agroproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agrolib.dto.LeituraDTO;
import br.com.agrolib.constantes.RabbitmqConstantes;

@Service
public class LeituraService {
	
	@Autowired
	private RabbitmqService rabbitmqService;
	
	public boolean enviarMensagem(LeituraDTO leituraDTO) {
		boolean valido = validar(leituraDTO);
		
		if (valido) {
			this.rabbitmqService.enviarMensagem(RabbitmqConstantes.FILA_LEITURA, leituraDTO);
		}
		
		return valido;
	}
	
	public boolean validar(LeituraDTO leituraDTO) {
		boolean valido = true;
		
		return valido;
	}
}