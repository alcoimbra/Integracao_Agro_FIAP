package br.com.agrolib.dto;

import java.io.Serializable;

public class LeituraDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Integer droneId;
	public Double latitude;
	public Double longitute;
	public Double temperatura;
	public Double umidade;
	public Boolean ativarRastreamento;
}