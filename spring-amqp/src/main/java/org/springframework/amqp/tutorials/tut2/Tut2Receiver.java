
package org.springframework.amqp.tutorials.tut2;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

import java.util.Random;

// ackMode can be equal to NONE, MANUAL, AUTO
// NONE: no acknowledge
// MANUAL: manual acknowledge
// AUTO: automatic acknowledge
@RabbitListener(queues = "tut.hello", ackMode = "AUTO")
public class Tut2Receiver {

	private final int instance;

	public Tut2Receiver(int i) {
		this.instance = i;
	}

	@RabbitHandler
	public void receive(String apartment) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println("instance " + this.instance + " [x] Received '" + apartment + "'");
		doWork(apartment);
		watch.stop();
		System.out.println("instance " + this.instance + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		Random random = new Random();
		int randomSleepTime = 500 * (random.nextInt(4) + 1);
		Thread.sleep(randomSleepTime);
		System.out.println("instance " + this.instance + " [x] Saved apartment " + in + " to database");
	}

}
