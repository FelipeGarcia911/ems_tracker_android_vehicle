package co.original.codigo.ems_tracker.models;

import org.json.JSONException;
import org.json.JSONObject;

import co.original.codigo.ems_tracker.helpers.GSONHelper;

public class TrackingGPSObject {

    private String id;
    private String latitude;
    private String longitude;
    private GSONHelper gsonHelper;

    public TrackingGPSObject() {
        gsonHelper = new GSONHelper();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",        this.id);
            jsonObject.put("latitude" , this.latitude);
            jsonObject.put("longitude", this.longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
