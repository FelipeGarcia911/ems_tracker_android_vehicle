package co.original.codigo.ems_tracker.models;

import co.original.codigo.ems_tracker.helpers.GSONHelper;

public class SocketMessageObject {

    private String method;
    private String data;
    private String message;
    private String errorCode;
    private String errorMsg;

    private GSONHelper gsonHelper;

    public SocketMessageObject() {
        gsonHelper = new GSONHelper();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = gsonHelper.serializeObject(data);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String serializeObject(){
        return gsonHelper.serializeObject(this);
    }
}
