
package org.springframework.amqp.tutorials.tut2;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile({"tut2", "work-queues"})
@Configuration
public class Tut2Config {

	@Bean
	public Queue hello() {
		return new Queue("tut.hello", true);
	} // true: durable

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Tut2Receiver receiver1() {
			return new Tut2Receiver(1);
		}

		@Bean
		public Tut2Receiver receiver2() {
			return new Tut2Receiver(2);
		}

		@Bean
		public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
			SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
			factory.setConnectionFactory(connectionFactory);
			factory.setPrefetchCount(1); // Ensures fair dispatch (1 message at a time per consumer)
			factory.setConcurrentConsumers(2); // Optional: Number of concurrent consumers
			factory.setMaxConcurrentConsumers(5); // Optional: Max number of consumers
			return factory;
		}

	}

	@Profile("sender")
	@Bean
	public Tut2Sender sender() {
		return new Tut2Sender();
	}

}
