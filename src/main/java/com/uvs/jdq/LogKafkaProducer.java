package com.uvs.jdq;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

/*@Service*/
public class LogKafkaProducer implements InitializingBean, DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(LogKafkaProducer.class);

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String brokerList;
    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.consumer.client-id}")
    private String clientID;
    private Producer<String, String> producer = null;

    private void initProducer() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientID);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        properties.put("retries", 1);
        properties.put("max.in.flight.requests.per.connection", 2);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
        log.info("Call initProducer");
    }

    public void sendMsg(String msg) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        try {
            producer.send(record, new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        log.warn("Kafka producer message failure!", e);
                    } else {
                        log.info("Kafka producer offset[" + metadata.offset() + "] - partition[" + metadata
                                .partition()
                                + "] - time[" + "] is aready send ok!");
                    }
                }
            });
            producer.flush();
        } catch (Exception e) {
            log.warn("Kafka producer message failure!", e);
        }
    }

    @Override
    public void destroy() throws Exception {
        if (producer != null) {
            producer.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            initProducer();
        } catch (Exception e) {
            log.warn("Init kafka producer failure!", e);
        }
    }
}
