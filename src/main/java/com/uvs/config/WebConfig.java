package com.uvs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Value("${report.platform.key.list:\"\"}")
    private String reportPlatform;
    @Value("${maxThread:1000}")
    private Integer maxThread;
    @Value("${cloud.log.accessKeyId:\"\"}")
    private String accessKeyId;
    @Value("${cloud.log.secretAccessKey:\"\"}")
    private String secretAccessKey;
    @Value("${cloud.log.endPoint:\"\"}")
    private String cloudLogEndPoint;
    @Value("${cloud.log.http.connectionTimeout:2000}")
    private int connectionTimeout;
    @Value("${cloud.log.regionId:\"\"}")
    private String regionId;
    @Value("${cloud.log.logsetUID:\"\"}")
    private String logsetUID;
    @Value("${cloud.log.logtopicUID:\"\"}")
    private String logtopicUID;
    @Value("${search.log.service:\"\"}")
    private String logService;
    @Value("${server.port:9300}")
    private String serverPort;
    @Value("${spring.eshost:localhost}")
    private String eshost;
    @Value("${spring.remove.es.time:604800000}")
    private String removeEsTime;
    @Value("${es.initialization.new.indices:{\"mappings\":{\"properties\":{\"timestamp\":{\"type\":\"keyword\"},\"ip\":{\"type\":\"keyword\"}}}}}")
    private String esIndicesParameter;
    @Value("${spring.prometheus.url:localhost}")
    private String url;
    @Value("${spring.prometheus.serviceId}")
    private String serviceId;
    @Value("${spring.prometheus.dataFmt}")
    private String dataFmt;

    public String getReportPlatform() {
        return reportPlatform;
    }
}