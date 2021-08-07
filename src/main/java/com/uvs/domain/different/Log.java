package com.uvs.domain.different;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Log {

    @Valid
    @NotNull(message = "File field cannot be empty!")
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
