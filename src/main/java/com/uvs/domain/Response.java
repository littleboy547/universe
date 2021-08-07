package com.uvs.domain;

public class Response {

    private boolean success;
    private String errCode;
    private String errMsg;

    public Response() {
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "success=" + success +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

    public Response(boolean success, UnicomResponseEnums enums) {
        this.success = success;
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
    }

    public Response(boolean success, String errCode, String errMsg) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}