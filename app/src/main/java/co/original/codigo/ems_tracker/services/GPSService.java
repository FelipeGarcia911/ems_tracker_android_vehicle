package co.original.codigo.ems_tracker.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import co.original.codigo.ems_tracker.helpers.LocalStorage.VehicleLocalStorage;
import co.original.codigo.ems_tracker.helpers.LocationHelper;
import co.original.codigo.ems_tracker.objects.Contansts;
import co.original.codigo.ems_tracker.objects.VehicleObject;

public class GPSService extends Service {
    private Handler handler;
    private LocationHelper locationHelper;
    private ServiceRepository serviceRepository;
    private VehicleLocalStorage vehicleLocalStorage;

    private String TAG = "LocationHelper";

    public GPSService() {
        serviceRepository = new ServiceRepositoryImp();
        vehicleLocalStorage = VehicleLocalStorage.getInstance();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initRepetitiveGPSTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initRepetitiveGPSTask(){
        locationHelper = new LocationHelper(getApplicationContext(), Contansts.GPS_TIME_UPDATE,Contansts.GPS_DISTANCE_UPDATE);
        handler = new Handler();
        handler.post(runnableGPSTracker);
    }

    private Runnable runnableGPSTracker = new Runnable() {
        @Override
        public void run() {
            Location lastKnownLocation = locationHelper.getLastKnownLocation();
            if (lastKnownLocation != null){
                String latitude = String.valueOf(lastKnownLocation.getLatitude());
                String longitude = String.valueOf(lastKnownLocation.getLongitude());
                Log.i("GPS",latitude+" -- "+longitude);
            }else {
                Log.i("GPS","Null Position");
            }
            handler.postDelayed(runnableGPSTracker, Contansts.GPS_TIME_UPDATE);
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnableGPSTracker);
        super.onDestroy();
    }

    private void updateGPSPosition(String latitude, String longitude){
        VehicleObject vehicleData = vehicleLocalStorage.getVehicle();
        vehicleData.setLatitude(latitude);
        vehicleData.setLongitude(longitude);

    }
}
