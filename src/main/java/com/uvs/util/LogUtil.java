package com.uvs.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvs.domain.Log;
import com.uvs.domain.LogEntry;
import com.uvs.domain.different.Host;
import com.uvs.domain.different.OtherLogEntry;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogUtil {

    private static final Logger LOG = LoggerFactory.getLogger(LogUtil.class);

    private ObjectMapper mapper = new ObjectMapper();

    public LogEntry convertLogFormat(OtherLogEntry otherLogEntry) {
        LogEntry logEntry = new LogEntry();
        try {
            List<Log> logs = new ArrayList<>();
            Log log = new Log();
            if (StringUtils.isEmpty(otherLogEntry.getMessage()) || otherLogEntry.getHost() == null
                    || otherLogEntry.getFields() == null || otherLogEntry.getLog() == null) {
                return null;
            }
            log.setContent(otherLogEntry.getMessage());
            String timestamp = otherLogEntry.getTimestamp();
            if (!StringUtils.isEmpty(timestamp) && timestamp.contains("Z")) {
                timestamp = String.valueOf(DateUtil.formatUTCToTimestamp(timestamp));
            }
            log.setTimestamp(timestamp);
            if (otherLogEntry.getHost() != null) {
                Host host = otherLogEntry.getHost();
                if (host.getIp() != null && !host.getIp().isEmpty()) {
                    log.setIp(host.getIp().get(0));
                }
            }
            if (otherLogEntry.getFields() != null && !otherLogEntry.getFields().isEmpty()) {
       /* log.setPodname(otherLogEntry.getFields().getPodName());
        log.setCluster(otherLogEntry.getFields().getCluster());*/
                log.setTags(otherLogEntry.getFields());
            }

            if (otherLogEntry.getLog() != null && otherLogEntry.getLog().getFile() != null) {
                log.setFilepath(otherLogEntry.getLog().getFile().getPath());
            }
            logs.add(log);
            logEntry.setLog(logs);
        } catch (Exception e) {
            LOG.warn("Log file conversion failed", e);
        }
        return logEntry;
    }

    public LogEntry batchConvertLog(ConsumerRecords<String, String> records, KafkaConsumer consumer) {
        LogEntry logEntry = new LogEntry();
        List<Log> logs = new ArrayList<>();
        try {
            for (ConsumerRecord<String, String> record : records) {
                String msg = record.value();
                if (!StringUtils.isEmpty(msg)) {
                    if (msg.charAt(0) != '[') {
                        LOG.warn("Json syntax error,support for Array. json{}", msg);
                        break;
                    }
                    JavaType javaType = getCollectionType(ArrayList.class, OtherLogEntry.class);
                    List<OtherLogEntry> list = mapper.readValue(msg, javaType);
                    for (OtherLogEntry otherLogEntry : list) {
                        if (StringUtils.isEmpty(otherLogEntry.getMessage()) || otherLogEntry.getHost() == null
                                || otherLogEntry.getFields() == null || otherLogEntry.getLog() == null) {
                            continue;
                        }
                        Log log = new Log();
                        log.setContent(otherLogEntry.getMessage());
                        String timestamp = otherLogEntry.getTimestamp();
                        if (!StringUtils.isEmpty(timestamp) && timestamp.contains("Z")) {
                            timestamp = String.valueOf(DateUtil.formatUTCToTimestamp(timestamp));
                        }
                        log.setTimestamp(timestamp);
                        if (otherLogEntry.getHost() != null) {
                            Host host = otherLogEntry.getHost();
                            if (host.getIp() != null && !host.getIp().isEmpty()) {
                                log.setIp(host.getIp().get(0));
                            }
                        }
                        if (otherLogEntry.getFields() != null && !otherLogEntry.getFields().isEmpty()) {
                            log.setTags(otherLogEntry.getFields());
                        }

                        if (otherLogEntry.getLog() != null && otherLogEntry.getLog().getFile() != null) {
                            log.setFilepath(otherLogEntry.getLog().getFile().getPath());
                        }
                        logs.add(log);
                    }
                }
            }
            consumer.commitAsync();
        } catch (Exception e) {
            LOG.warn("Log file conversion failed", e);
            consumer.commitAsync();
        }
        logEntry.setLog(logs);
        return logEntry;
    }

    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
