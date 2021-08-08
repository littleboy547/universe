package com.uvs;

import com.uvs.kafka.LogKafkaConsumer;
import com.uvs.util.SpringContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UvsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UvsApplication.class, args);
		new Thread((LogKafkaConsumer) SpringContext.getBean("logKafkaConsumer")).start();
	}

}
