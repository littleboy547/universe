package com.uvs.jdq;

import com.jd.bdp.jdq.JDQConfigUtil;
import com.uvs.config.WebConfig;
import com.uvs.domain.LogEntry;
import com.uvs.util.LogUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class LogKafkaConsumer implements InitializingBean, DisposableBean, Runnable {

    private static final Logger log = LoggerFactory.getLogger(LogKafkaConsumer.class);
    private static KafkaConsumer<String, String> consumer = null;
    @Resource
    private LogUtil logUtil;
    @Resource
    private WebConfig webConfig;

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String brokerList;
    @Value("${spring.kafka.consumer.client-id}")
    private String clientID;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupID;
    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.consumer.app.domain}")
    private String appDomain;
    @Value("${spring.kafka.consumer.accessKey}")
    private String accessKey;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.max.poll.records:100}")
    private int maxPollRecords;
    @Value("${spring.kafka.poll.records.timeout:1000}")
    private long pollRecordsTimeout;

    private ExecutorService syncReportThreadExecutor = Executors.newSingleThreadExecutor();

    private Properties initPropertiesConfig() {
        Properties properties = new Properties();
        properties.putAll(JDQConfigUtil.getClientConfigs(clientID, appDomain, accessKey));
        return properties;
    }

    private void doConsumer() {
        try {
            log.info("Initialization logKafkaConsumer......");
            Properties configs = initPropertiesConfig();
            consumer = new KafkaConsumer<>(configs);
            consumer.subscribe(Arrays.asList(topic));

            while (!Thread.currentThread().isInterrupted()) {
                ConsumerRecords<String, String> records = consumer.poll(pollRecordsTimeout);
                long beginTime = System.currentTimeMillis();
                if (log.isDebugEnabled()) {
                    log.debug("ConsumerRecords count: " + records.count());
                    for (ConsumerRecord<String, String> record : records) {
                        log.debug("offset=" + record.offset() + " ,key=" + record.key() +
                                " ,record=" + record.value());
                    }
                }
                if (records != null && !records.isEmpty()) {
                    LogEntry logEntry = logUtil.batchConvertLog(records, consumer);
                    if (logEntry != null) {
                        reportLog(logEntry);
                    }
                }
                long entTime = System.currentTimeMillis();
                if (log.isDebugEnabled()) {
                    log.debug("Use time: " + (entTime - beginTime));
                }
            }
        } catch (Exception e) {
            log.warn("KafkaConsumer exception", e);
        }
    }

    @Override
    public void run() {
        log.info("Call run......" + this);
        this.doConsumer();
    }

    @Override
    public void destroy() throws Exception {
        if (consumer != null) {
            consumer.close();
        }
        syncReportThreadExecutor.shutdownNow();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Call afterPropertiesSet......");
    }

    private void reportLog(LogEntry logEntry) {
        log.info("logEntry :{}", logEntry);
        String platform = webConfig.getReportPlatform();
        if (platform != null) {
            log.info("reportLog");
        }
    }
}
