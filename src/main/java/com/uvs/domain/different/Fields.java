package com.uvs.domain.different;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Fields {

    @Valid
    @NotBlank(message = "pod_name field cannot be empty!")
    @JsonProperty("pod_name")
    private String podName;

    @Valid
    @NotBlank(message = "flink_cluster_id field cannot be empty!")
    @JsonProperty("flink_cluster_id")
    private String cluster;

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
