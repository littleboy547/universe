package com.uvs.domain.different;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Host {

    @Valid
    @NotNull(message = "Ip field cannot be empty!")
    @Size(min = 1, message = "Ip cannot be empty!")
    private List<String> ip;

    @Valid
    @NotBlank(message = "Hostname field cannot be empty!")
    private String hostname;

    public List<String> getIp() {
        return ip;
    }

    public void setIp(List<String> ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
