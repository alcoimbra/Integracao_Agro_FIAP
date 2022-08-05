package br.com.agroconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.agrolib.constantes.RabbitmqConstantes;
import br.com.agrolib.dto.LeituraDTO;
@Component
public class LeituraConsumer {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RabbitListener(queues = RabbitmqConstantes.FILA_LEITURA)
	private void consumidor(LeituraDTO leituraDTO) {
		boolean alertaTemperatura = leituraDTO.temperatura >= 35 || leituraDTO.temperatura <= 0 ? true : false;
		boolean alertaUmidade = leituraDTO.umidade <= 15 ? true : false;
		
		if (alertaTemperatura || alertaUmidade) {
			String alerta = "O Drone de ID " + String.valueOf(leituraDTO.droneId) + " apresentou "
					+ "temperatura " + String.valueOf(leituraDTO.temperatura) + "º"
					+ " e umidade " + String.valueOf(leituraDTO.umidade) + "%";
			
			System.out.println(alerta);
			
			SimpleMailMessage mensagem = new SimpleMailMessage();
			mensagem.setText(alerta);
			mensagem.setSubject("Alerta Drone ID " + String.valueOf(leituraDTO.droneId));
			mensagem.setTo("emailPessoal@email.com");
			mensagem.setFrom("emailDaPessoaQueIraEnviar@email.com");
			
			try {
				mailSender.send(mensagem);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(alerta);
		}
	}
}