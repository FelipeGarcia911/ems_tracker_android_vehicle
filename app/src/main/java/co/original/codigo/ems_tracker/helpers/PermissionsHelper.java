package co.original.codigo.ems_tracker.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionsHelper {

    private Activity activity;
    public static final int GPS_PERMISSIONS = 1010;

    private static class SingletonHolder {
        private static final PermissionsHelper INSTANCE = new PermissionsHelper();
    }

    public static PermissionsHelper getInstance() {
        return PermissionsHelper.SingletonHolder.INSTANCE;
    }

    public void initialize(Activity activity){
        this.activity = activity;
    }

    public boolean isShowPermissionRequired(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public boolean isPermissionGranted(String manifestPermission){
        return ContextCompat.checkSelfPermission(activity,manifestPermission) == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isGPSPermissionsGranted(){
        return isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION) && isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public List<String> getListGPSPermissionRequired(){
        List<String> permissionsList = new ArrayList<>();
        if( !isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if( !isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)){
            permissionsList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        return permissionsList;
    }
}
