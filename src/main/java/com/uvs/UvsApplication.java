package com.uvs;

import com.uvs.jdq.LogKafkaConsumer;
import com.uvs.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UvsApplication {

    private static final Logger log = LoggerFactory.getLogger(UvsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UvsApplication.class, args);
        new Thread((LogKafkaConsumer) SpringContextUtil.getBean("logKafkaConsumer")).start();
    }
}