package com.example.demo.returnDataObject;

/**
 * This class defines the schema of the response. It is used to encapsulate data prepared by
 * the server side, this object will be serialized to JSON before sent back to the client end.
 */
public class CustomReturnData {

    private boolean isSuccess;

    private Integer statusCode; // 200 means OK

    private String message;

    private Object data; // payload


    public CustomReturnData() {
    }

    public CustomReturnData(boolean isSuccess, Integer statusCode, String message) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }

    public CustomReturnData(boolean isSuccess, Integer statusCode, String message, Object data) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean success) {
        isSuccess = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
