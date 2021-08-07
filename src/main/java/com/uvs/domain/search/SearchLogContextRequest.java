package com.uvs.domain.search;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class SearchLogContextRequest {

    @Valid
    @NotNull(message = "Anchor field cannot be empty!")
    @Size(min = 1, message = "Anchor list cannot be empty!")
    private List<Object> anchor;
    private String direction = "both"; //搜索方向,默认both,可取值:up,down,both
    @NotBlank(message = "Log id cannot be empty!")
    private String id;
    @NotNull(message = "lineSize field cannot be empty!")
    private Long lineSize;
    @NotNull(message = "time field cannot be empty!")
    private Long time;

    public List<Object> getAnchor() {
        return anchor;
    }

    public void setAnchor(List<Object> anchor) {
        this.anchor = anchor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLineSize() {
        return lineSize;
    }

    public void setLineSize(Long lineSize) {
        this.lineSize = lineSize;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SearchLogContextRequest{" +
                "anchor=" + anchor +
                ", direction='" + direction + '\'' +
                ", id='" + id + '\'' +
                ", lineSize=" + lineSize +
                ", time=" + time +
                '}';
    }
}
