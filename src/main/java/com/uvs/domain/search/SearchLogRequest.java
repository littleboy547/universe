package com.uvs.domain.search;

import java.util.ArrayList;
import java.util.List;

public class SearchLogRequest {
    private String serviceId;
    private String action;
    private List<Meta> metas = new ArrayList<>();
    private Boolean caseSensitive = false;
    private String startTime;
    private String endTime;
    private long pageNumber;
    private long pageSize;
    private String sort = "desc";
    private String content;
    private String searchMeta;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<Meta> getMetas() {
        return metas;
    }

    public void setMetas(List<Meta> metas) {
        this.metas = metas;
    }

    public Boolean getCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(Boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSearchMeta() {
        return searchMeta;
    }

    public void setSearchMeta(String searchMeta) {
        this.searchMeta = searchMeta;
    }

    @Override
    public String toString() {
        return "SearchLogRequest{" +
                "serviceId='" + serviceId + '\'' +
                ", action='" + action + '\'' +
                ", metas=" + metas +
                ", caseSensitive=" + caseSensitive +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", sort='" + sort + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
