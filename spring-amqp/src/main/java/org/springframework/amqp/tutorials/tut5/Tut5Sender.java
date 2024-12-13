
package org.springframework.amqp.tutorials.tut5;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;


public class Tut5Sender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	AtomicInteger index = new AtomicInteger(0);

	AtomicInteger count = new AtomicInteger(0);

	private final String[] keys = {"dean.administration.announce", "dean.computer-science.approve", "student.arts.submit",
			"student.computer-science.query", "student.engineering.project", "teacher.arts.grades", "teacher.engineering.supervise"};

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		StringBuilder builder = new StringBuilder("A message with key: ");
		if (this.index.incrementAndGet() == keys.length) {
			this.index.set(0);
		}
		String key = keys[this.index.get()];
		builder.append(key).append(' ');
		builder.append(this.count.incrementAndGet());
		String message = builder.toString();
		template.convertAndSend(topic.getName(), key, message);
		System.out.println(" [x] Sent '" + message + "'");
	}

}
