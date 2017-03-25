package co.original.codigo.ems_tracker.models;

import org.json.JSONException;
import org.json.JSONObject;

public class SocketMessageObject {

    private String method;
    private JSONObject data;
    private String message;
    private String errorCode;
    private String errorMsg;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
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

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method",    this.method);
            jsonObject.put("data" ,     this.data);
            jsonObject.put("message" ,  this.message);
            jsonObject.put("errorCode" ,this.errorCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
