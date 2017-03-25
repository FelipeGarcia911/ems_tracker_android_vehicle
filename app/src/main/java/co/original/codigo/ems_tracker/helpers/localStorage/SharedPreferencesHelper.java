package co.original.codigo.ems_tracker.helpers.localStorage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;
    private String SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES";

    private static class SingletonHolder {
        private static final SharedPreferencesHelper INSTANCE = new SharedPreferencesHelper();
    }

    public static SharedPreferencesHelper getInstance() {
        return SharedPreferencesHelper.SingletonHolder.INSTANCE;
    }

    public void initialize(Activity activity){
        this.sharedPreferences = activity.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_MULTI_PROCESS);
    }

    public void write(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public String read(String key){
        return sharedPreferences.getString(key,"");
    }

    public void remove(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

}
