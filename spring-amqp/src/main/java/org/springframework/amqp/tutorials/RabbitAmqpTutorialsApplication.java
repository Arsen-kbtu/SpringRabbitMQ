/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.amqp.tutorials;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitAmqpTutorialsApplication {

	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("This app uses Spring Profiles to control its behavior.\n");
			System.out.println("Options are: ");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=hello-world,receiver --server.port=8081");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=hello-world,sender --server.port=8082");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=work-queues,receiver --server.port=8083");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=work-queues,sender --server.port=8084");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=pub-sub,receiver --server.port=8085");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=pub-sub,sender --server.port=8086");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=routing,receiver --server.port=8087");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=routing,sender --server.port=8088");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=topics,receiver --server.port=8089");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=topics,sender --server.port=8090");

			System.out.println("API java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=message_controller,topics --server.port=8099");

			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=rpc,client --server.port=8091");
			System.out.println("java -jar target/rabbitmq-tutorials.jar --spring.profiles.active=rpc,server --server.port=8092");
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpTutorialsRunner();
	}

    public static void main(String[] args) {
        SpringApplication.run(RabbitAmqpTutorialsApplication.class, args);
    }

}
