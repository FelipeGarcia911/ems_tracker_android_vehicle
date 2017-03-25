package co.original.codigo.ems_tracker.helpers;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiHelper {

    public JSONObject getDataFromJSONObject(JSONObject jsonResponseObject){
        JSONObject jsonDataObject = null;
        try {
            jsonDataObject = jsonResponseObject.getJSONObject(Constants.JSON_RESPONSE_DATA_OBJECT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonDataObject;
    }

    public int getStatusCodeFromJsonResponse(JSONObject jsonResponseObject){
        int statusCode = 0;
        try {
            statusCode = jsonResponseObject.getInt(Constants.JSON_RESPONSE_STATUS_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return statusCode;
    }

    public String getMessageFromJsonResponse(JSONObject jsonResponseObject){
        String message = "";
        try {
            message = jsonResponseObject.getString(Constants.JSON_RESPONSE_MESSAGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return message;
    }
}
