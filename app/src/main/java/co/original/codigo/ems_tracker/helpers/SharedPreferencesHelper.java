package co.original.codigo.ems_tracker.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;

    private static class SingletonHolder {
        private static final SharedPreferencesHelper INSTANCE = new SharedPreferencesHelper();
    }

    public static SharedPreferencesHelper getInstance() {
        return SharedPreferencesHelper.SingletonHolder.INSTANCE;
    }

    public void initialize(Activity activity){
        this.sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
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
