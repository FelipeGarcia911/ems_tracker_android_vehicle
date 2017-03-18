package co.original.codigo.ems_tracker.helpers.LocalStorage;

import android.util.Log;

import com.google.gson.Gson;

import co.original.codigo.ems_tracker.helpers.SharedPreferencesHelper;
import co.original.codigo.ems_tracker.objects.VehicleObject;

public class VehicleLocalStorage {

    private static final String VEHICLE_KEY = "VEHICLE_KEY";
    private static final String VEHICLE_TIME = "VEHICLE_TIME";

    private Gson gson;
    private SharedPreferencesHelper preferencesHelper;

    private static class SingletonHolder {
        private static final VehicleLocalStorage INSTANCE = new VehicleLocalStorage();
    }

    public static VehicleLocalStorage getInstance() {
        return VehicleLocalStorage.SingletonHolder.INSTANCE;
    }

    public void initialize(){
        this.gson = new Gson();
        this.preferencesHelper = SharedPreferencesHelper.getInstance();
    }

    public void setUser(VehicleObject vehicleObject){
        String gsonString = gson.toJson(vehicleObject);
        preferencesHelper.write(VEHICLE_KEY,gsonString);
        saveUserTime();
    }

    public VehicleObject getVehicle(){
        VehicleObject userObject;
        String jsonString = preferencesHelper.read(VEHICLE_KEY);
        if (jsonString == null || jsonString.isEmpty()){
            return null;
        }else{
            userObject = gson.fromJson(jsonString,VehicleObject.class);
            return userObject;
        }
    }

    public void deleteVehicle(){
        if (preferencesHelper != null) {
            preferencesHelper.remove(VEHICLE_KEY);
            preferencesHelper.remove(VEHICLE_TIME);
        }else{
            Log.e("VehicleLocalStorage","preferencesHelper null");
        }
    }

    private void saveUserTime(){
        long longTime = System.currentTimeMillis();
        String stringTime = String.valueOf(longTime);
        if (preferencesHelper != null) {
            preferencesHelper.write(VEHICLE_TIME, stringTime);
        }else{
            Log.e("VehicleLocalStorage","preferencesHelper null");
        }
    }

    public boolean isLogin(){
        return getVehicle() != null;
    }

}
