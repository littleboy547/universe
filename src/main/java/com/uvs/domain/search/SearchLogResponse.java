package com.uvs.domain.search;


public class SearchLogResponse {
    private String requestId;
    private String message;
    private int statusCode;
    private SearchLogResult result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public SearchLogResult getResult() {
        return result;
    }

    public void setResult(SearchLogResult result) {
        this.result = result;
    }
}