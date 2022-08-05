package br.com.agroproducer.connection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import br.com.agrolib.constantes.RabbitmqConstantes;

@Component
public class RabbitmqConnection {
	
	private static final String NOME_EXCHANGE = "amq.direct";
	private AmqpAdmin amqpAdmin;
	
	public RabbitmqConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}
	
	private Queue fila(String nomeFila) {
		return new Queue(nomeFila, true, false, false);
	}
	
	private DirectExchange trocaDireta() {
		return new DirectExchange(NOME_EXCHANGE);
	}
	
	private Binding relacionamento(Queue fila, DirectExchange troca) {
		return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
	}
	
	private void adiciona() {
		Queue fila = this.fila(RabbitmqConstantes.FILA_LEITURA);
		DirectExchange troca = this.trocaDireta();
		Binding ligacao = this.relacionamento(fila, troca);
		
		this.amqpAdmin.declareQueue(fila);
		this.amqpAdmin.declareExchange(troca);
		this.amqpAdmin.declareBinding(ligacao);
	}
}