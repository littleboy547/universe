package com.uvs.domain.search;

import java.util.List;

public class SearchLogResult {

    private List<Object> data;
    private Long total;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
