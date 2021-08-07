package com.uvs.domain.different;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class OtherLogEntry {

    @JsonProperty("@timestamp")
    private String timestamp;
    @Valid
    @NotNull(message = "Host field cannot be empty!")
    private Host host;

    @Valid
    @NotNull(message = "Log field cannot be empty!")
    private Log log;

    @NotBlank(message = "Message field cannot be empty!")
    private String message;

    private Map<String, String> fields;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
