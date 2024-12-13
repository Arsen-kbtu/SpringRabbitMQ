
package org.springframework.amqp.tutorials.tut5;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tut5","topics"})
@Configuration
public class Tut5Config {

	@Bean
	public TopicExchange topic() {
		return new TopicExchange("tut.topic");
	}

	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Tut5Receiver receiver() {
	 	 	return new Tut5Receiver();
		}

		@Bean
		public Queue autoDeleteQueue1() {
			return new AnonymousQueue();
		}

		@Bean
		public Queue autoDeleteQueue2() {
			return new AnonymousQueue();
		}

		@Bean
		public Queue autoDeleteQueue3() { return new AnonymousQueue(); }

		@Bean
		public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("student.*.*");
		}

		@Bean
		public Binding binding1b(TopicExchange topic, Queue autoDeleteQueue1) {
			return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.lecture");
		}

		@Bean
		public Binding binding2b(TopicExchange topic, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("teacher.*.*");
		}

		@Bean
		public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("*.*.lecture");
		}

		@Bean
		public Binding binding2c(TopicExchange topic, Queue autoDeleteQueue2) {
			return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("*.computer-science.*");
		}

		@Bean
		public Binding binding3a(TopicExchange topic, Queue autoDeleteQueue3) {
			return BindingBuilder.bind(autoDeleteQueue3).to(topic).with("dean.*.*");
		}

		@Bean
		public Binding binding3b(TopicExchange topic, Queue autoDeleteQueue3) {
			return BindingBuilder.bind(autoDeleteQueue3).to(topic).with("*.computer-science.*");
		}

	}

	@Profile("sender")
	@Bean
	public Tut5Sender sender() {
		return new Tut5Sender();
	}

}
