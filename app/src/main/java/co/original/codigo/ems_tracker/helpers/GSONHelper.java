package co.original.codigo.ems_tracker.helpers;

import com.google.gson.Gson;

public class GSONHelper {
    private final Gson gson;

    public GSONHelper() {
        this.gson = new Gson();
    }

    public String serializeObject(Object object){
        return gson.toJson(object);
    }
}
