package br.com.agroproducer.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import br.com.agrolib.dto.LeituraDTO;

public class DroneSimulacaoJob implements Job {
	
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		Integer droneId = dataMap.getInt("droneId");
		LeituraDTO leituraDTO = new LeituraDTO();
		
		leituraDTO.droneID = droneId;
		leituraDTO.temperatura = getRandomNumber(-25, 40);
		leituraDTO.umidade = getRandomNumber(0, 100);
		leituraDTO.latitude = 100;
		leituraDTO.longitude = 200;
		leituraDTO.ativarRastreamento = false;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<LeituraDTO> httpExampleRequest = new HttpEntity<>(leituraDTO, headers);
		HttpEntity response = restTemplate.postForObject("http://localhost:8080/leitura", httpExampleRequest, HttpEntity.class);
	}
	
	private Integer getRandomNumber(Integer min, Integer max) {
		Integer randomNumber = (int) (Math.random() * max);
		
		if (randomNumber <= min) {
			randomNumber = randomNumber + min;
		}
		
		return randomNumber;
	}
}