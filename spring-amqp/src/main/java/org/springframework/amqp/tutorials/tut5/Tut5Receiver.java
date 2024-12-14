
package org.springframework.amqp.tutorials.tut5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;


public class Tut5Receiver {

	@RabbitListener(queues = "#{autoDeleteQueue1.name}")
	public void receive1(String in) throws InterruptedException {
		receive(in, 1);
	}

	@RabbitListener(queues = "#{autoDeleteQueue2.name}")
	public void receive2(String in) throws InterruptedException {
		receive(in, 2);
	}

	@RabbitListener(queues = "#{autoDeleteQueue3.name}")
	public void receive3(String in) throws InterruptedException {
		receive(in, 3);
	}

	public void receive(String in, int receiver) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();

		String queueRole = "";
		if(receiver == 1) {
			queueRole = "Student";
		}
		if(receiver == 2) {
			queueRole = "Teacher";
		}
		if(receiver == 3) {
			queueRole = "Dean";
		}
		System.out.println("instance " + queueRole + " [x] Received '" + in + "'");
		doWork(in);
		watch.stop();
		System.out.println("instance " + queueRole + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
	}

	private void doWork(String in) throws InterruptedException {
		for (char ch : in.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}

}
