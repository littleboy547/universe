package com.uvs.domain.different;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class File {

    @Valid
    @NotBlank(message = "Log path field cannot be empty!")
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
