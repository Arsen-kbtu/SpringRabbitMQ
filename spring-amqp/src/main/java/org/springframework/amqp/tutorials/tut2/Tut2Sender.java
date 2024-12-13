
package org.springframework.amqp.tutorials.tut2;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;


public class Tut2Sender {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queue;

	AtomicInteger dots = new AtomicInteger(0);

	AtomicInteger count = new AtomicInteger(0);

	AtomicInteger jobId = new AtomicInteger(0);

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		String jobDescription = "Job-" + jobId.incrementAndGet() + " apartment";
		Apartment apartment = new Apartment("Tole bi 87", 86000000, 1000);
		String messageContent = apartment.toString() + " " + jobId;

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
		messageProperties.setDeliveryMode(MessageProperties.DEFAULT_DELIVERY_MODE.PERSISTENT);

		Message message = new Message(messageContent.getBytes(), messageProperties);
		template.convertAndSend(queue.getName(), message);

		System.out.println(" [x] Sent '" + jobDescription + "'");
	}

}
