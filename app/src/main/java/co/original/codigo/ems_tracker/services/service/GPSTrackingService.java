package co.original.codigo.ems_tracker.services.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;

import co.original.codigo.ems_tracker.helpers.Constants;
import co.original.codigo.ems_tracker.helpers.LocationHelper;
import co.original.codigo.ems_tracker.services.interactor.GPSTrackingInteractor;
import co.original.codigo.ems_tracker.services.interactor.GPSTrackingInteractorImp;

public class GPSTrackingService extends Service {

    private Handler handler;
    private LocationHelper locationHelper;
    private GPSTrackingInteractor interactor;


    private String currentLatitude;
    private String currentLongitude;

    private String TAG = "LocationHelper";
    public static final String VEHICLE_NAME = "VEHICLE_NAME";
    public static final String VEHICLE_ID = "VEHICLE_ID";

    private String vehicleId;
    private String vehicleName;

    public GPSTrackingService() {
        interactor = new GPSTrackingInteractorImp(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null){
            vehicleId   = intent.getStringExtra(VEHICLE_ID);
            vehicleName = intent.getStringExtra(VEHICLE_NAME);
            if (vehicleId != null){
                initRepetitiveGPSTask();
            }else{
                onErrorLocationService("Parámetros del vehículo no indicados");
            }
        }else{
            onErrorLocationService("Error de recepción de parámetros del vehículo");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void initRepetitiveGPSTask(){
        locationHelper = new LocationHelper(getApplicationContext(), Constants.GPS_TIME_UPDATE, Constants.GPS_DISTANCE_UPDATE);
        handler = new Handler();
        handler.post(runnableGPSTracker);
    }

    private Runnable runnableGPSTracker = new Runnable() {
        @Override
        public void run() {
            Location lastKnownLocation = locationHelper.getLastKnownLocation();
            if (lastKnownLocation != null){
                currentLatitude = String.valueOf(lastKnownLocation.getLatitude());
                currentLongitude = String.valueOf(lastKnownLocation.getLongitude());
                interactor.updateGPSPosition(currentLatitude, currentLongitude);
            }
            handler.postDelayed(runnableGPSTracker, Constants.GPS_TIME_UPDATE);
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnableGPSTracker);
        super.onDestroy();
    }

    private void onErrorLocationService(String errorMessage){}
}
